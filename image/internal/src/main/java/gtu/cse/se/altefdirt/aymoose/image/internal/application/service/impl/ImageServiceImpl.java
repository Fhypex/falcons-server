package gtu.cse.se.altefdirt.aymoose.image.internal.application.service.impl;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.image.internal.application.model.ImageView;
import gtu.cse.se.altefdirt.aymoose.image.internal.application.service.ImageService;
import gtu.cse.se.altefdirt.aymoose.image.internal.domain.Image;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class ImageServiceImpl implements ImageService {
    
    private String imagesPath = "/image/internal/src/main/resources/images/";

    private final Path fileStorageLocation = Paths.get(imagesPath).toAbsolutePath().normalize();

    
    @Override
    public ImageView denormalize(Image image) 
    {       
        return ImageView.builder()
            .id(image.id().value())
            .relationId(image.getRelationId().value())
            .file(image.getFile())
            .extension(image.getExtension())
            .build();
    }
    @Override
    public String storeImage(Image image) {
        long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5 MB
        int MAX_WIDTH = 1920;
        int MAX_HEIGHT = 1080;
        System.out.println(image.getFile().getOriginalFilename());
        String originalFileName = image.getFile().getOriginalFilename();
        if (originalFileName == null) {
            throw new RuntimeException("Invalid file name");
        }

        if (image.getFile().getSize() > MAX_FILE_SIZE) {
            throw new RuntimeException("File is too large. Maximum size allowed is 5MB.");
        }

        try {
            BufferedImage bufferedImage = ImageIO.read(image.getFile().getInputStream());
            if (bufferedImage != null) {
                int width = bufferedImage.getWidth();
                int height = bufferedImage.getHeight();
                System.out.println(width + " " + height);
                if (width > MAX_WIDTH || height > MAX_HEIGHT) {
                    throw new RuntimeException("Image dimensions are too large. Max dimensions are " + MAX_WIDTH + "x" + MAX_HEIGHT);
                }
            } else {
                throw new RuntimeException("The file is not a valid image.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading the image file.", e);
        }
       
        String uploadDir = "/image/internal/src/main/resources/images/"; 
        String twoSlash = "\\" ;
        String newFileName = image.id().value(); 
        String dot = ".";
        System.out.println(newFileName);
        Path targetLocation = Paths.get(uploadDir + twoSlash + newFileName + dot + image.getExtension());
        System.out.println(targetLocation);
        try {
            
            Files.createDirectories(Paths.get(uploadDir));
            Files.copy(image.getFile().getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return newFileName;
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file. Please try again!", ex);
        }
    }
    @Override
    public Resource loadImageAsResource(String fileName , String extension) {
        String dot = ".";
        fileName = fileName + dot + extension;
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
