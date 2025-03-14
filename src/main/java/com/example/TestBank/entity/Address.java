package com.example.TestBank.entity;


import jakarta.persistence.*;
import lombok.Data;

    @Data
    @Entity
    @Table(name = "table_test2")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private String street;
    private String houseNumber;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

}
