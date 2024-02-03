package com.sumbaseassignment.Services;

import com.sumbaseassignment.Model.Customer;
import com.sumbaseassignment.Repository.CustomerRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServices {

    // Autowired instance of CustomerRepository for accessing data layer.
    @Autowired
    private CustomerRepository customerRepository;

    // Adds a new customer to the database.
    public Customer addCustomer(Customer customer) throws Exception{
        try {
            return customerRepository.save(customer);
        }
        catch (Exception e){
            throw new Exception("Something wen't wrong please check then try again");
        }
    }


    // Retrieves a customer by their ID.
    public Customer getCustomerById(int customerId) throws  Exception{

        Optional<Customer> customer = customerRepository.findById(customerId);

        if(customer.isEmpty()){
            throw new Exception("Customer not found");
        }
        return customer.get();
    }


    // Deletes a customer by their ID.
    public void deleteCustomerById(int customerId) throws Exception {

        try {
            Optional<Customer> customer = customerRepository.findById(customerId);

            if(customer.isEmpty()){
                throw new Exception("Customer not found");
            }
            else {
                customerRepository.deleteById(customerId);
            }

        } catch (EmptyResultDataAccessException e) {
            throw new Exception("Invalid id: " + customerId, e);
        }
    }

    // Updates details of an existing customer.
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



    // Retrieves all customers with pagination.
    public Page<Customer> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    // Searches for customers based on a search term.
    public List<Customer> searchCustomers(String searchTerm) {
        return customerRepository.searchCustomers(searchTerm);
    }





}
