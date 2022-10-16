package my.microservice.videogames.application.queries.subscribers;

import my.artifacts.EventHandler;
import my.artifacts.Subscriber;
import my.microservice.videogames.application.commands.events.developer.DeveloperCreatedEvent;
import my.microservice.videogames.application.commands.events.developer.DeveloperDeletedEvent;
import my.microservice.videogames.application.commands.events.developer.DeveloperUpdatedEvent;
import my.microservice.videogames.application.queries.dtos.DeveloperQuery;
import my.microservice.videogames.cross_cutting.utils.GsonUtils;
import my.microservice.videogames.infrastructure.events.CompanyEvents;
import my.microservice.videogames.infrastructure.events.DeveloperEvents;
import my.microservice.videogames.infrastructure.mappers.IDeveloperMapper;
import my.microservice.videogames.infrastructure.repositories.queries.IDeveloperQueryRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@RabbitListener(queues = "developers")
@Component
public class DeveloperSubscriber extends Subscriber {

    private final IDeveloperQueryRepository queryRepository;
    private final IDeveloperMapper mapper;
    public DeveloperSubscriber(IDeveloperQueryRepository queryRepository, IDeveloperMapper mapper) {
        this.queryRepository = queryRepository;
        this.mapper = mapper;
    }

    @EventHandler(events = {DeveloperEvents.CREATED})
    public void onCreated(String message) {
        DeveloperCreatedEvent event = GsonUtils.fromJson(message, DeveloperCreatedEvent.class);
        DeveloperQuery query = mapper.toQuery(event.getDeveloper());
        queryRepository.save(query);
    }

    @EventHandler(events = {DeveloperEvents.UPDATED})
    public void onUpdated(String message) {
        DeveloperUpdatedEvent event = GsonUtils.fromJson(message, DeveloperUpdatedEvent.class);
        DeveloperQuery entity = mapper.toQuery(event.getDeveloper());
        queryRepository.save(entity);
    }

    @EventHandler(events = {DeveloperEvents.DELETED})
    private void onDeleted(String message) {
        DeveloperDeletedEvent event = GsonUtils.fromJson(message, DeveloperDeletedEvent.class);
        queryRepository.deleteById(event.getDeletedId());
    }
}
