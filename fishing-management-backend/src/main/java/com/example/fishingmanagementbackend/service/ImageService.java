package com.example.fishingmanagementbackend.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

    public static final String IMAGES_FOLDER = System.getProperty("user.dir") + "/src/main/resources/static";
    
    
    public String storeImage(MultipartFile image) throws IOException {
        
        StringBuilder builder = new StringBuilder();
        Path fileName = Paths.get(IMAGES_FOLDER, image.getOriginalFilename());
        builder.append(image.getOriginalFilename());
  
        Files.write(fileName, image.getBytes());
        System.out.println(fileName.toString());
        
        return "http://localhost:8080/" + image.getOriginalFilename();
    }
}
