package my.microservice.videogames.application.queries.services;

import my.microservice.videogames.application.queries.dtos.PublisherQuery;
import my.microservice.videogames.application.queries.services.abstractions.IPublisherQueryService;
import my.microservice.videogames.infrastructure.repositories.queries.IPublisherQueryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherQueryService implements IPublisherQueryService {
    private final IPublisherQueryRepository queryRepository;

    public PublisherQueryService(IPublisherQueryRepository repository) {
        this.queryRepository = repository;
    }

    @Override
    public List<PublisherQuery> get() {
        List<PublisherQuery> queries = queryRepository.findAll();
        return queries;
    }

    @Override
    public Optional<PublisherQuery> getById(Long id) {
        return queryRepository.findById(id);
    }

}
