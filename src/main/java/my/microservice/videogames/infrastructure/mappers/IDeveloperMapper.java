package my.microservice.videogames.infrastructure.mappers;

import my.microservice.videogames.application.commands.dtos.CompanyCommand;
import my.microservice.videogames.application.commands.dtos.DeveloperCommand;
import my.microservice.videogames.application.commands.dtos.GameCommand;
import my.microservice.videogames.application.queries.dtos.CompanyQuery;
import my.microservice.videogames.application.queries.dtos.DeveloperQuery;
import my.microservice.videogames.domain.models.Company;
import my.microservice.videogames.domain.models.Developer;
import my.microservice.videogames.domain.models.Game;
import my.microservice.videogames.domain.models.Publisher;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IDeveloperMapper extends
        IEntityMapper<Developer, Long, DeveloperCommand, DeveloperQuery>,
        IEntityPatcher<Developer, Long, DeveloperCommand, DeveloperQuery>
{
    //This method prevents patch updates errors by deleting previous relation.
    @BeforeMapping
    default void cleanRelations(DeveloperCommand command, @MappingTarget Developer entity) {
        if (command.getParentCompanyId() != null)
            entity.setParentCompany(new Company());
    }

    @Override
    @Mapping(source = "parentCompanyId", target = "parentCompany.id")
    Developer toEntity(DeveloperCommand command);

    @Override
    @Mapping(source = "parentCompanyId", target = "parentCompany.id")
    void patch(DeveloperCommand command, @MappingTarget Developer entity);
}
