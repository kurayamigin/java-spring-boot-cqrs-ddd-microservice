package my.microservice.videogames.infrastructure.aggregators.abstractions;

import my.artifacts.models.abstractions.IEntity;
import my.microservice.videogames.cross_cutting.exceptions.EntityNotFoundException;
import my.microservice.videogames.infrastructure.repositories.commands.ICommandRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public abstract class EntityService<TEntity extends IEntity<TKey>, TKey> implements IService<TEntity, TKey> {
    protected Class<TEntity> aClass;
    protected abstract void validate(TEntity entity);
    protected final ICommandRepository<TEntity, TKey> repository;

    protected EntityService(Class<TEntity> aClass, ICommandRepository<TEntity, TKey> repository) {
        this.repository = repository;
        this.aClass = aClass;
    }

    @Override
    public TEntity create(TEntity entity) {
        this.validate(entity);
        repository.save(entity);
        return entity;
    }

    @Override
    public void delete(TKey id) {
        if (!repository.existsById(id)) throw new EntityNotFoundException(aClass, id);
        repository.deleteById(id);
    }

    @Override
    public void update(TEntity game) {
        repository.save(game);
    }

    @Override
    public List<TEntity> get() {
        return (List<TEntity>) repository.findAll();
    }

    @Override
    public List<TEntity> get(Iterable<TKey> ids) {
        return (List<TEntity>) repository.findAllById(ids);
    }

    @Override
    public TEntity get(TKey id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(aClass, id));
    }
}
