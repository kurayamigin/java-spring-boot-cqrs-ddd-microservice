package my.microservice.videogames.infrastructure.seeding;

import my.microservice.videogames.application.queries.dtos.PublisherQuery;
import my.microservice.videogames.domain.models.Publisher;
import my.microservice.videogames.infrastructure.mappers.IPublisherMapper;
import my.microservice.videogames.infrastructure.repositories.commands.IPublisherRepository;
import my.microservice.videogames.infrastructure.repositories.queries.IPublisherQueryRepository;
import org.springframework.stereotype.Component;

@Component
@SeederAnnotation
public class PublisherSeeder extends CqrsSeeder<Publisher, PublisherQuery, Long> {
    private static final String filename = "publishers.csv";
    private static final Class<Publisher> type = Publisher.class;
    public PublisherSeeder(IPublisherMapper mapper, IPublisherRepository repository, IPublisherQueryRepository queryRepository) {
        super(PublisherSeeder.filename, PublisherSeeder.type, mapper);
        super.repository = repository;
        super.queryRepository = queryRepository;
    }
}
