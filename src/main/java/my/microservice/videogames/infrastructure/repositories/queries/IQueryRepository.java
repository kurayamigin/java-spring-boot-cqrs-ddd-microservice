package my.microservice.videogames.infrastructure.repositories.queries;

import my.microservice.videogames.application.queries.dtos.IQuery;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IQueryRepository<TQuery extends IQuery<TKey>, TKey>
        extends PagingAndSortingRepository<TQuery, TKey> {
}
