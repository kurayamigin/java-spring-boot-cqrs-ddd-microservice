package my.microservice.videogames.infrastructure.seeding;

import my.microservice.videogames.domain.models.Genre;
import my.microservice.videogames.infrastructure.repositories.commands.IGenreRepository;
import org.springframework.stereotype.Component;

@Component
@SeederAnnotation
public class GenresSeeder extends EntitySeeder<Genre, Long> {
    private static final String filename = "genres.csv";
    private static final Class<Genre> type = Genre.class;
    public GenresSeeder(IGenreRepository repository) {
        super(GenresSeeder.filename, GenresSeeder.type);
        super.repository = repository;
    }
}
