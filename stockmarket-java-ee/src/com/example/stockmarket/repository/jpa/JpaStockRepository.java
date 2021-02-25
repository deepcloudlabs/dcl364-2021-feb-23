package com.example.stockmarket.repository.jpa;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.example.stockmarket.application.events.StockPriceChangedEvent;
import com.example.stockmarket.entity.Stock;
import com.example.stockmarket.repository.StockRepository;

@Stateless
public class JpaStockRepository implements StockRepository {
	@PersistenceContext(unitName = "stockmarketPU")
	private EntityManager entityManager;
	@Inject
	private Event<StockPriceChangedEvent> event;
	
	@Override
	public Optional<Stock> findOne(String symbol) {
		return Optional.ofNullable(entityManager.find(Stock.class, symbol));
	}

	@Override
	public List<Stock> findAll(int page, int size) {
		return entityManager.createNamedQuery("Stock.findAll", Stock.class)
				             .setFirstResult(page*size)
				             .setMaxResults(size)
				             .getResultList();
	}

	// 1. Async
	// 2. Transaction
	@Override
	@Transactional
	public Future<Stock> create(Stock stock) {
		entityManager.persist(stock);
		return new AsyncResult<Stock>(stock);
	}

	@Override
	@Transactional
	public Stock update(Stock stock) {
		var symbol = stock.getSymbol();
		var managedStock = entityManager.find(Stock.class, symbol);
		if (Objects.isNull(managedStock))
			throw new IllegalArgumentException("Cannot find stock to update");
		var stockEvent = new StockPriceChangedEvent(
				symbol, managedStock.getPrice(), stock.getPrice());
		managedStock.setPrice(stock.getPrice());
		managedStock.setDescription(stock.getDescription());
		managedStock.setCompany(stock.getCompany());
		entityManager.merge(managedStock);
		event.fire(stockEvent);
		return managedStock;
	}

	@Override
	@Transactional
	public Stock removeById(String symbol) {
		var managedStock = entityManager.find(Stock.class, symbol);
		if (Objects.isNull(managedStock))
			throw new IllegalArgumentException("Cannot find stock to remove");
		entityManager.remove(managedStock);
		return managedStock;
	}

	@Override
	@Transactional
	public Stock remove(Stock stock) {
		return this.removeById(stock.getSymbol());
	}

	@Override
	public List<Stock> findAllByCompany(String company) {
		return entityManager.createNamedQuery("Stock.findAllByCompany", Stock.class)
				     .setParameter("company", company)
				     .getResultList();
	}

}
