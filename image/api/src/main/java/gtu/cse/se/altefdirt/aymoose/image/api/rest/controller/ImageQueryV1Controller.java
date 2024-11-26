package gtu.cse.se.altefdirt.aymoose.image.api.rest.controller;

import java.nio.file.Files;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.image.internal.application.model.ImageView;
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
        String url = imageRepository.url(image);
        return ImageResponseDTO.fromView(new ImageView(image, url));
    }


    @GetMapping(value = "/images", params = Parameter.RELATION)
    List<ImageResponseDTO> getImagesByRelationId(@RequestParam(Parameter.RELATION) String relationId) {
        List<Image> images = imageRepository.findAllByRelationId(AggregateId.from(relationId));

        return images.stream().map(image -> {
            return ImageResponseDTO.fromView(new ImageView(image, imageRepository.url(image)));
        }).collect(Collectors.toUnmodifiableList());
    }




}
