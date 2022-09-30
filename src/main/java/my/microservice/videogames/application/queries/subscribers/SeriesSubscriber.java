package my.microservice.videogames.application.queries.subscribers;

import my.microservice.videogames.application.commands.events.series.SeriesCreatedEvent;
import my.microservice.videogames.application.commands.events.series.SeriesDeletedEvent;
import my.microservice.videogames.application.commands.events.series.SeriesUpdatedEvent;
import my.microservice.videogames.application.queries.dtos.SeriesQuery;
import my.microservice.videogames.cross_cutting.cqrs.Subscriber;
import my.microservice.videogames.cross_cutting.events.SeriesEvents;
import my.microservice.videogames.cross_cutting.utils.GsonUtils;
import my.microservice.videogames.infrastructure.mappers.ISeriesMapper;
import my.microservice.videogames.infrastructure.repositories.queries.ISeriesQueryRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@RabbitListener(queues = "hello")
@Component
public class SeriesSubscriber extends Subscriber {
    Map<String, Consumer<String>> handlers = new HashMap<String, Consumer<String>>(){{
        put(SeriesEvents.CREATED, SeriesSubscriber.this::onCreated);
        put(SeriesEvents.UPDATED, SeriesSubscriber.this::onUpdated);
        put(SeriesEvents.DELETED, SeriesSubscriber.this::onDeleted);
    }};

    private final ISeriesQueryRepository queryRepository;
    private final ISeriesMapper mapper;
    public SeriesSubscriber(ISeriesQueryRepository queryRepository, ISeriesMapper mapper) {
        this.queryRepository = queryRepository;
        this.mapper = mapper;
    }

    @Override
    public Map<String, Consumer<String>> getHandlers() {
        return handlers;
    }

    private void onCreated(String message) {
        SeriesCreatedEvent event = GsonUtils.fromJson(message, SeriesCreatedEvent.class);
        SeriesQuery query = mapper.toQuery(event.getSeries());
        queryRepository.save(query);
    }

    private void onUpdated(String message) {
        SeriesUpdatedEvent event = GsonUtils.fromJson(message, SeriesUpdatedEvent.class);
        SeriesQuery entity = mapper.toQuery(event.getSeries());
        queryRepository.save(entity);
    }

    private void onDeleted(String message) {
        SeriesDeletedEvent event = GsonUtils.fromJson(message, SeriesDeletedEvent.class);
        queryRepository.deleteById(event.getDeletedId());
    }
}
