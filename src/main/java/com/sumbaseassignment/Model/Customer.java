package com.sumbaseassignment.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Customer {
    // Primary key for the customer entity, auto-generated.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    private String firstName;

    private String lastName;

    private String street;

    private String address;

    private String city;

    private String state;

    private String email;

    private String phoneNumber;


}
