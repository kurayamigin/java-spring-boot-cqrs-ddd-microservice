package my.microservice.videogames.infrastructure.repositories.queries;

import my.microservice.videogames.application.queries.dtos.PublisherQuery;
import my.microservice.videogames.domain.models.Publisher;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IPublisherQueryRepository extends
        IQueryRepository<PublisherQuery, Long>,
        MongoRepository<PublisherQuery, Long> {
}
