package com.example.TestBank.response;

import lombok.Data;

@Data
public class PersonResponse {
    private int id;
    private String name;
    private int age;
    private String gender;

    public PersonResponse(int id, String name, int age, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}
