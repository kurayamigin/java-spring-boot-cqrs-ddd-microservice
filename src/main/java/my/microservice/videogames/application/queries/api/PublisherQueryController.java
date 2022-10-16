package my.microservice.videogames.application.queries.api;

import my.microservice.videogames.application.queries.dtos.PublisherQuery;
import my.microservice.videogames.application.queries.services.abstractions.IPublisherQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("publisher")
public class PublisherQueryController {

    private final IPublisherQueryService queryService;

    public PublisherQueryController(IPublisherQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping
    public ResponseEntity<List<PublisherQuery>> get() {
        CompletableFuture<List<PublisherQuery>> future = CompletableFuture.supplyAsync(queryService::get);
        return ResponseEntity.ok(future.join());
    }

    @GetMapping("{id}")
    public ResponseEntity<PublisherQuery> getById(@PathVariable Long id) {
        Optional<PublisherQuery> query = queryService.getById(id);
        return query
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
