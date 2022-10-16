package my.microservice.videogames.application.queries.services;

import my.microservice.videogames.application.queries.dtos.SeriesQuery;
import my.microservice.videogames.application.queries.services.abstractions.ISeriesQueryService;
import my.microservice.videogames.infrastructure.repositories.queries.ISeriesQueryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeriesQueryService implements ISeriesQueryService {
    private final ISeriesQueryRepository queryRepository;

    public SeriesQueryService(ISeriesQueryRepository repository) {
        this.queryRepository = repository;
    }

    @Override
    public List<SeriesQuery> get() {
        List<SeriesQuery> queries = queryRepository.findAll();
        return queries;
    }

    @Override
    public Optional<SeriesQuery> getById(Long id) {
        return queryRepository.findById(id);
    }

}
