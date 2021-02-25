package com.example.stockmarket.api;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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
import javax.ws.rs.core.MediaType;

import com.example.stockmarket.application.StockMarketApplication;
import com.example.stockmarket.entity.Stock;
import com.example.stockmarket.validation.StockSymbol;

@Path("/stocks")
@ApplicationScoped
public class StockApi {
	@Inject
	private StockMarketApplication stockMarketApp;
	@Resource // (2)
	private ManagedExecutorService managedExecutorService;
	
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
	@Path("{symbol}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Stock updateStock(
			@PathParam("symbol") @Valid @StockSymbol String symbol, 
			@Valid Stock stock) {
		return stockMarketApp.update(stock);
	}
	
	@DELETE
	@Path("{symbol}")
	@Produces(MediaType.APPLICATION_JSON)
	public Stock removeStock(
			@PathParam("symbol") @Valid @StockSymbol String symbol) {
		return stockMarketApp.remove(symbol);
	}
}
