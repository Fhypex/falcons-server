package gtu.cse.se.altefdirt.aymoose.image.internal.infra.adapter.rest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.image.internal.application.command.CreateImage;
import gtu.cse.se.altefdirt.aymoose.image.internal.domain.Image;
import gtu.cse.se.altefdirt.aymoose.image.internal.infra.adapter.rest.dto.CreateImageRequestDTO;
import gtu.cse.se.altefdirt.aymoose.core.application.CommandRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import gtu.cse.se.altefdirt.aymoose.shared.application.Response;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class ImageCommandV1Controller {

    private final CommandRunner runner;

    @PostMapping("/images")
    public Response<Image> createImage(@RequestPart("image") MultipartFile file,
            @RequestPart("data") CreateImageRequestDTO createImageRequestDTO) {
        Image image = runner.run(new CreateImage(
                createImageRequestDTO.relationId(),
                file));
        return Response.success(image, "Image created successfully");
    }
}
