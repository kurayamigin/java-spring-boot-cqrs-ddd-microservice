package my.microservice.videogames.application.commands.api;

import com.github.fge.jsonpatch.JsonPatch;
import my.microservice.videogames.application.commands.dtos.GameCommand;
import my.microservice.videogames.application.commands.dtos.SeriesCommand;
import my.microservice.videogames.application.commands.services.abstractions.IGameCommandService;
import my.microservice.videogames.application.commands.services.abstractions.ISeriesCommandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("series")
public class SeriesCommandController {

    private final ISeriesCommandService commandService;

    public SeriesCommandController(ISeriesCommandService commandService) {
        this.commandService = commandService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid SeriesCommand command) {
        CompletableFuture.runAsync(() -> commandService.create(command)).join();
        return ResponseEntity
                .created(ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri())
                .build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        commandService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> patch(@PathVariable Long id, @RequestBody JsonPatch patch) {
        commandService.patch(id, patch);
        return ResponseEntity.noContent().build();
    }
}