package my.microservice.videogames.infrastructure.repositories.commands;

import my.microservice.videogames.domain.models.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGameCommandRepository extends ICommandRepository<Game, Long> {
}
