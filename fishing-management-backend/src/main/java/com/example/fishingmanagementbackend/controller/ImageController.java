package com.example.fishingmanagementbackend.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.fishingmanagementbackend.service.ImageService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("api/image")
public class ImageController {

    @Autowired
    private ImageService imageService;
    
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
        String imagePath = imageService.storeImage(file);
        ObjectMapper mapper = new ObjectMapper();
        String imagePathJSON = mapper.writeValueAsString(imagePath);
        return ResponseEntity.ok(imagePathJSON);
    }
}
