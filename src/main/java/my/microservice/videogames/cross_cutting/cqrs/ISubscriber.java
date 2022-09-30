package my.microservice.videogames.cross_cutting.cqrs;

public interface ISubscriber {

    void receive(String message);
}
