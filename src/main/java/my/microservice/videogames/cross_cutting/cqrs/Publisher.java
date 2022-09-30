package my.microservice.videogames.cross_cutting.cqrs;

import com.google.gson.Gson;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class Publisher implements IPublisher {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;


    @Override
    public <TEvent extends Event> void send(TEvent event) {
        String message = new Gson().toJson(event);
        this.template.convertAndSend(queue.getName(), message);
        System.out.println(" [x] Sent '" + message + "'");
    }
}
