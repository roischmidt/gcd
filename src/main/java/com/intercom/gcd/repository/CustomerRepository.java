package com.intercom.gcd.repository;

import com.intercom.gcd.dto.Customer;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

/**
 * Storage class for the validated customers
 */
@Repository
public class CustomerRepository {

    private List<Customer> customers = new LinkedList<>();

    public void addCustomer(Customer customer){
        customers.add(customer.copy());
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void clear() {
        customers.clear();
    }
}
