package my.microservice.videogames.application.queries.api;

import my.microservice.videogames.application.queries.dtos.CompanyQuery;
import my.microservice.videogames.application.queries.services.abstractions.ICompanyQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("company")
public class CompanyQueryController {

    private final ICompanyQueryService queryService;

    public CompanyQueryController(ICompanyQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping
    public ResponseEntity<List<CompanyQuery>> get() {
        CompletableFuture<List<CompanyQuery>> future = CompletableFuture.supplyAsync(queryService::get);
        return ResponseEntity.ok(future.join());
    }

    @GetMapping("{id}")
    public ResponseEntity<CompanyQuery> getById(@PathVariable Long id) {
        Optional<CompanyQuery> query = queryService.getById(id);
        return query
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
