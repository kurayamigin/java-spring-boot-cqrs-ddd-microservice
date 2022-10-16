package my.microservice.videogames.application.queries.api;

import my.microservice.videogames.application.queries.services.abstractions.IGeneralQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("general")
public class GeneralQueryController {
    private final IGeneralQueryService generalQueryService;

    public GeneralQueryController(IGeneralQueryService generalQueryService) {
        this.generalQueryService = generalQueryService;
    }

    @GetMapping("sync-query-db")
    public ResponseEntity<?> sync() {
        generalQueryService.syncQueryStorage();
        return ResponseEntity.noContent().build();
    }
}
