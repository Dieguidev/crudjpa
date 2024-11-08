package com.dieguidev.crudjpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.dieguidev.crudjpa.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
    // User findByUsername(String username);

}
