package my.microservice.videogames.infrastructure.seeding;

import my.artifacts.models.abstractions.IEntity;
import my.microservice.videogames.cross_cutting.utils.CsvBeanReader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

public abstract class EntitySeeder<
        TEntity extends IEntity<TKey>,
        TKey>
        implements ISeeder {

    protected final String root = "src/main/java/my/microservice/videogames/infrastructure/seeding/seeds";
    protected final File file;
    protected CrudRepository<TEntity, TKey> repository;

    private final Class<TEntity> type;

    public EntitySeeder(String filename, Class<TEntity> type) {
        this.file = new File(Paths.get(root, filename).toString());
        this.type = type;
    }

    @Override
    @Transactional
    public void seed() {
        List<TEntity> beans = CsvBeanReader.readCsv(file.toPath(), type);
        seedEntities(beans);
    }

    private void seedEntities(List<TEntity> beans) {
        repository.saveAll(beans);
    }
}
