package gtu.cse.se.altefdirt.aymoose.account.internal.infra.adapter.rest.dto;

import org.springframework.web.multipart.MultipartFile;

public record UpdateProfilePictureRequestDTO(
        String userId,
        MultipartFile image) {
    }