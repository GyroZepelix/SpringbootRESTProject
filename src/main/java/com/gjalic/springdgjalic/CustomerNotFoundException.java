package com.gjalic.springdgjalic;

public class CustomerNotFoundException extends RuntimeException{

    CustomerNotFoundException(Long id) {
        super("Could not find customer " + id);
    }
}
