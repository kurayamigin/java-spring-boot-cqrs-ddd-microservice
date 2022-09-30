package my.microservice.videogames.infrastructure.mappers;

import my.microservice.videogames.application.commands.dtos.CompanyCommand;
import my.microservice.videogames.application.commands.dtos.SeriesCommand;
import my.microservice.videogames.application.queries.dtos.CompanyQuery;
import my.microservice.videogames.application.queries.dtos.SeriesQuery;
import my.microservice.videogames.domain.models.Company;
import my.microservice.videogames.domain.models.Serie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISeriesMapper extends
        IEntityMapper<Serie, Long, SeriesCommand, SeriesQuery>,
        IEntityPatcher<Serie, Long, SeriesCommand, SeriesQuery>
{
}
