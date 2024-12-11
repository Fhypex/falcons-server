package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.rest.dto;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.Validate;
import org.springframework.web.multipart.MultipartFile;

public record UpdateCourtRequestDTO(
        UUID id,
        String name,
        String description,
        Integer height,
        Integer width,
        Integer capacity,
        Integer price,
        List<UUID> deletedImages,
        List<MultipartFile> newImages) {

    public UpdateCourtRequestDTO(
            UUID id,
            String name,
            String description,
            Integer height,
            Integer width,
            Integer capacity,
            Integer price,
            List<UUID> deletedImages,
            List<MultipartFile> newImages) {
        Validate.notNull(id, "Court ID cannot be null");
        this.id = id;
        this.name = name;
        this.description = description;
        this.height = height;
        this.width = width;
        this.capacity = capacity;
        this.price = price;
        this.deletedImages = deletedImages;
        this.newImages = newImages;
    }
}