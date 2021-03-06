package com.example.stockmarket.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.example.stockmarket.validation.StockSymbol;

// JPA: ORM Solution
// JPA Provider -> Hibernate, EclipseLink, OpenJPA, ...
// Bean Validation -> Constraint
@Entity
@Table(name = "stocks")
@NamedQueries({ @NamedQuery(name = "Stock.findAll", query = "select s from Stock s"),
		@NamedQuery(name = "Stock.findAllByCompany", query = "select s from Stock s where s.company=:company") })
public class Stock {
	@Id
	@StockSymbol
	private String symbol;
	private String description;
	@NotEmpty
	private String company;
	@Column(name = "fiyat")
	@Min(0)
	private double price;

	public Stock() {
	}

	public Stock(Stock stock) {
		this.symbol = stock.symbol;
		this.company = stock.company;
		this.price = stock.price;
		this.description = stock.description;
	}

	public Stock(String symbol, String description, @NotEmpty String company, @Min(0) double price) {
		this.symbol = symbol;
		this.description = description;
		this.company = company;
		this.price = price;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Stock [symbol=" + symbol + ", description=" + description + ", company=" + company + ", price=" + price
				+ "]";
	}

}
