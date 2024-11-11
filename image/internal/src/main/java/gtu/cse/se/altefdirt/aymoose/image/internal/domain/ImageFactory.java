package gtu.cse.se.altefdirt.aymoose.image.internal.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

@Component
public class ImageFactory {
    
    private String imagesPath = "/image/internal/src/main/resources/images/";

    private final Path IMAGE_STORAGE_PATH = Paths.get(imagesPath).toAbsolutePath().normalize();
    
    public Image create(AggregateId relationId, MultipartFile file, String extension) {

        return new Image(AggregateId.generate(), relationId, file, extension);
    }

    public Image load(AggregateId id, AggregateId relationId, String imageId, String extension) {
        String dot = ".";
        System.out.println(this.IMAGE_STORAGE_PATH.toAbsolutePath().normalize());
        Path imagePath = IMAGE_STORAGE_PATH.resolve(imageId + dot + extension);

        if (!Files.exists(imagePath)) {
            throw new RuntimeException("File not found: " + imageId);
        }

        try {          
            byte[] fileContent = Files.readAllBytes(imagePath);

            MultipartFile file = new CustomMultipartFile(imageId, "image/" + extension, fileContent);

            return new Image(id, relationId, file, extension);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load the file: " + imageId, e);
        }
    }

}