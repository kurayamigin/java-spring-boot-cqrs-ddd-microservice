package my.microservice.videogames.infrastructure.mappers;

import my.artifacts.models.abstractions.IEntity;
import my.microservice.videogames.application.commands.dtos.ICommand;
import my.microservice.videogames.application.queries.dtos.IQuery;
import org.mapstruct.MappingTarget;

public interface IEntityPatcher<
        TEntity extends IEntity<TKey>,
        TKey,
        TCommand extends ICommand,
        TQuery extends IQuery<TKey>> {
    void patch(TCommand command, @MappingTarget TEntity entity);
    void patch(TQuery query, @MappingTarget TEntity entity);

}
