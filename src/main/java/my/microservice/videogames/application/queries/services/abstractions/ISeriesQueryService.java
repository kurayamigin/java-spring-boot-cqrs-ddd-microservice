package my.microservice.videogames.application.queries.services.abstractions;

import my.microservice.videogames.application.queries.dtos.SeriesQuery;

import java.util.List;
import java.util.Optional;

public interface ISeriesQueryService {
    List<SeriesQuery> get();
    Optional<SeriesQuery> getById(Long id);
}
