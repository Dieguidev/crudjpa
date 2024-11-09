package com.dieguidev.crudjpa;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import com.dieguidev.crudjpa.entities.Product;

import io.micrometer.common.lang.NonNull;

@Component
@Validated
public class ProductValidation implements Validator {

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return Product.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target,@NonNull Errors errors) {
        Product product = (Product) target;

        if (product.getName() == null || product.getName().isEmpty()) {
            errors.rejectValue("name", "name.empty", "El nombre del producto no puede estar vacío");
        }

        if (product.getPrice() == null || product.getPrice().doubleValue() <= 0) {
            errors.rejectValue("price", "price.invalid", "El precio del producto debe ser mayor que cero");
        }

        if (product.getDescription() == null || product.getDescription().isEmpty()) {
            errors.rejectValue("description", "description.empty", "La descripción del producto no puede estar vacía");
        }
    }

}
