package my.microservice.videogames.application.queries.dtos;

import my.artifacts.Query;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "series")
public class SeriesQuery extends Query<Long> {

    private String name;
    private Date releaseDate;

    @DBRef
    private List<GameQuery> games;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<GameQuery> getGames() {
        return games;
    }

    public void setGames(List<GameQuery> games) {
        this.games = games;
    }
}
