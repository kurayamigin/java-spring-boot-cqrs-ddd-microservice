package my.microservice.videogames.application.dtos.queries;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class GameQuery implements IQuery {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
