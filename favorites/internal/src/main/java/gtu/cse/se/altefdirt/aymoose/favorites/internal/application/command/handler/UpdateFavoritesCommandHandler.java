package gtu.cse.se.altefdirt.aymoose.favorites.internal.application.command.handler;

import java.util.Optional;

import gtu.cse.se.altefdirt.aymoose.favorites.internal.application.command.UpdateFavorites;
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
public class UpdateFavoritesCommandHandler implements CommandHandler<UpdateFavorites, FavoritesView> {

    private final FavoritesService service;
    private final FavoritesRepository repository;

    @Override
    public FavoritesView handle(UpdateFavorites command) {

        Optional<Favorites> fetch = repository.findById(AggregateId.from(command.id()));

        if (fetch.isEmpty()) {
            throw new IllegalArgumentException("Favorite does not exist");
        }

        Favorites favorite = fetch.get();

        
        favorite.updateDetails(command.userId(), command.facilityId());

        Favorites savedFavorite = repository.save(favorite);

        return service.denormalize(savedFavorite);
    }
}
