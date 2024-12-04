package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command;

import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import jakarta.validation.constraints.NotNull;

public record UpdateFacility(
        @NotNull UUID id,
        String phoneNumber,
        String name,
        String description,
        Long districtId,
        String fullAddress,
        String location,
        String contactDetails,
        Integer openTime,
        Integer closeTime,
        List<String> amenities,
        Boolean isActive,
        List<String> deletedImages,
        List<MultipartFile> newImages) implements Command {
}
