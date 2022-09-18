package my.microservice.videogames.domain.repositories.commands;

import my.microservice.videogames.domain.entities.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGameCommandRepository extends CrudRepository<Game, Long> {
}
