package com.gjalic.springdgjalic.customer;

import org.springframework.data.repository.CrudRepository;

public interface FileRepository extends CrudRepository<File, Long> {
    File findById(long id);
}
