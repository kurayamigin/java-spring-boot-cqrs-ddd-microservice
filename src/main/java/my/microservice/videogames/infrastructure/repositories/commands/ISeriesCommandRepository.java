package my.microservice.videogames.infrastructure.repositories.commands;

import my.microservice.videogames.domain.models.Game;
import my.microservice.videogames.domain.models.Serie;
import org.springframework.stereotype.Repository;

@Repository
public interface ISeriesCommandRepository extends ICommandRepository<Serie, Long> {
}
