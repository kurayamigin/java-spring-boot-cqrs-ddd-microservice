package my.microservice.videogames.application.queries.subscribers;

import my.artifacts.EventHandler;
import my.artifacts.Subscriber;
import my.microservice.videogames.application.commands.events.publisher.PublisherCreatedEvent;
import my.microservice.videogames.application.commands.events.publisher.PublisherDeletedEvent;
import my.microservice.videogames.application.commands.events.publisher.PublisherUpdatedEvent;
import my.microservice.videogames.application.queries.dtos.PublisherQuery;
import my.microservice.videogames.cross_cutting.utils.GsonUtils;
import my.microservice.videogames.infrastructure.events.GameEvents;
import my.microservice.videogames.infrastructure.events.PublisherEvents;
import my.microservice.videogames.infrastructure.mappers.IPublisherMapper;
import my.microservice.videogames.infrastructure.repositories.queries.IPublisherQueryRepository;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@RabbitListener(queues = "publishers")
@Component
public class PublisherSubscriber extends Subscriber {


    private final IPublisherQueryRepository queryRepository;
    private final IPublisherMapper mapper;
    public PublisherSubscriber(IPublisherQueryRepository queryRepository, IPublisherMapper mapper) {
        this.queryRepository = queryRepository;
        this.mapper = mapper;
    }

    @EventHandler(events = {PublisherEvents.CREATED})
    public void onCreated(String message) {
        PublisherCreatedEvent event = GsonUtils.fromJson(message, PublisherCreatedEvent.class);
        PublisherQuery query = mapper.toQuery(event.getPublisher());
        queryRepository.save(query);
    }

    @EventHandler(events = {PublisherEvents.UPDATED})
    public void onUpdated(String message) {
        PublisherUpdatedEvent event = GsonUtils.fromJson(message, PublisherUpdatedEvent.class);
        PublisherQuery entity = mapper.toQuery(event.getPublisher());
        queryRepository.save(entity);
    }

    @EventHandler(events = {PublisherEvents.DELETED})
    public void onDeleted(String message) {
        PublisherDeletedEvent event = GsonUtils.fromJson(message, PublisherDeletedEvent.class);
        queryRepository.deleteById(event.getDeletedId());
    }
}
