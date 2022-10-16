package my.microservice.videogames.application.queries.api;

import my.microservice.videogames.application.queries.dtos.SeriesQuery;
import my.microservice.videogames.application.queries.services.abstractions.ISeriesQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("series")
public class SeriesQueryController {

    private final ISeriesQueryService queryService;

    public SeriesQueryController(ISeriesQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping
    public ResponseEntity<List<SeriesQuery>> get() {
        CompletableFuture<List<SeriesQuery>> future = CompletableFuture.supplyAsync(queryService::get);
        return ResponseEntity.ok(future.join());
    }

    @GetMapping("{id}")
    public ResponseEntity<SeriesQuery> getById(@PathVariable Long id) {
        Optional<SeriesQuery> query = queryService.getById(id);
        return query
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
