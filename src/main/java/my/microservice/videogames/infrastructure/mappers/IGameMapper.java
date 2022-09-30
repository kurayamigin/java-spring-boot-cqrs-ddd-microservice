package my.microservice.videogames.infrastructure.mappers;

import my.microservice.videogames.application.commands.dtos.GameCommand;
import my.microservice.videogames.application.queries.dtos.GameQuery;
import my.microservice.videogames.domain.models.Game;
import my.microservice.videogames.domain.models.Publisher;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
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
    Game toEntity(GameCommand command);

    @Override
    @Mapping(source = "publisherId", target = "publisher.id")
    void patch(GameCommand command, @MappingTarget Game entity);
}
