package my.microservice.videogames.infrastructure.aggregators.abstractions;

import my.artifacts.models.abstractions.IEntity;

import java.util.List;

public interface IService<TEntity extends IEntity<TKey>, TKey> {

    TEntity create(TEntity entity);

    void delete(TKey id);

    void update(TEntity game);

    List<TEntity> get();
    List<TEntity> get(Iterable<TKey> ids);
    TEntity get(TKey id);
}
