package my.microservice.videogames.application.queries.subscribers;

import my.microservice.videogames.application.commands.events.company.CompanyCreatedEvent;
import my.microservice.videogames.application.commands.events.company.CompanyDeletedEvent;
import my.microservice.videogames.application.commands.events.company.CompanyUpdatedEvent;
import my.microservice.videogames.application.queries.dtos.CompanyQuery;
import my.microservice.videogames.cross_cutting.cqrs.Subscriber;
import my.microservice.videogames.cross_cutting.events.CompanyEvents;
import my.microservice.videogames.cross_cutting.utils.GsonUtils;
import my.microservice.videogames.infrastructure.mappers.ICompanyMapper;
import my.microservice.videogames.infrastructure.repositories.queries.ICompanyQueryRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@RabbitListener(queues = "hello")
@Component
public class CompanySubscriber extends Subscriber {
    Map<String, Consumer<String>> handlers = new HashMap<String, Consumer<String>>(){{
        put(CompanyEvents.CREATED, CompanySubscriber.this::onCreated);
        put(CompanyEvents.UPDATED, CompanySubscriber.this::onUpdated);
        put(CompanyEvents.DELETED, CompanySubscriber.this::onDeleted);
    }};

    private final ICompanyQueryRepository queryRepository;
    private final ICompanyMapper mapper;
    public CompanySubscriber(ICompanyQueryRepository queryRepository, ICompanyMapper mapper) {
        this.queryRepository = queryRepository;
        this.mapper = mapper;
    }

    @Override
    public Map<String, Consumer<String>> getHandlers() {
        return handlers;
    }

    private void onCreated(String message) {
        CompanyCreatedEvent event = GsonUtils.fromJson(message, CompanyCreatedEvent.class);
        CompanyQuery query = mapper.toQuery(event.getCompany());
        queryRepository.save(query);
    }

    private void onUpdated(String message) {
        CompanyUpdatedEvent event = GsonUtils.fromJson(message, CompanyUpdatedEvent.class);
        CompanyQuery entity = mapper.toQuery(event.getCompany());
        queryRepository.save(entity);
    }

    private void onDeleted(String message) {
        CompanyDeletedEvent event = GsonUtils.fromJson(message, CompanyDeletedEvent.class);
        queryRepository.deleteById(event.getDeletedId());
    }
}
