package gtu.cse.se.altefdirt.aymoose.facility.api.rest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.CreateAmenity;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.CreateCity;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.CreateDistrict;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.CreateFacility;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.AmenityView;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.CityView;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.FacilityView;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.District;
import gtu.cse.se.altefdirt.aymoose.core.application.CommandRunner;
import gtu.cse.se.altefdirt.aymoose.facility.api.rest.dto.CreateAmenityRequestDTO;
import gtu.cse.se.altefdirt.aymoose.facility.api.rest.dto.CreateCityRequestDTO;
import gtu.cse.se.altefdirt.aymoose.facility.api.rest.dto.CreateDistrictRequestDTO;
import gtu.cse.se.altefdirt.aymoose.facility.api.rest.dto.CreateFacilityRequestDTO;
import gtu.cse.se.altefdirt.aymoose.shared.api.rest.version.ApiVersionV1;
import gtu.cse.se.altefdirt.aymoose.shared.application.Response;
import lombok.RequiredArgsConstructor;

@RestController
@ApiVersionV1
@RequiredArgsConstructor
class FacilityCommandV1Controller {

    private final CommandRunner runner;

    @PostMapping("/facilities")
    public Response<FacilityView> createFacility(@RequestBody CreateFacilityRequestDTO request) {
        FacilityView view = runner.run(new CreateFacility(
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
                request.isActive()));
        return Response.success(view, "Facility created successfully");
    }


    @PostMapping("/amenities")
    public Response<AmenityView> createAmenity(@RequestPart("image") MultipartFile image, 
    @RequestPart("data") CreateAmenityRequestDTO request) {
        AmenityView view = runner.run(new CreateAmenity(
                image,
                request.name()));
        return Response.success(view, "Amenity created successfully");
    }


    @PostMapping("/cities")
    public Response<CityView> createCity(@RequestBody CreateCityRequestDTO request) {
        CityView view = runner.run(new CreateCity(
                request.name()));
        return Response.success(view, "City created successfully");
    }

    @PostMapping("/cities/districts")
    public Response<District> createDistrict(@RequestBody CreateDistrictRequestDTO request) {
        District district = runner.run(new CreateDistrict(request.cityId(),
                request.name()));
        return Response.success(district, "District created successfully");
    }


}
