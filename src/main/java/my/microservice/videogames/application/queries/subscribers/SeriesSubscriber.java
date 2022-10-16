package my.microservice.videogames.application.queries.subscribers;

import my.artifacts.EventHandler;
import my.artifacts.Subscriber;
import my.microservice.videogames.application.commands.events.series.SeriesCreatedEvent;
import my.microservice.videogames.application.commands.events.series.SeriesDeletedEvent;
import my.microservice.videogames.application.commands.events.series.SeriesUpdatedEvent;
import my.microservice.videogames.application.queries.dtos.SeriesQuery;
import my.microservice.videogames.cross_cutting.utils.GsonUtils;
import my.microservice.videogames.infrastructure.events.PublisherEvents;
import my.microservice.videogames.infrastructure.events.SeriesEvents;
import my.microservice.videogames.infrastructure.mappers.ISeriesMapper;
import my.microservice.videogames.infrastructure.repositories.queries.ISeriesQueryRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@RabbitListener(queues = "series")
@Component
public class SeriesSubscriber extends Subscriber {


    private final ISeriesQueryRepository queryRepository;
    private final ISeriesMapper mapper;
    public SeriesSubscriber(ISeriesQueryRepository queryRepository, ISeriesMapper mapper) {
        this.queryRepository = queryRepository;
        this.mapper = mapper;
    }


    @EventHandler(events = {SeriesEvents.CREATED})
    public void onCreated(String message) {
        SeriesCreatedEvent event = GsonUtils.fromJson(message, SeriesCreatedEvent.class);
        SeriesQuery query = mapper.toQuery(event.getSeries());
        queryRepository.save(query);
    }

    @EventHandler(events = {SeriesEvents.UPDATED})
    public void onUpdated(String message) {
        SeriesUpdatedEvent event = GsonUtils.fromJson(message, SeriesUpdatedEvent.class);
        SeriesQuery entity = mapper.toQuery(event.getSeries());
        queryRepository.save(entity);
    }

    @EventHandler(events = {SeriesEvents.DELETED})
    public void onDeleted(String message) {
        SeriesDeletedEvent event = GsonUtils.fromJson(message, SeriesDeletedEvent.class);
        queryRepository.deleteById(event.getDeletedId());
    }
}
