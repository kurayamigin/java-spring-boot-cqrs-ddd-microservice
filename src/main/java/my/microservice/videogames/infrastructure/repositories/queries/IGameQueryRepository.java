package my.microservice.videogames.infrastructure.repositories.queries;

import my.microservice.videogames.application.queries.dtos.GameQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGameQueryRepository
        extends
        IQueryRepository<GameQuery, Long>,
        MongoRepository<GameQuery, Long> {
}
