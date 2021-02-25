package com.example.stockmarket.repository.jpa;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.example.stockmarket.entity.Stock;
import com.example.stockmarket.repository.StockRepository;

@Stateless
public class JpaStockRepository implements StockRepository {
	@PersistenceContext
	private EntityManager entityManager;
	
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
	public Stock update(Stock entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Stock removeById(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Stock remove(Stock entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Stock> findAllByCompany(String company) {
		return entityManager.createNamedQuery("Stock.findAllByCompany", Stock.class)
				     .setParameter("company", company)
				     .getResultList();
	}

}
