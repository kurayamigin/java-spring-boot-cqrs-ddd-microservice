package my.microservice.videogames.infrastructure.repositories.commands;

import my.microservice.videogames.domain.models.Developer;
import my.microservice.videogames.domain.models.Game;
import org.springframework.stereotype.Repository;

@Repository
public interface IDeveloperRepository extends ICommandRepository<Developer, Long> {
}
