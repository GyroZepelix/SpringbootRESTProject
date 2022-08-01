package com.gjalic.springdgjalic.file;

import com.gjalic.springdgjalic.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ClassEmitter;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class FileController {

    @Autowired
    FileService service;

    @Autowired
    private final CustomerService cService;

    FileController(FileService service) {
        this.service = service;
        this.cService = cService;
    }

    @GetMapping("/customers/files")
    public Iterable<File> getFiles(Model model) {
        return service.findAll();
    }

    @GetMapping("/customers/{id}/files")
    public Iterable<File> getFilesFromOne(@PathVariable Long id) {
        return service.getFilesFromCustomer(id);
    }

    @PostMapping(value = "/customers/{id}/files",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public File UploadFile(@RequestParam MultipartFile file, @PathVariable Long id) {
        try {
            service.save(new File());
        }
        catch (Exception e) {

        }

        return service.saveFile(file, id);

    }

    //----------------------



//    @PostMapping("/customers/{id}/files")
//    public String UploadMultipleFiles(@RequestBody() MultipartFile[] files, @PathVariable Long id) {
//        for (MultipartFile file: files) {
//            service.saveFile(file, id);
//        }
//        return "redirect:/";
//    }

    @PostMapping(value = "/customers/{id}/files",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public File UploadMultipleFiles(@RequestParam MultipartFile file, @PathVariable Long id) {
        File thefile = service.saveFile(file, id);
        return thefile;
    }

    @GetMapping("/customers/{cId}/files/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable("cId") Long customerId, @PathVariable("id") Long id) {
        File file = service.getFile(customerId, id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getDocType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\"" + file.getDocName() + "\"")
                .body(new ByteArrayResource(file.getData()));

    }

}
