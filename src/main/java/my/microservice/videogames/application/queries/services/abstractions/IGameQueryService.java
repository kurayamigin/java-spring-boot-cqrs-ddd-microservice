package my.microservice.videogames.application.queries.services.abstractions;

import my.microservice.videogames.application.queries.dtos.GameQuery;

import java.util.List;
import java.util.Optional;

public interface IGameQueryService {
    List<GameQuery> get();
    Optional<GameQuery> getById(Long id);
}
