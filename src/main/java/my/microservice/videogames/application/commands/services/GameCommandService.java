package my.microservice.videogames.application.commands.services;

import com.github.fge.jsonpatch.JsonPatch;
import my.microservice.videogames.application.commands.dtos.GameCommand;
import my.microservice.videogames.application.commands.events.game.GameCreatedEvent;
import my.microservice.videogames.application.commands.events.game.GameDeletedEvent;
import my.microservice.videogames.application.commands.events.game.GameUpdatedEvent;
import my.microservice.videogames.application.commands.services.abstractions.IGameCommandService;
import my.microservice.videogames.cross_cutting.cqrs.IPublisher;
import my.microservice.videogames.cross_cutting.exceptions.EntityNotFoundException;
import my.microservice.videogames.cross_cutting.utils.RestPatchUtils;
import my.microservice.videogames.domain.models.Game;
import my.microservice.videogames.infrastructure.mappers.IGameMapper;
import my.microservice.videogames.infrastructure.repositories.commands.IGameCommandRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GameCommandService implements IGameCommandService {
    private final IGameCommandRepository gameRepository;
    private final IPublisher publisher;

    private final IGameMapper mapper;

    public GameCommandService(IGameCommandRepository gameRepository, IPublisher publisher, IGameMapper mapper) {
        this.gameRepository = gameRepository;
        this.publisher = publisher;
        this.mapper = mapper;
    }

    @Override
    public List<GameCommand> get() {
        List<Game> games = (List<Game>) gameRepository.findAll();
        return mapper.toCommand(games);
    }

    @Override
    public GameCommand getById(Long id) throws EntityNotFoundException {
        Optional<Game> game = gameRepository.findById(id);
        return game.map(mapper::toCommand)
                .orElseThrow(() -> new EntityNotFoundException(Game.class, id));
    }

    @Override
    public void create(GameCommand command) {
        Game entity = mapper.toEntity(command);
        gameRepository.save(entity);

        publisher.send(new GameCreatedEvent(entity));
//        //TODO: change to event sourcing
//        ApplicationContext IoC = ApplicationContextProvider.getContext();
//        IGameQueryRepository repository = IoC.getBean(IGameQueryRepository.class);
//        repository.save(mapper.toQuery(entity));

    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        if (!gameRepository.existsById(id)) throw new EntityNotFoundException(Game.class, id);
        gameRepository.deleteById(id);
        publisher.send(new GameDeletedEvent(id));
//        //TODO: change to event sourcing
//        ApplicationContext IoC = ApplicationContextProvider.getContext();
//        IGameQueryRepository repository = IoC.getBean(IGameQueryRepository.class);
//        repository.deleteById(id);
    }

    @Override
    public void patch(Long id, JsonPatch patch) throws EntityNotFoundException {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Game.class, id));
        GameCommand patched = RestPatchUtils.apply(patch, mapper.toCommand(game));
        mapper.patch(patched, game);
        gameRepository.save(game);

        publisher.send(new GameUpdatedEvent(game));
//        //TODO: change to event sourcing
//        ApplicationContext IoC = ApplicationContextProvider.getContext();
//        IGameQueryRepository repository = IoC.getBean(IGameQueryRepository.class);
//        repository.save(mapper.toQuery(game));
    }
}
