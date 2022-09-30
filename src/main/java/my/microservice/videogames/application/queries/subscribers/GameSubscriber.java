package my.microservice.videogames.application.queries.subscribers;

import my.microservice.videogames.application.commands.events.game.GameCreatedEvent;
import my.microservice.videogames.application.commands.events.game.GameDeletedEvent;
import my.microservice.videogames.application.commands.events.game.GameUpdatedEvent;
import my.microservice.videogames.application.queries.dtos.GameQuery;
import my.microservice.videogames.cross_cutting.cqrs.Subscriber;
import my.microservice.videogames.cross_cutting.events.GameEvents;
import my.microservice.videogames.cross_cutting.utils.GsonUtils;
import my.microservice.videogames.infrastructure.mappers.IGameMapper;
import my.microservice.videogames.infrastructure.repositories.queries.IGameQueryRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@RabbitListener(queues = "hello")
@Component
public class GameSubscriber extends Subscriber {
    Map<String, Consumer<String>> handlers = new HashMap<String, Consumer<String>>(){{
        put(GameEvents.CREATED, GameSubscriber.this::onCreated);
        put(GameEvents.UPDATED, GameSubscriber.this::onUpdated);
        put(GameEvents.DELETED, GameSubscriber.this::onDeleted);
    }};

    private final IGameQueryRepository queryRepository;
    private final IGameMapper mapper;
    public GameSubscriber(IGameQueryRepository queryRepository, IGameMapper mapper) {
        this.queryRepository = queryRepository;
        this.mapper = mapper;
    }

    @Override
    public Map<String, Consumer<String>> getHandlers() {
        return handlers;
    }

    private void onCreated(String message) {
        GameCreatedEvent event = GsonUtils.fromJson(message, GameCreatedEvent.class);
        GameQuery query = mapper.toQuery(event.getCreatedGame());
        queryRepository.save(query);
    }

    private void onUpdated(String message) {
        GameUpdatedEvent event = GsonUtils.fromJson(message, GameUpdatedEvent.class);
        GameQuery entity = mapper.toQuery(event.getUpdatedGame());
        queryRepository.save(entity);
    }

    private void onDeleted(String message) {
        GameDeletedEvent event = GsonUtils.fromJson(message, GameDeletedEvent.class);
        queryRepository.deleteById(event.getDeletedId());
    }
}
