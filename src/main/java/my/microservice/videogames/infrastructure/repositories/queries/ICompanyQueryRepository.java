package my.microservice.videogames.infrastructure.repositories.queries;

import my.microservice.videogames.application.queries.dtos.CompanyQuery;
import my.microservice.videogames.application.queries.dtos.GameQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompanyQueryRepository
        extends
        IQueryRepository<CompanyQuery, Long>,
        MongoRepository<CompanyQuery, Long> {
}
