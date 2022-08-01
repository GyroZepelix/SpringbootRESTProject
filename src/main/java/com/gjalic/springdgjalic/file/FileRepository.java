package com.gjalic.springdgjalic.file;

import com.gjalic.springdgjalic.customer.Customer;
import com.gjalic.springdgjalic.file.File;
import org.springframework.data.repository.CrudRepository;

public interface FileRepository extends CrudRepository<File, Long> {
    File findById(long id);

    Iterable<File> findByCustomer(Customer customer);
}
