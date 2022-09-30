package my.microservice.videogames.application.queries.dtos;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "game")
public class GameQuery extends Query<Long> {

    private String name;

    @DBRef
    private PublisherQuery publisher;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PublisherQuery getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherQuery publisher) {
        this.publisher = publisher;
    }
}
