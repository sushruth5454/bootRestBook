package com.api.book.controllers;

import com.api.book.helper.FileUploadHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class FileUploadController {

    @Autowired
    private FileUploadHelper fileUploadHelper;
    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file){
//        System.out.println(file.getOriginalFilename());
//        System.out.println(file.getSize());
        try {
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Select a file");
            }
            if (!file.getContentType().equals("image/jpeg")) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Only Jpeg Content type are allowed");
            }

            //file upload code
            boolean uploaded = fileUploadHelper.uploadFile(file);
            if (uploaded) {
//                return ResponseEntity.ok("Uploaded");
                return ResponseEntity.ok(ServletUriComponentsBuilder.
                        fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong , Try again");
    }
}
