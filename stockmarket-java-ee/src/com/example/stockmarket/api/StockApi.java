package com.example.stockmarket.api;

import java.util.UUID;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.json.Json;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseBroadcaster;
import javax.ws.rs.sse.SseEventSink;

import com.example.stockmarket.application.StockMarketApplication;
import com.example.stockmarket.application.events.StockPriceChangedEvent;
import com.example.stockmarket.entity.Stock;
import com.example.stockmarket.validation.StockSymbol;

@Path("/stocks")
@ApplicationScoped
public class StockApi {
	@Inject
	private StockMarketApplication stockMarketApp;
	@Resource // (2)
	private ManagedExecutorService managedExecutorService;
	
	// SSE Configuration
	@SuppressWarnings("unused")
	private Sse sse;
	private SseBroadcaster sseBroadcaster;
	private OutboundSseEvent.Builder eventBuilder;
	
	@Context
	public void setSse(Sse sse) {
		this.sse = sse;
		this.sseBroadcaster = sse.newBroadcaster();
		this.eventBuilder = sse.newEventBuilder();
	}

	@GET
	@Path("subscribe")
	@Produces(MediaType.SERVER_SENT_EVENTS)
	public void listen(@Context SseEventSink sseEventSink) {
		this.sseBroadcaster.register(sseEventSink);
	}
	
	// http://localhost:8100/stockmarket/api/v1/stocks/ORCL
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{symbol}")
	public Stock findBySymbol(@PathParam("symbol") String symbol) {
		return stockMarketApp.findStockBySymbol(symbol);
	}
	
	// http://localhost:8100/stockmarket/api/v1/stocks?page=0&size=15
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	// Async ?
	public void findAllByPage(
			@QueryParam("page") int page,
			@QueryParam("size") int size,
			@Suspended AsyncResponse asyncResponse // (1)
		) {
		managedExecutorService.execute( // (3) 
		   () -> asyncResponse.resume(stockMarketApp.findStocks(page,size))
		);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Stock addStock(@Valid Stock stock) {
		return stockMarketApp.add(stock);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Stock updateStock(@Valid Stock stock) {
		return stockMarketApp.update(stock);
	}
	
	public void listenStockPriceChanges(@Observes StockPriceChangedEvent event) {
		var json = Json.createObjectBuilder()
		    .add("symbol", event.getSymbol())
		    .add("oldPrice", event.getOldPrice())
		    .add("newPrice", event.getNewPrice())
		    .build()
		    .toString();
		var sseEvent = 
				eventBuilder.name("stockPriceChangedEvent")
				            .id(UUID.randomUUID().toString())
				            .mediaType(MediaType.APPLICATION_JSON_TYPE)
				            .data(json)
				            .reconnectDelay(3000)
				            .comment("price changed")
				            .build();
		sseBroadcaster.broadcast(sseEvent);
	
	}
	
	@DELETE
	@Path("{symbol}")
	@Produces(MediaType.APPLICATION_JSON)
	public Stock removeStock(@PathParam("symbol") @Valid @StockSymbol String symbol) {
		return stockMarketApp.remove(symbol);
	}
}
