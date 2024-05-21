package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.entities.Image;
import org.example.repositories.ImageRepository;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageRepository imageRepository;
    @GetMapping("/image/{id}")
    public ResponseEntity<?> getImageById(@PathVariable Long id) {
        Image image = imageRepository.findById(id).orElse(null);
        return ResponseEntity.ok()
                .header("fileName", image.getFileName())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }

}
