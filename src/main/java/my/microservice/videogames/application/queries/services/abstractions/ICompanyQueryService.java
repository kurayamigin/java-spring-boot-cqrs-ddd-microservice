package my.microservice.videogames.application.queries.services.abstractions;

import my.microservice.videogames.application.queries.dtos.CompanyQuery;

import java.util.List;
import java.util.Optional;

public interface ICompanyQueryService {
    List<CompanyQuery> get();
    Optional<CompanyQuery> getById(Long id);
}
