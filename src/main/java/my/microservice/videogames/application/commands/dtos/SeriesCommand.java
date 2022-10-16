package my.microservice.videogames.application.commands.dtos;

import my.artifacts.abstractions.ICommand;
import my.microservice.videogames.cross_cutting.validators.ValidDate;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class SeriesCommand implements ICommand {

    @NotNull
    @Length(max = 250)
    private String name;

    @ValidDate
    private Date releaseDate;

    @NotEmpty
    private List<Long> gameIds;

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

    public List<Long> getGameIds() {
        return gameIds;
    }

    public void setGameIds(List<Long> gameIds) {
        this.gameIds = gameIds;
    }
}
