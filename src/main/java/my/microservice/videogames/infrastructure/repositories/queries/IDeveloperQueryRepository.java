package my.microservice.videogames.infrastructure.repositories.queries;

import my.microservice.videogames.application.queries.dtos.DeveloperQuery;
import my.microservice.videogames.application.queries.dtos.GameQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDeveloperQueryRepository
        extends
        IQueryRepository<DeveloperQuery, Long>,
        MongoRepository<DeveloperQuery, Long> {
}
