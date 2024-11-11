package gtu.cse.se.altefdirt.aymoose.favorites.api.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gtu.cse.se.altefdirt.aymoose.favorites.internal.application.command.CreateFavorites;
import gtu.cse.se.altefdirt.aymoose.favorites.internal.application.model.FavoritesView;
import gtu.cse.se.altefdirt.aymoose.core.application.CommandRunner;
import gtu.cse.se.altefdirt.aymoose.favorites.api.rest.dto.CreateFavoritesRequestDTO;
import gtu.cse.se.altefdirt.aymoose.shared.api.rest.version.ApiVersionV1;
import gtu.cse.se.altefdirt.aymoose.shared.application.Response;
import lombok.RequiredArgsConstructor;

@RestController
@ApiVersionV1
@RequiredArgsConstructor
class FavoritesCommandV1Controller {

    private final CommandRunner runner;

    @PostMapping("/favorites")
    public Response<FavoritesView> createFavorite(@RequestBody CreateFavoritesRequestDTO request) {
        FavoritesView view = runner.run(new CreateFavorites(
                request.userId(),
                request.facilityId()
        ));
        return Response.success(view, "Favorite added successfully");
    }
}

