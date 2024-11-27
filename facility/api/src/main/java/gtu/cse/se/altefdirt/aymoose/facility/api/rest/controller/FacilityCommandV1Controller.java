package gtu.cse.se.altefdirt.aymoose.facility.api.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.CreateAmenity;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.CreateCity;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.CreateDistrict;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.CreateFacility;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Amenity;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.City;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.District;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Facility;
import gtu.cse.se.altefdirt.aymoose.core.application.CommandRunner;
import gtu.cse.se.altefdirt.aymoose.facility.api.rest.dto.CreateAmenityRequestDTO;
import gtu.cse.se.altefdirt.aymoose.facility.api.rest.dto.CreateCityRequestDTO;
import gtu.cse.se.altefdirt.aymoose.facility.api.rest.dto.CreateDistrictRequestDTO;
import gtu.cse.se.altefdirt.aymoose.facility.api.rest.dto.CreateFacilityRequestDTO;
import gtu.cse.se.altefdirt.aymoose.shared.api.rest.version.ApiVersionV1;
import gtu.cse.se.altefdirt.aymoose.shared.application.Response;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@RestController
@ApiVersionV1
@RequiredArgsConstructor
class FacilityCommandV1Controller {

    private final CommandRunner runner;

    @PostMapping("/facilities")
    public Response<AggregateId> createFacility(@RequestPart("images") List<MultipartFile> images,
            @RequestPart("data") CreateFacilityRequestDTO request) {
        Facility facility = runner.run(new CreateFacility(
                request.ownerId(),
                request.phoneNumber(),
                request.name(),
                request.description(),
                request.districtId(),
                request.fullAddress(),
                request.location(),
                request.contactDetails(),
                request.openTime(),
                request.closeTime(),
                request.amenityIds(),
                request.isActive(),
                images));
        return Response.success(facility.id(), "Facility created successfully");
    }

    @PostMapping("/amenities")
    public Response<AggregateId> createAmenity(@RequestPart("image") MultipartFile image,
            @RequestPart("data") CreateAmenityRequestDTO request) {
        Amenity amenity = runner.run(new CreateAmenity(
                image,
                request.name()));
        return Response.success(amenity.id(), "Amenity created successfully");
    }

    @PostMapping("/cities")
    public Response<Long> createCity(@RequestBody CreateCityRequestDTO request) {
        City city = runner.run(new CreateCity(
                request.name()));
        return Response.success(city.id(), "City created successfully");
    }

    @PostMapping("/cities/districts")
    public Response<Long> createDistrict(@RequestBody CreateDistrictRequestDTO request) {
        District district = runner.run(new CreateDistrict(request.cityId(),
                request.name()));
        return Response.success(district.id(), "District created successfully");
    }

}
