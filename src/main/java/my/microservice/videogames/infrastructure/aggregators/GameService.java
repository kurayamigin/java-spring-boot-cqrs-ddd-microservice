package my.microservice.videogames.infrastructure.aggregators;

import my.artifacts.models.Entity;
import my.microservice.videogames.cross_cutting.exceptions.EntityNotFoundException;
import my.microservice.videogames.domain.models.*;
import my.microservice.videogames.infrastructure.repositories.commands.*;
import my.microservice.videogames.infrastructure.aggregators.abstractions.EntityService;
import my.microservice.videogames.infrastructure.aggregators.abstractions.IGameService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class GameService extends EntityService<Game, Long> implements IGameService {

    private final IPublisherRepository publisherRepository;
    private final ICompanyRepository companyRepository;
    private final IGenreRepository genreRepository;
    private final IDeveloperRepository developerRepository;

    public GameService(IGameRepository repository, IPublisherRepository publisherRepository, ICompanyRepository companyRepository, IGenreRepository genreRepository, IDeveloperRepository developerRepository) {
        super(Game.class, repository);
        this.publisherRepository = publisherRepository;
        this.companyRepository = companyRepository;
        this.genreRepository = genreRepository;
        this.developerRepository = developerRepository;
    }

    @Override
    public Game create(Game entity) {
        super.create(entity);
        Set<Long> ids = entity.getGenres().stream().map(Entity::getId).collect(Collectors.toSet());
        entity.setGenres(genreRepository.findAllById(ids));
        return entity;
    }

    protected void validate(Game game) throws EntityNotFoundException {
        Long id;
        if ((id = game.getPublisher().getId()) == null || !publisherRepository.existsById(id))
            throw new EntityNotFoundException(Publisher.class, id);
        if ((id = game.getCompany().getId()) == null || !companyRepository.existsById(id))
            throw new EntityNotFoundException(Company.class, id);
        if ((id = game.getDeveloper().getId()) == null || !developerRepository.existsById(id))
            throw new EntityNotFoundException(Developer.class, id);
        Set<Long> ids = game.getGenres().stream().map(Entity::getId).collect(Collectors.toSet());
        if (ids.isEmpty() || !genreRepository.existsAllByIdIn(ids))
            throw new EntityNotFoundException(Genre.class, id);
    }
}
