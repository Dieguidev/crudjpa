package com.dieguidev.crudjpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dieguidev.crudjpa.entities.User;
import com.dieguidev.crudjpa.repositories.RoleRepository;
import com.dieguidev.crudjpa.repositories.UserRepository;



@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional
    public User save(User user) {

        return userRepository.save(user);
    }

}
