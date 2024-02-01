package com.sumbaseassignment.Repository;

import com.sumbaseassignment.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
