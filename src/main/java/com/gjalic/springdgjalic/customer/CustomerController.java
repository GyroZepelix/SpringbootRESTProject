package com.gjalic.springdgjalic.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
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

    @GetMapping("/customers/files")
    public String getFiles(Model model) {
        List<File> files = service.getFiles();
        model.addAttribute("docs", files);
        return "doc";
    }

    @GetMapping("/customers/{id}/files")
    public String  getFilesFromOne(@PathVariable Long id ,Model model) {
        List<File> files = service.getFilesFromCustomer(id);
        model.addAttribute("docs", files);
        return "doc";
    }

//    @PostMapping("/customers/{id}/files")
//    public String UploadMultipleFiles(@RequestBody() MultipartFile[] files, @PathVariable Long id) {
//        for (MultipartFile file: files) {
//            service.saveFile(file, id);
//        }
//        return "redirect:/";
//    }

    @PostMapping(value = "/customers/{id}/files",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<String> UploadMultipleFiles(@RequestParam MultipartFile file , @PathVariable Long id) {
        service.saveFile(file, id);
        return ResponseEntity.ok("Success " + file.getOriginalFilename());
    }

    @GetMapping("/customers/{cId}/files/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable("cId") Long customerId, @PathVariable("id") Long id) {
        File file = service.getFile(customerId, id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getDocType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+file.getDocName()+"\"")
                .body(new ByteArrayResource(file.getData()));


    }
}
