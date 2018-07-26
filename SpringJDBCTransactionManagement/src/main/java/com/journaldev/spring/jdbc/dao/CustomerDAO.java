package com.journaldev.spring.jdbc.dao;

import com.journaldev.spring.jdbc.model.Customer;

public interface CustomerDAO {

	public abstract void create(Customer customer);
}