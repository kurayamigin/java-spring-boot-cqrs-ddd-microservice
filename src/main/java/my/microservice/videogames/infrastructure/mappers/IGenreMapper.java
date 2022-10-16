package my.microservice.videogames.infrastructure.mappers;

import my.microservice.videogames.application.queries.dtos.GenreQuery;
import my.microservice.videogames.domain.models.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface IGenreMapper {
    Set<Genre> toEntity(Set<GenreQuery> queries);
    Set<GenreQuery> toQuery(Set<Genre> genres);

    Genre toEntity(GenreQuery query);

    GenreQuery toQuery(Genre genre);

    default Genre mapId(Long id) {
        Genre genre = new Genre();
        genre.setId(id);
        return genre;
    }
    Set<Genre> mapIds(Set<Long> ids);

}
