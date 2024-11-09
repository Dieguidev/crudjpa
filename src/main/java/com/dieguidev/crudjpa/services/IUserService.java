package com.dieguidev.crudjpa.services;

import java.util.List;

import com.dieguidev.crudjpa.entities.User;

public interface IUserService {
    List<User> findAll();

    User save(User user);
}
