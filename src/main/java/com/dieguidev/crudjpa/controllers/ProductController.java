package com.dieguidev.crudjpa.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import com.dieguidev.crudjpa.ProductValidation;
import com.dieguidev.crudjpa.entities.Product;
import com.dieguidev.crudjpa.services.IProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    // @Autowired
    // private ProductValidation productValidation;

    @GetMapping("")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<Product> list() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (productOptional.isPresent()) {
            return ResponseEntity.ok(productOptional.orElseThrow());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // *método anterior mejorado con ResponseStatusException
    // @GetMapping("/{id}")
    // public ResponseEntity<Product> getById(@PathVariable Long id) {
    // Product product = productService.findById(id)
    // .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product
    // not found"));
    // return ResponseEntity.ok(product);
    // }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult result) {
        // productValidation.validate(product, result);
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@Valid @RequestBody Product product, BindingResult result,
            @PathVariable Long id) {
        // productValidation.validate(product, result);
        if (result.hasFieldErrors()) {
            return validation(result);
        }

        Optional<Product> produOptional = productService.update(id, product);
        if (produOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(produOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Product> productOptional = productService.delete(id);
        if (productOptional.isPresent()) {
            return ResponseEntity.ok(productOptional.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }
}
