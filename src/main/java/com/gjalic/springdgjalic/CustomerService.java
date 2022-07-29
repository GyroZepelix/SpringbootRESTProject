package com.gjalic.springdgjalic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Iterable<Customer> findAll() {
        return repository.findAll();
    }

    public Customer findById(long id) {
        return repository.findById(id);
    }

    public List<Customer> findByActive(Boolean active) {
        return repository.findByActive(active);
    }

    public List<Customer> findByLastName(String lastName) {
        return repository.findByLastName(lastName);
    }

    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    public Customer replaceCustomerByID(Customer _newCustomer, Long id) {

        return repository.findById(id)
                .map(customer -> {
                    customer.setFirstName(_newCustomer.getFirstName());
                    customer.setLastName(_newCustomer.getLastName());
                    customer.setActive(_newCustomer.getActive());
                    return repository.save(customer);
                }).orElseGet(() -> {
                    _newCustomer.setId(id);
                    return repository.save(_newCustomer);
                });
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }




}
