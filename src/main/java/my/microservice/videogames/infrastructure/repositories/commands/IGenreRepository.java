package my.microservice.videogames.infrastructure.repositories.commands;

import my.microservice.videogames.domain.models.Genre;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IGenreRepository extends ICommandRepository<Genre, Long> {

    @Override
    Set<Genre> findAllById(Iterable<Long> longs);

    boolean existsAllByIdIn(Set<Long> id);
}
