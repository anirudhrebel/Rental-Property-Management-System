package com.example.RentalPropertySearch.Rental.Property.Search.DataAccessObjects;

import com.example.RentalPropertySearch.Rental.Property.Search.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmailAddress(String email);
}
