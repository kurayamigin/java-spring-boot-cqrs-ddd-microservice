package my.microservice.videogames.cross_cutting.cqrs;

public interface IPublisher {

    <TEvent extends Event> void send(TEvent event);
}
