package gtu.cse.se.altefdirt.aymoose.image.ornek.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageServicexd {
    String storeImage(MultipartFile file);
    Resource loadImageAsResource(String fileName);
}