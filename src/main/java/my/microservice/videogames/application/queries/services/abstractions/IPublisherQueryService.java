package my.microservice.videogames.application.queries.services.abstractions;

import my.microservice.videogames.application.queries.dtos.PublisherQuery;

import java.util.List;
import java.util.Optional;

public interface IPublisherQueryService {
    List<PublisherQuery> get();
    Optional<PublisherQuery> getById(Long id);
}
