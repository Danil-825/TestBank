package com.example.TestBank.response;

import lombok.Data;
import java.util.List;

@Data
public class PersonWithAddressResponse {
    private int id;
    private String name;
    private int age;
    private String gender;
    private List<AddressResponse> addresses;

    public PersonWithAddressResponse(int id, String name, int age, String gender, List<AddressResponse> addresses) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.addresses = addresses;
    }
}