package my.microservice.videogames.application.services.queries;

import my.microservice.videogames.application.dtos.queries.GameQuery;
import my.microservice.videogames.application.services.queries.abstractions.IGameQueryService;
import my.microservice.videogames.domain.entities.Game;
import my.microservice.videogames.domain.repositories.queries.IGameQueryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameQueryService implements IGameQueryService {
    private final IGameQueryRepository gameRepository;

    public GameQueryService(IGameQueryRepository repository) {
        this.gameRepository = repository;
    }

    @Override
    public List<GameQuery> get() {
        List<GameQuery> games = gameRepository.findAll();
        return games;
    }

}
