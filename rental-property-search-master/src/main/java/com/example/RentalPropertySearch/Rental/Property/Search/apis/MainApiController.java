package com.example.RentalPropertySearch.Rental.Property.Search.apis;

import com.example.RentalPropertySearch.Rental.Property.Search.model.User;
import com.example.RentalPropertySearch.Rental.Property.Search.services.RentalService;
import com.example.RentalPropertySearch.Rental.Property.Search.model.RentalProperty;
import com.example.RentalPropertySearch.Rental.Property.Search.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/api")
public class MainApiController {

    @Autowired
    public RentalService rentalService;

    @Autowired
    public UserService userService;

    //hit login controller first to carry a small session

    //login?email="bob@gmail.com
    @GetMapping(path="/login")
    public @ResponseBody
    String loginApiController(@RequestParam String email) {
        return rentalService.loginService(email);
    }

    @GetMapping(path="/viewall")
    public @ResponseBody
    Iterable<RentalProperty> viewProperties() {
        return rentalService.viewProperties();
    }

    //search?start=1000&end=200000
    @GetMapping(path="/search")
    public @ResponseBody Iterable<RentalProperty> searchBasedOnPriceRange(@RequestParam String start, @RequestParam String end) {
        return rentalService.searchBasedOnPriceRange(start,end);
    }

    //api/savefavoriate/2
    @GetMapping(path="/savefavorite/{id}")
    public @ResponseBody
    String savePropertyAsFavorite(@PathVariable String id) {
        return rentalService.savePropertyAsFavorite(id);
    }

    @PostMapping(path="/saveproperty")
    public @ResponseBody
    String saveNewProperty(@RequestBody RentalProperty property) {
        return rentalService.saveNewProperty(property);
    }

    @PostMapping(path="/saveuser")
    public @ResponseBody
    String saveNewuser(@RequestBody User user) { return userService.saveUser(user); }

    @GetMapping(path="/viewsaved")
    public @ResponseBody
    Iterable<RentalProperty> viewSavedPropertiesList() {
        return rentalService.viewSavedPropertiesList();
    }
}
