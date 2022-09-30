package my.microservice.videogames.infrastructure.mappers;

import my.artifacts.models.abstractions.IEntity;
import my.microservice.videogames.application.commands.dtos.GameCommand;
import my.microservice.videogames.application.commands.dtos.ICommand;
import my.microservice.videogames.application.queries.dtos.GameQuery;
import my.microservice.videogames.application.queries.dtos.IQuery;
import my.microservice.videogames.domain.models.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

public interface IEntityMapper<
        TEntity extends IEntity<TKey>,
        TKey,
        TCommand extends ICommand,
        TQuery extends IQuery<TKey>> {

    TQuery toQuery(TEntity entity);
    TCommand toCommand(TEntity entity);
    TEntity toEntity(TCommand command);

    List<TQuery> toQuery(List<TEntity> entity);
    List<TCommand> toCommand(List<TEntity> entity);
    List<TEntity> toEntity(List<TCommand> command);

}
