package com.example.RentalPropertySearch.Rental.Property.Search.DataAccessObjects;

import com.example.RentalPropertySearch.Rental.Property.Search.model.SavedProperty;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SavedPropertyRepository extends CrudRepository<SavedProperty, Long> {
    List<SavedProperty> findAllByUserId(Integer userId);
}
