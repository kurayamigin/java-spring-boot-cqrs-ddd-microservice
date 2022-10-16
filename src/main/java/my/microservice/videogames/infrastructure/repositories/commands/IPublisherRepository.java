package my.microservice.videogames.infrastructure.repositories.commands;

import my.microservice.videogames.domain.models.Publisher;
import org.springframework.stereotype.Repository;

@Repository
public interface IPublisherRepository extends ICommandRepository<Publisher, Long> {
}
