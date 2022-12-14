package com.gjalic.springdgjalic.customer;

import com.gjalic.springdgjalic.file.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
public class CustomerController {


    @Autowired
    CustomerService service;

    CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/customers")
    Iterable<Customer> all() {
        return service.findAll();
    }

    @PostMapping("/customers")
    Customer newCustomer(@RequestBody Customer newCustomer) {
        return service.save(newCustomer);
    }

    @GetMapping("/customers/{id}")
    Customer one(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/customers/query")
    List<Customer> getSpecific(@RequestParam(name="active") Boolean isActive) {
        return service.findByActive(isActive);
    }

    @PutMapping("/customers/{id}")
    Customer replaceCustomer(@RequestBody Customer newCustomer, @PathVariable Long id) {

        return service.replaceCustomerByID(newCustomer, id);
    }

    @DeleteMapping("/customers/{id}")
    void deleteCustomer(@PathVariable Long id) {
        service.deleteById(id);
    }




}
