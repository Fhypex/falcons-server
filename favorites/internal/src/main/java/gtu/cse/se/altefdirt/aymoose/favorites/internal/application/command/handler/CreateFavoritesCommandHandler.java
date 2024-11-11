package gtu.cse.se.altefdirt.aymoose.favorites.internal.application.command.handler;

import gtu.cse.se.altefdirt.aymoose.favorites.internal.application.command.CreateFavoritesCommand;
import gtu.cse.se.altefdirt.aymoose.favorites.internal.application.model.FavoriteView;
import gtu.cse.se.altefdirt.aymoose.favorites.internal.application.service.FavoritesService;
import gtu.cse.se.altefdirt.aymoose.favorites.internal.domain.Favorites;
import gtu.cse.se.altefdirt.aymoose.favorites.internal.domain.FavoritesFactory;
import gtu.cse.se.altefdirt.aymoose.favorites.internal.domain.FavoritesRepository;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@RegisterHandler
@RequiredArgsConstructor
public class CreateFavoritesCommandHandler implements CommandHandler<CreateFavoritesCommand, FavoritesView> {

    private final FavoritesFactory factory;
    private final FavoritesService service; 
    private final FavoritesRepository favoritesRepository;

    @Override
    public FavoritesView handle(CreateFavoritesCommand command) {

        
        Favorites favorite = factory.create(
                AggregateId.from(command.userId()),
                AggregateId.from(command.facilityId())
        );

        
        Favorites savedFavorite = favoritesRepository.save(favorite);

        
        return service.denormalize(savedFavorite);
    }
}
