package my.microservice.videogames.cross_cutting.cqrs;

import com.google.gson.Gson;
import my.microservice.videogames.cross_cutting.utils.GsonUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;

import java.util.Map;
import java.util.function.Consumer;

public abstract class Subscriber implements ISubscriber {


    @RabbitHandler
    @Override
    final public void receive(String message) {
        Event event = GsonUtils.fromJson(message, Event.class);
        System.out.println(event);
        String eventName = event.name.toLowerCase();
        Consumer<String> handler = getHandlers().get(eventName);
        handler.accept(message);
    }

    protected abstract Map<String, Consumer<String>> getHandlers();

}
