package gtu.cse.se.altefdirt.aymoose.image.ornek;

import java.nio.file.Files;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.image.ornek.service.ImageServicexd;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final ImageServicexd imageService;

    public ImageController(ImageServicexd imageService) {
        this.imageService = imageService;
    }

    // Endpoint to handle image upload
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file) {
        String fileName = imageService.storeImage(file);
        String fileDownloadUri = "/api/images/download/" + fileName;
        return ResponseEntity.ok(fileDownloadUri);
    }

    @PostMapping("/upload/string")
    public ResponseEntity<String> uploadImageText(@RequestParam("image") MultipartFile file , @RequestParam("description") String text) {
        String fileName = imageService.storeImage(file);
        String fileDownloadUri = text + fileName;
        return ResponseEntity.ok(fileDownloadUri);
    }

    // Endpoint to download the image as a resource
    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String fileName) {
        Resource resource = imageService.loadImageAsResource(fileName);

        String contentType;
        try {
            contentType = Files.probeContentType(resource.getFile().toPath());
        } catch (Exception e) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}