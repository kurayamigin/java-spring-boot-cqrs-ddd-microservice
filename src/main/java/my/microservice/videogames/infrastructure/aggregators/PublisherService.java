package my.microservice.videogames.infrastructure.aggregators;

import my.microservice.videogames.domain.models.Publisher;
import my.microservice.videogames.infrastructure.repositories.commands.IPublisherRepository;
import my.microservice.videogames.infrastructure.aggregators.abstractions.EntityService;
import my.microservice.videogames.infrastructure.aggregators.abstractions.IPublisherService;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@Service
@Transactional
public class PublisherService extends EntityService<Publisher, Long> implements IPublisherService {

    public PublisherService(IPublisherRepository repository) {
        super(Publisher.class, repository);
    }

    protected void validate(Publisher company) throws ResponseStatusException {}
}
