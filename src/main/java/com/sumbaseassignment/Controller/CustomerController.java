package com.sumbaseassignment.Controller;


import com.sumbaseassignment.Model.Customer;
import com.sumbaseassignment.Services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/home")
public class CustomerController {

    @Autowired
    private CustomerServices customerServices;

    @GetMapping("/user")
    public String geuserDetails(){
        System.out.print("Getting User");
        return "users";
    }

    @PostMapping("/api/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) throws Exception{
        try{
            Customer customerDetails = customerServices.addCustomer(customer);
            return ResponseEntity.ok(customerDetails);
        }
        catch (Exception e){
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/api/getlist")
    public ResponseEntity<List<Customer>> getList() throws  Exception{
        try{
            return ResponseEntity.ok(customerServices.getAllCustomer());
        }
        catch (Exception e){
            throw new Exception("Internal Server Issue");
        }
    }

    @GetMapping("/api/CustomerById/{customerId}")
    public ResponseEntity<Customer> findById(@PathVariable int customerId) throws Exception{
        try{
            return ResponseEntity.ok(customerServices.getCustomerById(customerId));
        }
        catch (Exception e){
            throw new Exception("Customer Not Found");
        }

    }

}
