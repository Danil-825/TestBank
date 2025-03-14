package com.example.TestBank.response;

import lombok.Data;

@Data
public class AddressResponse {
    private String city;
    private String street;
    private String houseNumber;

    public AddressResponse(String city, String street, String houseNumber) {
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }
}