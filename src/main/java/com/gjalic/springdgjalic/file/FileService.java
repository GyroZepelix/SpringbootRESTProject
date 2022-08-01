package com.gjalic.springdgjalic.file;

import com.gjalic.springdgjalic.customer.Customer;
import com.gjalic.springdgjalic.customer.CustomerRepository;
import com.gjalic.springdgjalic.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    @Autowired
    private final FileRepository repository;


    public FileService(FileRepository repository) {
        this.repository = repository;

    }

    public Iterable<File> findAll() {
        return repository.findAll();
    }

    public Iterable<File> findByCustomer(Customer customer) {
        return repository.findByCustomer(customer);
    }

//    public File findSpecific(Customer customer, long id) { return repository.findBySpecific(customer,id); }

    public File save(File file) {return repository.save(file);}


//    public File saveFile(MultipartFile file, long id) {
//        String documentName = file.getOriginalFilename();
//        try {
//            repository.save();
//            //findById(id).getCustomerFiles().add((new File( documentName, file.getContentType(), file.getBytes())));
//        }
//
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        return findById(id).getCustomerFiles().get();
//  }

    //-------------

//    public List<File> getFilesFromCustomer(long customerId) {
//        return findById(customerId).getCustomerFiles();
//    }
//    public File findFile(Customer customer, long id) throws FileNotFoundException {
//        Iterable<File> files = findByCustomer(customer);
//        for (File file : files) {
//            if (file.getID() == id) throw
//        }
//        throw new FileNotFoundException(customerId, id);
//    }
//
//    public List<File> getFiles() {
//        Iterable<Customer> customers = findAll();
//
//        ArrayList<File> files = new ArrayList<File>();
//        customers.forEach(customer -> files.addAll(customer.getCustomerFiles()));
//        return files;
//    }
}

