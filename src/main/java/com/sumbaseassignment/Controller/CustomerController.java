package com.sumbaseassignment.Controller;


import com.sumbaseassignment.Exception.ResourceNotFoundException;
import com.sumbaseassignment.Model.Customer;
import com.sumbaseassignment.Services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
    public ResponseEntity findById(@PathVariable int customerId) throws Exception{
        try{
            return ResponseEntity.ok(customerServices.getCustomerById(customerId));
        }
        catch (Exception e){
//            throw new Exception("Customer Not Found");
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    @DeleteMapping("/api/deleteById/{id}")
//    public ResponseEntity<String> deleteById(@PathVariable int id ) {
//            try {
//                customerServices.deleteCustomerById(id);
//                return ResponseEntity.ok("Cutomer deleted from DB");
//            }
//            catch (Exception e){
//                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//            }
//    }


    @DeleteMapping("/api/deleteById/{customerId}")
    public ResponseEntity deleteById(@PathVariable int customerId) {
        try {
            customerServices.deleteCustomerById(customerId);
            return ResponseEntity.ok("Customer deleted from DB");
        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/api/updateDetails/{id}")
    public ResponseEntity<Customer> updateCustomerDetails(
            @PathVariable int id,
            @RequestBody Customer updateCustomer) {
        try {
            Customer updatedCustomer = customerServices.updateCustomerDetails(id, updateCustomer);

            if (updatedCustomer != null) {
                return ResponseEntity.ok(updatedCustomer);
            } else {
                // If the customer with the given ID is not found, return a response with 404 status
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null); // or new ResponseEntity<>("Customer not found with ID: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Handle other exceptions, and return a response with 500 Internal Server Error status
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



}
