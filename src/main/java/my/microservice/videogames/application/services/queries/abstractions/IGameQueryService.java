package my.microservice.videogames.application.services.queries.abstractions;

import my.microservice.videogames.application.dtos.queries.GameQuery;

import java.util.List;

public interface IGameQueryService {
    List<GameQuery> get();
}
