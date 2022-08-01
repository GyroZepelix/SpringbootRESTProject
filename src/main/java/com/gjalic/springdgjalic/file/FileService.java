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


    public FileService(FileRepository repository, CustomerService cService) {
        this.repository = repository;

    }

    public Iterable<File> findAll() {
        return repository.findAll();
    }

    public Iterable<File> findByCustomer(Customer customer) {
        return repository.findByCustomer(customer);
    }

    public File save(File file) {
        return repository.save(file);
    }

    public File saveFile(MultipartFile file, long id) {
        String documentName = file.getOriginalFilename();
        try {
            repository.save();
            //findById(id).getCustomerFiles().add((new File( documentName, file.getContentType(), file.getBytes())));
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        return findById(id).getCustomerFiles().get();
    }

    //-------------

    public List<File> getFilesFromCustomer(long customerId) {
        return findById(customerId).getCustomerFiles();
    }
    public File getFile(long customerId, long id) throws FileNotFoundException {
        List<File> files = findById(customerId).getCustomerFiles();
        for (File file : files) {
            if (file.getID() == id) {
                return file;
            }
        }
        throw new FileNotFoundException(customerId, id);
    }

    public List<File> getFiles() {
        Iterable<Customer> customers = findAll();

        ArrayList<File> files = new ArrayList<File>();
        customers.forEach(customer -> files.addAll(customer.getCustomerFiles()));
        return files;
    }
}

