package my.microservice.videogames.application.queries.dtos;

import my.microservice.videogames.application.commands.dtos.GameCommand;
import my.microservice.videogames.cross_cutting.validators.ValidDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Document(collection = "game")
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
