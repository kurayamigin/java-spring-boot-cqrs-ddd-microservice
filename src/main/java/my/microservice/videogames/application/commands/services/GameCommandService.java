package my.microservice.videogames.application.commands.services;

import com.github.fge.jsonpatch.JsonPatch;
import my.artifacts.abstractions.IPublisher;
import my.microservice.videogames.application.commands.dtos.GameCommand;
import my.microservice.videogames.application.commands.events.game.GameCreatedEvent;
import my.microservice.videogames.application.commands.events.game.GameDeletedEvent;
import my.microservice.videogames.application.commands.events.game.GameUpdatedEvent;
import my.microservice.videogames.application.commands.publishers.GamePublisher;
import my.microservice.videogames.application.commands.services.abstractions.IGameCommandService;
import my.microservice.videogames.cross_cutting.exceptions.EntityNotFoundException;
import my.microservice.videogames.cross_cutting.utils.RestPatchUtils;
import my.microservice.videogames.domain.models.Game;
import my.microservice.videogames.infrastructure.mappers.IGameMapper;
import my.microservice.videogames.infrastructure.aggregators.abstractions.IGameService;
import org.springframework.stereotype.Service;

@Service
public class GameCommandService implements IGameCommandService {
    private final IGameService gameService;
    private final IPublisher publisher;
    private final IGameMapper mapper;

    public GameCommandService(IGameService gameService, GamePublisher publisher, IGameMapper mapper) {
        this.gameService = gameService;
        this.publisher = publisher;
        this.mapper = mapper;
    }

    @Override
    public Long create(GameCommand command) {
        Game entity = mapper.toEntity(command);
        gameService.create(entity);
        publisher.send(new GameCreatedEvent(entity));
        return entity.getId();
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        gameService.delete(id);
        publisher.send(new GameDeletedEvent(id));
    }

    @Override
    public void patch(Long id, JsonPatch patch) throws EntityNotFoundException {
        Game game = gameService.get(id);
        GameCommand patched = RestPatchUtils.apply(patch, mapper.toCommand(game));
        mapper.patch(patched, game);
        gameService.update(game);
        publisher.send(new GameUpdatedEvent(game));
    }
}
