package my.microservice.videogames.application.commands.api;

import com.github.fge.jsonpatch.JsonPatch;
import my.microservice.videogames.application.commands.dtos.CompanyCommand;
import my.microservice.videogames.application.commands.dtos.DeveloperCommand;
import my.microservice.videogames.application.commands.dtos.GameCommand;
import my.microservice.videogames.application.commands.services.abstractions.IDeveloperCommandService;
import my.microservice.videogames.application.commands.services.abstractions.IGameCommandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("developer")
public class DeveloperCommandController {

    private final IDeveloperCommandService commandService;

    public DeveloperCommandController(IDeveloperCommandService commandService) {
        this.commandService = commandService;
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody @Valid DeveloperCommand command) {
        Long created = CompletableFuture.supplyAsync(() -> commandService.create(command)).join();
        return ResponseEntity
                .created(ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri())
                .body(created);
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
