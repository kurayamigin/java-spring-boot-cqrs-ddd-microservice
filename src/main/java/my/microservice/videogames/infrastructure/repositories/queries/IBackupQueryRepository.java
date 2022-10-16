package my.microservice.videogames.infrastructure.repositories.queries;

import my.microservice.videogames.application.queries.dtos.BackupQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBackupQueryRepository extends MongoRepository<BackupQuery, Long> {
}
