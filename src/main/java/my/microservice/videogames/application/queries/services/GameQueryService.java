package my.microservice.videogames.application.queries.services;

import my.microservice.videogames.application.queries.dtos.GameQuery;
import my.microservice.videogames.application.queries.services.abstractions.IGameQueryService;
import my.microservice.videogames.infrastructure.repositories.queries.IGameQueryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<GameQuery> getById(Long id) {
        return gameRepository.findById(id);
    }

}
