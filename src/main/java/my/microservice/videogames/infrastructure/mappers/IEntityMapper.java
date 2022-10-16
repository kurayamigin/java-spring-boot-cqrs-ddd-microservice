package my.microservice.videogames.infrastructure.mappers;

import my.artifacts.abstractions.ICommand;
import my.artifacts.abstractions.IQuery;
import my.artifacts.models.EntityFactory;
import my.artifacts.models.abstractions.IEntity;
import my.microservice.videogames.domain.models.Genre;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public interface IEntityMapper<
        TEntity extends IEntity<TKey>,
        TKey,
        TCommand extends ICommand,
        TQuery extends IQuery<TKey>> {

    default TEntity genericTypeObject(Supplier<TEntity> supplier) {
        return supplier.get();
    }

    TQuery toQuery(TEntity entity);
    TCommand toCommand(TEntity entity);
    TEntity toEntity(TCommand command);

    List<TQuery> toQuery(List<TEntity> entity);
    List<TCommand> toCommand(List<TEntity> entity);
    List<TEntity> toEntity(List<TCommand> command);

}
