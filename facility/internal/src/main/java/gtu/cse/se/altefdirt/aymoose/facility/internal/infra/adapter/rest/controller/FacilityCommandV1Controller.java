package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.rest.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.CreateAmenity;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.CreateCity;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.CreateDistrict;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.CreateFacility;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.DeleteAmenity;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.DeleteCity;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.DeleteDistrict;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.DeleteFacility;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.UpdateFacility;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Amenity;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.City;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.District;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Facility;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.rest.dto.CreateAmenityRequestDTO;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.rest.dto.CreateCityRequestDTO;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.rest.dto.CreateDistrictRequestDTO;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.rest.dto.CreateFacilityRequestDTO;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.rest.dto.UpdateFacilityRequestDTO;
import gtu.cse.se.altefdirt.aymoose.core.application.CommandRunner;
import gtu.cse.se.altefdirt.aymoose.shared.api.rest.version.ApiVersionV1;
import gtu.cse.se.altefdirt.aymoose.shared.application.Response;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@RestController
@ApiVersionV1
@RequiredArgsConstructor
class FacilityCommandV1Controller {

    private static final class Parameter {
        private static final String ID = "id";
    }

    private final CommandRunner runner;

    @PostMapping("/facilities")
    public Response<UUID> createFacility(@RequestPart("images") List<MultipartFile> images,
            @RequestPart("data") CreateFacilityRequestDTO request) {
        Facility facility = runner.run(new CreateFacility(
                request.userId(),
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
        return Response.success(facility.id().value(), "Facility created successfully");
    }

    @PostMapping("/amenities")
    public Response<UUID> createAmenity(@RequestPart("image") MultipartFile image,
            @RequestPart("data") CreateAmenityRequestDTO request) {
        Amenity amenity = runner.run(new CreateAmenity(
                image,
                request.name()));
        return Response.success(amenity.id().value(), "Amenity created successfully");
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

    @DeleteMapping("/cities/district/{id}")
    public Response<Long> deleteDistrict(@PathVariable(Parameter.ID) Long id) {
        Long districtId = runner.run(new DeleteDistrict(id));
        return Response.success(districtId, "District created successfully");
    }

    @DeleteMapping("/cities/{id}")
    public Response<Long> deleteCity(@PathVariable(Parameter.ID) Long id) {
        Long cityId = runner.run(new DeleteCity(id));
        return Response.success(cityId, "City deleted created successfully");
    }

    @DeleteMapping("/facilities/{id}")
    public Response<UUID> deleteFacility(@PathVariable(Parameter.ID) UUID id) {
        AggregateId facilityId = runner.run(new DeleteFacility(id));
        return Response.success(facilityId.value(), "Facility deleted successfully");
    }

    @PatchMapping("/facilities/{id}")
    public Response<UUID> updateFacility(@PathVariable(Parameter.ID) UUID id,
            @RequestPart("images") List<MultipartFile> images,
            @RequestPart("data") UpdateFacilityRequestDTO request) {
        AggregateId facilityId = runner.run(new UpdateFacility(
                id,
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
                request.deletedImages(),
                images));
        return Response.success(facilityId.value(), "Facility updated successfully");
    }

    @PostMapping("/amenities/{id}")
    public Response<UUID> createAmenity(@PathVariable(Parameter.ID) UUID id) {
        AggregateId amenityId = runner.run(new DeleteAmenity(id));
        return Response.success(amenityId.value(), "Amenity deleted successfully");
    }

}
