package my.microservice.videogames.application.queries.services;

import my.microservice.videogames.application.queries.dtos.DeveloperQuery;
import my.microservice.videogames.application.queries.services.abstractions.IDeveloperQueryService;
import my.microservice.videogames.infrastructure.repositories.queries.IDeveloperQueryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeveloperQueryService implements IDeveloperQueryService {
    private final IDeveloperQueryRepository queryRepository;

    public DeveloperQueryService(IDeveloperQueryRepository repository) {
        this.queryRepository = repository;
    }

    @Override
    public List<DeveloperQuery> get() {
        List<DeveloperQuery> queries = queryRepository.findAll();
        return queries;
    }

    @Override
    public Optional<DeveloperQuery> getById(Long id) {
        return queryRepository.findById(id);
    }

}
