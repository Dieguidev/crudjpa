package com.dieguidev.crudjpa.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.dieguidev.crudjpa.services.IProductService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class isExistsDbValidation implements ConstraintValidator<IsExistsDb, String> {

    @Autowired
    private IProductService productService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (productService == null) {
            return true;

        }
        return !productService.existsBySku(value);
    }

}
