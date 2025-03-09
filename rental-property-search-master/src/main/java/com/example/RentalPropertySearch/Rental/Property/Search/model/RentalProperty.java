package com.example.RentalPropertySearch.Rental.Property.Search.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RentalProperty {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String propertyName;

    private String address;

    private String city;

    private Double price;

    private Double leaseTerm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getLeaseTerm() {
        return leaseTerm;
    }

    public void setLeaseTerm(Double leaseTerm) {
        this.leaseTerm = leaseTerm;
    }
}
