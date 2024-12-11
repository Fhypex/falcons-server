package gtu.cse.se.altefdirt.aymoose.favorites.internal.application.command.handler;

import java.util.Optional;

import gtu.cse.se.altefdirt.aymoose.favorites.internal.application.command.DeleteFavorites;
import gtu.cse.se.altefdirt.aymoose.favorites.internal.application.model.FavoritesView;
import gtu.cse.se.altefdirt.aymoose.favorites.internal.application.service.FavoritesService;
import gtu.cse.se.altefdirt.aymoose.favorites.internal.domain.Favorites;
import gtu.cse.se.altefdirt.aymoose.favorites.internal.domain.FavoritesRepository;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@RegisterHandler
@RequiredArgsConstructor
public class DeleteFavoritesCommandHandler implements CommandHandler<DeleteFavorites, FavoritesView> {

    private final FavoritesService service;
    private final FavoritesRepository favoritesRepository;

    @Override
    public FavoritesView handle(DeleteFavorites command) {
        Optional<Favorites> fetch = favoritesRepository.findById(AggregateId.fromUUID(command.favoritesId()));
        if (fetch.isEmpty()) {
            throw new IllegalArgumentException("Favorite does not exist");
        }
        Favorites favorite = fetch.get();
        favorite.deactivate();
        return service.denormalize(favoritesRepository.save(favorite));
    }
}
