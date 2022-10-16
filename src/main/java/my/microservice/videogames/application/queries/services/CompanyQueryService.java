package my.microservice.videogames.application.queries.services;

import my.microservice.videogames.application.queries.dtos.CompanyQuery;
import my.microservice.videogames.application.queries.services.abstractions.ICompanyQueryService;
import my.microservice.videogames.infrastructure.repositories.queries.ICompanyQueryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyQueryService implements ICompanyQueryService {
    private final ICompanyQueryRepository queryRepository;

    public CompanyQueryService(ICompanyQueryRepository repository) {
        this.queryRepository = repository;
    }

    @Override
    public List<CompanyQuery> get() {
        return queryRepository.findAll();
    }

    @Override
    public Optional<CompanyQuery> getById(Long id) {
        return queryRepository.findById(id);
    }

}
