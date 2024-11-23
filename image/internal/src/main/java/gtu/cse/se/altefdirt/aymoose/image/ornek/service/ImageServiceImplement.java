package gtu.cse.se.altefdirt.aymoose.image.ornek.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;

@Service
public class ImageServiceImplement implements ImageServicexd {

    private final Path fileStorageLocation = Paths.get(".").normalize();

    public ImageServiceImplement() {
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not create the directory where uploaded files will be stored.", e);
        }
    }

    @Override
    public String storeImage(MultipartFile file) {
        // Maximum file size (in bytes) - e.g., 5MB
        long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5 MB
        // Maximum width and height in pixels
        int MAX_WIDTH = 1920;
        int MAX_HEIGHT = 1080;

        // Get the file name
        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null) {
            throw new RuntimeException("Invalid file name");
        }

        // Check for file size
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new RuntimeException("File is too large. Maximum size allowed is 5MB.");
        }

        // Check for image dimensions (width and height)
        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image != null) {
                int width = image.getWidth();
                int height = image.getHeight();

                if (width > MAX_WIDTH || height > MAX_HEIGHT) {
                    throw new RuntimeException("Image dimensions are too large. Max dimensions are " + MAX_WIDTH + "x" + MAX_HEIGHT);
                }
            } else {
                throw new RuntimeException("The file is not a valid image.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading the image file.", e);
        }

        // Custom directory to store the file
        String uploadDir = "custom/directory/path/";  // Path where you want to store the image
        String newFileName = "new_" + originalFileName;  // New file name (you can customize this as needed)

        Path targetLocation = Paths.get(uploadDir + newFileName);  // Combine the directory and new file name

        // Store the file
        try {
            // Ensure the directory exists, or create it
            Files.createDirectories(Paths.get(uploadDir));

            // Copy the file to the target location with the new file name
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            // Return the new file name (or the path if needed)
            return newFileName;
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file. Please try again!", ex);
        }
    }

    @Override
    public Resource loadImageAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("File not found: " + fileName);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("File not found: " + fileName, e);
        }
    }
}