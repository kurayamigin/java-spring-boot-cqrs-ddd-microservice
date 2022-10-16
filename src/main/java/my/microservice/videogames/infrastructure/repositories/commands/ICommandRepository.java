package my.microservice.videogames.infrastructure.repositories.commands;

import my.artifacts.models.abstractions.IEntity;
import org.springframework.data.repository.CrudRepository;

public interface ICommandRepository<TEntity extends IEntity<TKey>, TKey>
        extends CrudRepository<TEntity, TKey>{
}
