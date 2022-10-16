package my.microservice.videogames.application.commands.publishers;

import my.artifacts.Publisher;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CompanyPublisher extends Publisher {

    public CompanyPublisher(@Qualifier("companies-queue") Queue queue) {
        super(queue);
    }
}
