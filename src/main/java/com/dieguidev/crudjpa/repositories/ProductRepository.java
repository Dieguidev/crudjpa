package com.dieguidev.crudjpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.dieguidev.crudjpa.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
