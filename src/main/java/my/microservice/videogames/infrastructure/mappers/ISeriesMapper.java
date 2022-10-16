package my.microservice.videogames.infrastructure.mappers;

import my.microservice.videogames.application.commands.dtos.GameCommand;
import my.microservice.videogames.application.commands.dtos.SeriesCommand;
import my.microservice.videogames.application.queries.dtos.SeriesQuery;
import my.microservice.videogames.domain.models.Game;
import my.microservice.videogames.domain.models.Series;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IGameMapper.class})
public interface ISeriesMapper extends
        IEntityMapper<Series, Long, SeriesCommand, SeriesQuery>,
        IEntityPatcher<Series, Long, SeriesCommand, SeriesQuery>
{

    @Mapping(source = "gameIds", target = "games")
    Series toEntity(SeriesCommand command);

}
