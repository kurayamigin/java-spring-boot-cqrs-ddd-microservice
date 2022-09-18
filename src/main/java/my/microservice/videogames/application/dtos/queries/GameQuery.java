package my.microservice.videogames.application.dtos.queries;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class GameQuery implements IQuery {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
