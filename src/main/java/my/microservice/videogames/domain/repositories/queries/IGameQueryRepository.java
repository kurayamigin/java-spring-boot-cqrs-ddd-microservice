package my.microservice.videogames.domain.repositories.queries;

import my.microservice.videogames.application.dtos.queries.GameQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGameQueryRepository extends MongoRepository<GameQuery, Long> {
}
