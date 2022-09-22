package com.jordan.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.jordan.service.CloudinaryService;

@RestController
public class CloudinaryController {
	private final CloudinaryService CloudinaryService = new CloudinaryService();

	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile f) {
        String url = CloudinaryService.uploadFile(f);
        return "File uploaded successfully: File path :  " + url;
    }


}
