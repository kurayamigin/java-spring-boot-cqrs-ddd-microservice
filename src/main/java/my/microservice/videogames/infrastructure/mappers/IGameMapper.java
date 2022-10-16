package my.microservice.videogames.infrastructure.mappers;

import my.microservice.videogames.application.commands.dtos.GameCommand;
import my.microservice.videogames.application.queries.dtos.GameQuery;
import my.microservice.videogames.domain.models.Game;
import my.microservice.videogames.domain.models.Genre;
import my.microservice.videogames.domain.models.Publisher;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {IGenreMapper.class})
public interface IGameMapper extends
        IEntityMapper<Game, Long, GameCommand, GameQuery>,
        IEntityPatcher<Game, Long, GameCommand, GameQuery>{

    //This method prevents patch updates errors by deleting previous relation.
    @BeforeMapping
    default void cleanRelations(GameCommand command, @MappingTarget Game entity) {
        if (command.getPublisherId() != null)
            entity.setPublisher( new Publisher());
    }

    @Override
    @Mapping(source = "publisherId", target = "publisher.id")
    @Mapping(source = "companyId", target = "company.id")
    @Mapping(source = "developerId", target = "developer.id")
    @Mapping(source = "genreIds", target = "genres")
    Game toEntity(GameCommand command);

    @Override
    @Mapping(source = "publisherId", target = "publisher.id")
    void patch(GameCommand command, @MappingTarget Game entity);


    default Game mapId(Long id) {
        Game game = new Game();
        game.setId(id);
        return game;
    }
    List<Game> map(List<Long> value);
}
