package my.microservice.videogames.infrastructure.repositories.commands;

import my.microservice.videogames.domain.models.Series;
import org.springframework.stereotype.Repository;

@Repository
public interface ISeriesRepository extends ICommandRepository<Series, Long> {
}
