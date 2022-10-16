package my.microservice.videogames.application.queries.services.abstractions;

import my.microservice.videogames.application.queries.dtos.DeveloperQuery;

import java.util.List;
import java.util.Optional;

public interface IDeveloperQueryService {
    List<DeveloperQuery> get();
    Optional<DeveloperQuery> getById(Long id);
}
