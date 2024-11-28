package gtu.cse.se.altefdirt.aymoose.image.api.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gtu.cse.se.altefdirt.aymoose.image.internal.domain.Image;
import gtu.cse.se.altefdirt.aymoose.image.internal.domain.ImageRepository;
import gtu.cse.se.altefdirt.aymoose.image.api.rest.dto.ImageResponseDTO;
import gtu.cse.se.altefdirt.aymoose.shared.api.rest.version.ApiVersionV1;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@RestController
@ApiVersionV1
@RequiredArgsConstructor
class ImageQueryV1Controller {

    private final ImageRepository imageRepository;

    private static final class Parameter {
        private static final String ID = "id";
        private static final String RELATION = "relation";
    }

    @GetMapping(value = "/image/{id}")
    ImageResponseDTO getImageById(@PathVariable(Parameter.ID) String id) {
        Image image = imageRepository.findById(AggregateId.from(id)).get();
        return ImageResponseDTO.fromDomain(image);
    }

    @GetMapping(value = "/images", params = Parameter.RELATION)
    List<ImageResponseDTO> getImagesByRelationId(@RequestParam(Parameter.RELATION) String relationId) {
        List<Image> images = imageRepository.findByRelationId(AggregateId.from(relationId));
        return images.stream().map(ImageResponseDTO::fromDomain).collect(Collectors.toUnmodifiableList());
    }
}
