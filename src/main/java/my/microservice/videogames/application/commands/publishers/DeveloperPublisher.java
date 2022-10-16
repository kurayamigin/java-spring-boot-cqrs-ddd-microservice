package my.microservice.videogames.application.commands.publishers;

import my.artifacts.Publisher;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DeveloperPublisher extends Publisher {

    public DeveloperPublisher(@Qualifier("devs-queue") Queue queue) {
        super(queue);
    }
}
