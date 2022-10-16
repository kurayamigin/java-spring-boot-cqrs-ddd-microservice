package my.microservice.videogames.application.queries.subscribers;

import my.artifacts.EventHandler;
import my.artifacts.Subscriber;
import my.microservice.videogames.application.commands.events.game.GameCreatedEvent;
import my.microservice.videogames.application.commands.events.game.GameDeletedEvent;
import my.microservice.videogames.application.commands.events.game.GameUpdatedEvent;
import my.microservice.videogames.application.queries.dtos.GameQuery;
import my.microservice.videogames.cross_cutting.utils.GsonUtils;
import my.microservice.videogames.infrastructure.events.DeveloperEvents;
import my.microservice.videogames.infrastructure.events.GameEvents;
import my.microservice.videogames.infrastructure.mappers.IGameMapper;
import my.microservice.videogames.infrastructure.repositories.queries.IGameQueryRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@RabbitListener(queues = "games")
@Component
public class GameSubscriber extends Subscriber {

    private final IGameQueryRepository queryRepository;
    private final IGameMapper mapper;
    public GameSubscriber(IGameQueryRepository queryRepository, IGameMapper mapper) {
        this.queryRepository = queryRepository;
        this.mapper = mapper;
    }

    @EventHandler(events = {GameEvents.CREATED})
    public void onCreated(String message) {
        GameCreatedEvent event = GsonUtils.fromJson(message, GameCreatedEvent.class);
        GameQuery query = mapper.toQuery(event.getCreatedGame());
        queryRepository.save(query);
    }

    @EventHandler(events = {GameEvents.UPDATED})
    public void onUpdated(String message) {
        GameUpdatedEvent event = GsonUtils.fromJson(message, GameUpdatedEvent.class);
        GameQuery entity = mapper.toQuery(event.getUpdatedGame());
        queryRepository.save(entity);
    }

    @EventHandler(events = {GameEvents.DELETED})
    public void onDeleted(String message) {
        GameDeletedEvent event = GsonUtils.fromJson(message, GameDeletedEvent.class);
        queryRepository.deleteById(event.getDeletedId());
    }
}
