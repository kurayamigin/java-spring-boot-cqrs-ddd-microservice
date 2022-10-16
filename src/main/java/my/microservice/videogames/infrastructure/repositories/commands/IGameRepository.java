package my.microservice.videogames.infrastructure.repositories.commands;

import my.microservice.videogames.domain.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGameRepository extends ICommandRepository<Game, Long>, JpaRepository<Game, Long> {
}
