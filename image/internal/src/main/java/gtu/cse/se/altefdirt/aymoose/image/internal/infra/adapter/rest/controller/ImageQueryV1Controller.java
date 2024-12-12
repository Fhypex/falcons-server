package gtu.cse.se.altefdirt.aymoose.image.internal.infra.adapter.rest.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gtu.cse.se.altefdirt.aymoose.image.internal.domain.Image;
import gtu.cse.se.altefdirt.aymoose.image.internal.domain.ImageRepository;
import gtu.cse.se.altefdirt.aymoose.image.internal.infra.adapter.rest.dto.ImageResponseDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class ImageQueryV1Controller {

    private final ImageRepository imageRepository;

    private static final class Parameter {
        private static final String ID = "id";
        private static final String RELATION = "relation";
    }

    @GetMapping(value = "/image/{id}")
    ImageResponseDTO getImageById(@PathVariable(Parameter.ID) UUID id) {
        Image image = imageRepository.findById(AggregateId.fromUUID(id)).get();
        return ImageResponseDTO.fromDomain(image);
    }

    @GetMapping(value = "/images", params = Parameter.RELATION)
    List<ImageResponseDTO> getImagesByRelationId(@RequestParam(Parameter.RELATION) UUID relationId) {
        List<Image> images = imageRepository.findByRelationId(AggregateId.fromUUID(relationId));
        return images.stream().map(ImageResponseDTO::fromDomain).toList();
    }
}
