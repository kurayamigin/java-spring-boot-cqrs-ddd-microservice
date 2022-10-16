package my.microservice.videogames.application.commands.services;

import com.github.fge.jsonpatch.JsonPatch;
import my.artifacts.abstractions.IPublisher;
import my.microservice.videogames.application.commands.dtos.SeriesCommand;
import my.microservice.videogames.application.commands.events.series.SeriesCreatedEvent;
import my.microservice.videogames.application.commands.events.series.SeriesDeletedEvent;
import my.microservice.videogames.application.commands.events.series.SeriesUpdatedEvent;
import my.microservice.videogames.application.commands.publishers.SeriesPublisher;
import my.microservice.videogames.application.commands.services.abstractions.ISeriesCommandService;
import my.microservice.videogames.cross_cutting.exceptions.EntityNotFoundException;
import my.microservice.videogames.cross_cutting.utils.RestPatchUtils;
import my.microservice.videogames.domain.models.Series;
import my.microservice.videogames.infrastructure.mappers.ISeriesMapper;
import my.microservice.videogames.infrastructure.aggregators.abstractions.ISeriesService;
import org.springframework.stereotype.Service;

@Service
public class SeriesCommandService implements ISeriesCommandService {
    private final ISeriesService service;
    private final ISeriesMapper mapper;
    private final IPublisher publisher;

    public SeriesCommandService(ISeriesService service, ISeriesMapper mapper, SeriesPublisher publisher) {
        this.service = service;
        this.mapper = mapper;
        this.publisher = publisher;
    }

    @Override
    public Long create(SeriesCommand command) {
        Series entity = mapper.toEntity(command);
        service.create(entity);
        publisher.send(new SeriesCreatedEvent(entity));
        return entity.getId();
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        service.delete(id);
        publisher.send(new SeriesDeletedEvent(id));
    }

    @Override
    public void patch(Long id, JsonPatch patch) throws EntityNotFoundException {
        Series entity = service.get(id);
        SeriesCommand patched = RestPatchUtils.apply(patch, mapper.toCommand(entity));
        mapper.patch(patched, entity);
        service.update(entity);
        publisher.send(new SeriesUpdatedEvent(entity));
    }
}
