package com.sumbaseassignment.Services;

import com.sumbaseassignment.Model.Customer;
import com.sumbaseassignment.Repository.CustomerRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServices {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer addCustomer(Customer customer) throws Exception{
        try {
            return customerRepository.save(customer);
        }
        catch (Exception e){
            throw new Exception("Something wen't wrong please check then try again");
        }
    }

    public Customer getCustomerById(int customerId) throws  Exception{
        try {
            return customerRepository.findById(customerId).orElse(null);
        }
        catch (Exception e){
            throw new Exception("Customer Id is not valid please check");
        }
    }

//    public Customer getCustomerById(Integer cutomerId) throws Exception{
//        Optional<Customer> optionalCustomer = customerRepository.findById(cutomerId);
//
//        if(optionalCustomer.isEmpty()){
//            throw new Exception("customer Id is invalid");
//        }
//        Customer result = optionalCustomer.get();
//        return  result;
//    }



    public void deleteCustomerById(int customerId) {
        try {
            customerRepository.deleteById(customerId);
        }
        catch (Exception e) {
            throw new RuntimeException("Customer Id is not valid, please check again: " + customerId, e);
        }
    }

    public Customer updateCustomerDetails(int cutomerId, Customer updateCustomer) throws Exception {

        try {
            Customer existingCustomer  =  customerRepository.findById(cutomerId).orElse(null);

         if(existingCustomer != null){
             existingCustomer.setFirstName(updateCustomer.getFirstName());
             existingCustomer.setLastName(updateCustomer.getLastName());
             existingCustomer.setCity(updateCustomer.getCity());
             existingCustomer.setEmail(updateCustomer.getEmail());
             existingCustomer.setAddress(updateCustomer.getAddress());
             existingCustomer.setState(updateCustomer.getState());
             existingCustomer.setStreet(updateCustomer.getStreet());
             existingCustomer.setPhoneNumber(updateCustomer.getPhoneNumber());

             return customerRepository.save(existingCustomer);
         }
         else {
             return null;
         }
        }
        catch (Exception e){
            throw new Exception("Customer not found");
        }
    }

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }






}
