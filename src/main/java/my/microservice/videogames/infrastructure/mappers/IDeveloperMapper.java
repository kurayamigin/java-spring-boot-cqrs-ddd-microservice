package my.microservice.videogames.infrastructure.mappers;

import my.microservice.videogames.application.commands.dtos.CompanyCommand;
import my.microservice.videogames.application.commands.dtos.DeveloperCommand;
import my.microservice.videogames.application.queries.dtos.CompanyQuery;
import my.microservice.videogames.application.queries.dtos.DeveloperQuery;
import my.microservice.videogames.domain.models.Company;
import my.microservice.videogames.domain.models.Developer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IDeveloperMapper extends
        IEntityMapper<Developer, Long, DeveloperCommand, DeveloperQuery>,
        IEntityPatcher<Developer, Long, DeveloperCommand, DeveloperQuery>
{
}
