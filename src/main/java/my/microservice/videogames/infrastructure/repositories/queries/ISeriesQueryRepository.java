package my.microservice.videogames.infrastructure.repositories.queries;

import my.microservice.videogames.application.queries.dtos.SeriesQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISeriesQueryRepository
        extends
        IQueryRepository<SeriesQuery, Long>,
        MongoRepository<SeriesQuery, Long> {
}
