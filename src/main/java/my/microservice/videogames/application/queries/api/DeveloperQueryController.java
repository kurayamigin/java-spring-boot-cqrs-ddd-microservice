package my.microservice.videogames.application.queries.api;

import my.microservice.videogames.application.queries.dtos.DeveloperQuery;
import my.microservice.videogames.application.queries.services.abstractions.IDeveloperQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("developer")
public class DeveloperQueryController {

    private final IDeveloperQueryService queryService;

    public DeveloperQueryController(IDeveloperQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping
    public ResponseEntity<List<DeveloperQuery>> get() {
        CompletableFuture<List<DeveloperQuery>> future = CompletableFuture.supplyAsync(queryService::get);
        return ResponseEntity.ok(future.join());
    }

    @GetMapping("{id}")
    public ResponseEntity<DeveloperQuery> getById(@PathVariable Long id) {
        Optional<DeveloperQuery> gameQuery = queryService.getById(id);
        return gameQuery
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
