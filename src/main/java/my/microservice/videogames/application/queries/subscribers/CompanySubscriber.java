package my.microservice.videogames.application.queries.subscribers;

import my.artifacts.EventHandler;
import my.artifacts.Subscriber;
import my.microservice.videogames.application.commands.events.company.CompanyCreatedEvent;
import my.microservice.videogames.application.commands.events.company.CompanyDeletedEvent;
import my.microservice.videogames.application.commands.events.company.CompanyUpdatedEvent;
import my.microservice.videogames.application.queries.dtos.CompanyQuery;
import my.microservice.videogames.cross_cutting.utils.GsonUtils;
import my.microservice.videogames.infrastructure.events.CompanyEvents;
import my.microservice.videogames.infrastructure.mappers.ICompanyMapper;
import my.microservice.videogames.infrastructure.repositories.queries.ICompanyQueryRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@RabbitListener(queues = "companies")
@Component
public class CompanySubscriber extends Subscriber {
    private final ICompanyQueryRepository queryRepository;
    private final ICompanyMapper mapper;
    public CompanySubscriber(ICompanyQueryRepository queryRepository, ICompanyMapper mapper) {
        this.queryRepository = queryRepository;
        this.mapper = mapper;
    }

    @EventHandler(events = {CompanyEvents.CREATED})
    public void onCreated(String message) {
        CompanyCreatedEvent event = GsonUtils.fromJson(message, CompanyCreatedEvent.class);
        CompanyQuery query = mapper.toQuery(event.getCompany());
        queryRepository.save(query);
    }

    @EventHandler(events = {CompanyEvents.UPDATED})
    public void onUpdated(String message) {
        CompanyUpdatedEvent event = GsonUtils.fromJson(message, CompanyUpdatedEvent.class);
        CompanyQuery entity = mapper.toQuery(event.getCompany());
        queryRepository.save(entity);
    }

    @EventHandler(events = {CompanyEvents.DELETED})
    public void onDeleted(String message) {
        CompanyDeletedEvent event = GsonUtils.fromJson(message, CompanyDeletedEvent.class);
        queryRepository.deleteById(event.getDeletedId());
    }
}
