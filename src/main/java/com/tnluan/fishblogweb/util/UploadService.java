package com.tnluan.fishblogweb.util;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class UploadService {

    public String UploadImage(MultipartFile imageFile, String folder) {
        if (imageFile.isEmpty()) {
            return null;
        }

        try {
            // Combine base directory with specific folder (e.g., kindFish, fishBlog, etc.)
            Path uploadPath = Paths.get(Constant.baseUploadDir + folder);

            // Create the upload directory if it does not exist
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = "";
            if (folder.equals(Constant.uploadImageFishBlogDir)) {
                fileName = imageFile.getOriginalFilename();
            } else {
                // Generate a unique filename using the current timestamp to avoid collisions
                fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
            }
            Path filePath = uploadPath.resolve(fileName);

            // Save the file to the target location, replacing if a file with same name exists
            Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Return a relative URL that can be used in HTML templates
            return "/upload/" + folder + "/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Could not store image. Error: " + e.getMessage());
        }
    }

    public void deleteImage(String imageUrl) {
        try {
            if (imageUrl == null || imageUrl.isEmpty()) return;

            // Remove "/upload/"
            String relativePath = imageUrl.replaceFirst("/upload/", "");
            // Get relative path to file
            Path filePath = Paths.get(Constant.baseUploadDir).resolve(relativePath);

            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
