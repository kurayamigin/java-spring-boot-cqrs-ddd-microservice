package my.microservice.videogames.application.queries.subscribers;

import my.microservice.videogames.application.commands.events.publisher.PublisherCreatedEvent;
import my.microservice.videogames.application.commands.events.publisher.PublisherDeletedEvent;
import my.microservice.videogames.application.commands.events.publisher.PublisherUpdatedEvent;
import my.microservice.videogames.application.queries.dtos.PublisherQuery;
import my.microservice.videogames.cross_cutting.cqrs.Subscriber;
import my.microservice.videogames.cross_cutting.events.PublisherEvents;
import my.microservice.videogames.cross_cutting.utils.GsonUtils;
import my.microservice.videogames.infrastructure.mappers.IPublisherMapper;
import my.microservice.videogames.infrastructure.repositories.queries.IPublisherQueryRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@RabbitListener(queues = "hello")
@Component
public class PublisherSubscriber extends Subscriber {
    Map<String, Consumer<String>> handlers = new HashMap<String, Consumer<String>>(){{
        put(PublisherEvents.CREATED, PublisherSubscriber.this::onCreated);
        put(PublisherEvents.UPDATED, PublisherSubscriber.this::onUpdated);
        put(PublisherEvents.DELETED, PublisherSubscriber.this::onDeleted);
    }};

    private final IPublisherQueryRepository queryRepository;
    private final IPublisherMapper mapper;
    public PublisherSubscriber(IPublisherQueryRepository queryRepository, IPublisherMapper mapper) {
        this.queryRepository = queryRepository;
        this.mapper = mapper;
    }

    @Override
    public Map<String, Consumer<String>> getHandlers() {
        return handlers;
    }

    private void onCreated(String message) {
        PublisherCreatedEvent event = GsonUtils.fromJson(message, PublisherCreatedEvent.class);
        PublisherQuery query = mapper.toQuery(event.getPublisher());
        queryRepository.save(query);
    }

    private void onUpdated(String message) {
        PublisherUpdatedEvent event = GsonUtils.fromJson(message, PublisherUpdatedEvent.class);
        PublisherQuery entity = mapper.toQuery(event.getPublisher());
        queryRepository.save(entity);
    }

    private void onDeleted(String message) {
        PublisherDeletedEvent event = GsonUtils.fromJson(message, PublisherDeletedEvent.class);
        queryRepository.deleteById(event.getDeletedId());
    }
}
