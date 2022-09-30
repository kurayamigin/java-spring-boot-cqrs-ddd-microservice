package my.microservice.videogames.infrastructure.mappers;

import my.microservice.videogames.application.commands.dtos.ICommand;
import my.microservice.videogames.application.commands.dtos.PublisherCommand;
import my.microservice.videogames.application.queries.dtos.PublisherQuery;
import my.microservice.videogames.domain.models.Publisher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IPublisherMapper extends
        IEntityMapper<Publisher, Long, PublisherCommand, PublisherQuery>,
        IEntityPatcher<Publisher, Long, PublisherCommand, PublisherQuery>
{
}