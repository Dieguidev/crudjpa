package com.dieguidev.crudjpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.dieguidev.crudjpa.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
    // Role findByName(String name);

}
