package com.dieguidev.crudjpa.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.dieguidev.crudjpa.services.IUserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExistsByUsernameValidate implements ConstraintValidator<ExistsByUsername, String> {

    @Autowired
    private IUserService userService;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (userService == null) {
            return true;

        }
        return !userService.existsByUsername(username);
    }

}
