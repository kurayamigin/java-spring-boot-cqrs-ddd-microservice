package my.microservice.videogames.infrastructure.mappers;

import my.microservice.videogames.application.commands.dtos.CompanyCommand;
import my.microservice.videogames.application.commands.dtos.PublisherCommand;
import my.microservice.videogames.application.queries.dtos.CompanyQuery;
import my.microservice.videogames.application.queries.dtos.PublisherQuery;
import my.microservice.videogames.domain.models.Company;
import my.microservice.videogames.domain.models.Publisher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICompanyMapper extends
        IEntityMapper<Company, Long, CompanyCommand, CompanyQuery>,
        IEntityPatcher<Company, Long, CompanyCommand, CompanyQuery>
{
}
