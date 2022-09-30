package my.microservice.videogames.application.queries.api;

import my.microservice.videogames.application.commands.dtos.GameCommand;
import my.microservice.videogames.application.commands.services.abstractions.IGameCommandService;
import my.microservice.videogames.application.queries.dtos.GameQuery;
import my.microservice.videogames.application.queries.services.abstractions.IGameQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("game")
public class GameQueryController {

    private final IGameQueryService queryService;

    public GameQueryController(IGameQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping
    public ResponseEntity<List<GameQuery>> get() {
        CompletableFuture<List<GameQuery>> future = CompletableFuture.supplyAsync(queryService::get);
        return ResponseEntity.ok(future.join());
    }

    @GetMapping("{id}")
    public ResponseEntity<GameQuery> getById(@PathVariable Long id) {
        Optional<GameQuery> gameQuery = queryService.getById(id);
        return gameQuery
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
