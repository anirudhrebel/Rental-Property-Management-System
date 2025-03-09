package com.example.RentalPropertySearch.Rental.Property.Search.services;

import com.example.RentalPropertySearch.Rental.Property.Search.DataAccessObjects.DaoServiceManager;
import com.example.RentalPropertySearch.Rental.Property.Search.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    public DaoServiceManager manager;

    public String saveUser(User user){
        if(user.getEmailAddress() != null){
            manager.getUserRepository().save(user);
            return "Saved Successfully";
        }
        else{
            return "Could Not Save User";
        }
    }
}
