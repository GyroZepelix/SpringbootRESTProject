package com.gjalic.springdgjalic.customer;

public class FileNotFoundException extends RuntimeException{
    FileNotFoundException(long customerId ,long id) {super("Could not find file " + id + " on Customer " + customerId);}
}
