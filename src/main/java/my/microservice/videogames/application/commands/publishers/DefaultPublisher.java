package my.microservice.videogames.application.commands.publishers;

import my.artifacts.Publisher;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class DefaultPublisher extends Publisher {
    public DefaultPublisher(Queue queue) {
        super(queue);
    }
}
