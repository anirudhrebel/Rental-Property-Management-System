package com.example.RentalPropertySearch.Rental.Property.Search.DataAccessObjects;

import com.example.RentalPropertySearch.Rental.Property.Search.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

//this class is the manager for all repositorys and needs the given annotations
@Service
public class DaoServiceManager {

    @Autowired
    private RentalPropertyRepository rentalPropertyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SavedPropertyRepository savedPropertyRepository;

    @Autowired
    private RentalService rentalService;

    public RentalPropertyRepository getRentalPropertyRepository() {
        return rentalPropertyRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public SavedPropertyRepository getSavedPropertyRepository() { return savedPropertyRepository; }

    @Bean
    public RentalService getRentalService() { return rentalService; }
}
