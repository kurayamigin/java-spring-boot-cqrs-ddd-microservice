package my.microservice.videogames.application.api;

import my.microservice.videogames.application.dtos.commands.GameCommand;
import my.microservice.videogames.application.dtos.queries.GameQuery;
import my.microservice.videogames.application.services.commands.abstractions.IGameCommandService;
import my.microservice.videogames.application.services.queries.abstractions.IGameQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("game")
public class GameController {

    private final IGameCommandService commandService;
    private final IGameQueryService queryService;

    public GameController(IGameCommandService commandService, IGameQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @GetMapping
    public ResponseEntity<List<GameQuery>> get() {
        CompletableFuture<List<GameQuery>> future = CompletableFuture.supplyAsync(queryService::get);
//        List<GameQuery> gameQueries = service.get();
        return ResponseEntity.ok(future.join());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid GameCommand command) {
        CompletableFuture.runAsync(() -> commandService.create(command)).join();
        return ResponseEntity
                .created(ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri())
                .build();
    }
}
