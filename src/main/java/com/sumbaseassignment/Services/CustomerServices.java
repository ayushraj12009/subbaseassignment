package com.sumbaseassignment.Services;

import com.sumbaseassignment.Model.Customer;
import com.sumbaseassignment.Repository.CustomerRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

        Optional<Customer> customer = customerRepository.findById(customerId);

        if(customer.isEmpty()){
            throw new Exception("Customer not found");
        }
        return customer.get();
    }


    public void deleteCustomerById(int customerId) throws Exception {
        try {
            customerRepository.deleteById(customerId);
        } catch (EmptyResultDataAccessException e) {
            throw new Exception("Invalid id: " + customerId, e);
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
