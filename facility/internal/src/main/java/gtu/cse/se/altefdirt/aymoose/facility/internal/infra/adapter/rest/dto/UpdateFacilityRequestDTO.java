package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.rest.dto;

import java.util.List;
import org.apache.commons.lang3.Validate;
import org.springframework.web.multipart.MultipartFile;

public record UpdateFacilityRequestDTO(
        String id,
        String phoneNumber,
        String name,
        String description,
        Long districtId,
        String fullAddress,
        String location,
        String contactDetails,
        int openTime,
        int closeTime,
        List<String> amenityIds,
        boolean isActive,
        List<String> deletedImages,
        List<MultipartFile> newImages) {
    public UpdateFacilityRequestDTO(
            String id,
            String phoneNumber,
            String name,
            String description,
            Long districtId,
            String fullAddress,
            String location,
            String contactDetails,
            int openTime,
            int closeTime,
            List<String> amenityIds,
            boolean isActive,
            List<String> deletedImages,
            List<MultipartFile> newImages) {

        Validate.notNull(id, "Facility ID cannot be null");
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.description = description;
        this.districtId = districtId;
        this.fullAddress = fullAddress;
        this.location = location;
        this.contactDetails = contactDetails;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.amenityIds = amenityIds;
        this.isActive = isActive;
        this.deletedImages = deletedImages;
        this.newImages = newImages;
    }
}
