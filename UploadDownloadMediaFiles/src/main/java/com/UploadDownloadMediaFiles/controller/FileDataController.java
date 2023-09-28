package com.UploadDownloadMediaFiles.controller;

import com.UploadDownloadMediaFiles.service.FileDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileDataController {
    @Autowired
    private FileDataService fileDataService;

    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException{
        String uploadImage =
                 fileDataService.uploadImage(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("/image/{filename}")
    public ResponseEntity<?> downloadImage(@PathVariable("filename") String fileName) throws IOException{
       byte[] imageData= fileDataService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

 }
