package com.example.RentalPropertySearch.Rental.Property.Search.DataAccessObjects;

import com.example.RentalPropertySearch.Rental.Property.Search.model.RentalProperty;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface RentalPropertyRepository extends CrudRepository<RentalProperty, Integer> {
    List<RentalProperty> findByPriceBetween(Double start, Double end);
}