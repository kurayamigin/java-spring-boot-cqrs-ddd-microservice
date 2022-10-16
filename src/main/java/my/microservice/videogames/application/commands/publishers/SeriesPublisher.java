package my.microservice.videogames.application.commands.publishers;

import my.artifacts.Publisher;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SeriesPublisher extends Publisher {

    public SeriesPublisher(@Qualifier("series-queue") Queue queue) {
        super(queue);
    }
}
