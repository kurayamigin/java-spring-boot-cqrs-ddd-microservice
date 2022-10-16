package my.microservice.videogames.application.commands.publishers;

import my.artifacts.Publisher;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GamePublisher extends Publisher {

    public GamePublisher(@Qualifier("games-queue") Queue queue) {
        super(queue);
    }
}
