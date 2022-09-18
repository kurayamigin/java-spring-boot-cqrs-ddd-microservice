package my.microservice.videogames.application.services.commands;

import my.microservice.videogames.application.dtos.commands.GameCommand;
import my.microservice.videogames.application.dtos.mappers.IGameMapper;
import my.microservice.videogames.application.dtos.queries.GameQuery;
import my.microservice.videogames.application.services.commands.abstractions.IGameCommandService;
import my.microservice.videogames.cross_cutting.utils.ApplicationContextProvider;
import my.microservice.videogames.domain.entities.Game;
import my.microservice.videogames.domain.repositories.commands.IGameCommandRepository;
import my.microservice.videogames.domain.repositories.queries.IGameQueryRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Service
@Transactional
public class GameCommandService implements IGameCommandService {
    private final IGameCommandRepository gameRepository;
    private final IGameMapper mapper;

    public GameCommandService(IGameCommandRepository gameRepository, IGameMapper mapper) {
        this.gameRepository = gameRepository;
        this.mapper = mapper;
    }

    @Override
    public List<GameQuery> get() {
        List<Game> games = (List<Game>) gameRepository.findAll();
        List<GameQuery> queries = mapper.toQuery(games);
        return queries;
    }

    @Override
    public void create(GameCommand command) {
        Game entity = mapper.toEntity(command);
        gameRepository.save(entity);

        //TODO: change to event sourcing
        ApplicationContext IoC = ApplicationContextProvider.getContext();
        IGameQueryRepository repository = IoC.getBean(IGameQueryRepository.class);
        repository.save(mapper.toQuery(entity));
     }
}
