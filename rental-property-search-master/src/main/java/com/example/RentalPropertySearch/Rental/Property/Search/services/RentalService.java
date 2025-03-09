package com.example.RentalPropertySearch.Rental.Property.Search.services;

import com.example.RentalPropertySearch.Rental.Property.Search.DataAccessObjects.DaoServiceManager;
import com.example.RentalPropertySearch.Rental.Property.Search.model.RentalProperty;
import com.example.RentalPropertySearch.Rental.Property.Search.model.SavedProperty;
import com.example.RentalPropertySearch.Rental.Property.Search.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RentalService {

    User carryUserSession = null;

    @Autowired
    public DaoServiceManager manager;

    public String loginService(String email){
        User user = manager.getUserRepository().findByEmailAddress(email);
        if(user != null){
            carryUserSession = user;
            return "Successfully Authenticated Please continue Using the Application";
        }
        return "Could Not Authenticate That User";
    }

    public Iterable<RentalProperty> viewProperties(){
        if(carryUserSession == null){
            return null;
        }
        else{
            return manager.getRentalPropertyRepository().findAll();
        }
    }

    public Iterable<RentalProperty> searchBasedOnPriceRange(String start, String end){
        if(carryUserSession == null){
            return null;
        }
        else{
            Double begin = Double.parseDouble(start);
            Double ending = Double.parseDouble(end);
            List<RentalProperty> properties = manager.getRentalPropertyRepository().findByPriceBetween(begin,ending);
            return properties;
        }
    }

    public String savePropertyAsFavorite(String id){
        if(carryUserSession == null){
            return "Please Authenticate First";
        }
        else{
            Optional<RentalProperty> rentalPropertyOptional = manager.getRentalPropertyRepository().findById(Integer.parseInt(id));
            if(!rentalPropertyOptional.isPresent()){
                return "Rental with that ID does not exists";
            }
            else{
                RentalProperty property = rentalPropertyOptional.get();
                SavedProperty savedProperty = new SavedProperty();
                savedProperty.setRentalId(property.getId());
                savedProperty.setUserId(carryUserSession.getId());
                manager.getSavedPropertyRepository().save(savedProperty);
            }
            return "Successfully Saved The Property As Your Fav";
        }
    }

    public String saveNewProperty(RentalProperty property){
        if(property.getAddress() == null){
            return "Missing Address";
        }
        if(property.getCity() == null){
            return "Missing City";
        }
        if(property.getLeaseTerm() == null){
            return "Missing Lease Term";
        }
        if(property.getPrice() == null){
            return "Missing Price";
        }
        if(property.getPropertyName() == null){
            return "Missing Property Name";
        }
        manager.getRentalPropertyRepository().save(property);
        return "Saved Successfully";
    }

    public Iterable<RentalProperty> viewSavedPropertiesList(){
        if(carryUserSession == null){
            return null;
        }
        else{
            List<RentalProperty> savedRentalsToReturn = new ArrayList<RentalProperty>();
            List<SavedProperty> properties = manager.getSavedPropertyRepository().findAllByUserId(carryUserSession.getId());
            for(SavedProperty property : properties){
                Optional<RentalProperty> optional = manager.getRentalPropertyRepository().findById(property.getRentalId());
                if(optional.isPresent()){
                    RentalProperty rental = optional.get();
                    savedRentalsToReturn.add(rental);
                }
            }
            return savedRentalsToReturn;
        }
    }
}
