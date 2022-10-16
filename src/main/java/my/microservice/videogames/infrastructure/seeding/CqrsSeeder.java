package my.microservice.videogames.infrastructure.seeding;

import my.artifacts.abstractions.ICommand;
import my.artifacts.abstractions.IQuery;
import my.artifacts.models.abstractions.IEntity;
import my.microservice.videogames.cross_cutting.utils.CsvBeanReader;
import my.microservice.videogames.infrastructure.mappers.IEntityMapper;
import my.microservice.videogames.infrastructure.repositories.commands.ICommandRepository;
import my.microservice.videogames.infrastructure.repositories.queries.IQueryRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

public abstract class CqrsSeeder<
        TEntity extends IEntity<TKey>,
        TQuery extends IQuery<TKey>,
        TKey>
        implements ISeeder {

    protected final String root = "src/main/java/my/microservice/videogames/infrastructure/seeding/seeds";
    protected final File file;
    protected ICommandRepository<TEntity, TKey> repository;
    protected IQueryRepository<TQuery, TKey> queryRepository;

    private final Class<TEntity> type;
    private final IEntityMapper<TEntity, TKey, ? extends ICommand, TQuery> mapper;

    public CqrsSeeder(String filename, Class<TEntity> type,
                      IEntityMapper<TEntity, TKey, ? extends ICommand, TQuery> mapper) {
        this.file = new File(Paths.get(root, filename).toString());
        this.type = type;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public void seed() {
        List<TEntity> beans = CsvBeanReader.readCsv(file.toPath(), type);
        seedEntities(beans);
        seedQueries(beans);
    }

    private void seedEntities(List<TEntity> beans) {
        repository.saveAll(beans);
    }

    private void seedQueries(List<TEntity> beans) {
        List<TQuery> queries = mapper.toQuery(beans);
        queryRepository.saveAll(queries);
    }
}
