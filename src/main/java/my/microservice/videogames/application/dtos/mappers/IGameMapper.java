package my.microservice.videogames.application.dtos.mappers;

import my.microservice.videogames.application.dtos.commands.GameCommand;
import my.microservice.videogames.application.dtos.queries.GameQuery;
import my.microservice.videogames.domain.entities.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IGameMapper {

    GameQuery toQuery(Game entity);

    @Mapping(source = "publisherId", target = "publisher.id")
    Game toEntity(GameCommand command);

    List<GameQuery> toQuery(List<Game> entity);
    List<Game> toEntity(List<GameCommand> command);
}
