package my.microservice.videogames.domain.entities;

import my.artifacts.models.AuditableLogicEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Serie extends AuditableLogicEntity<Long> {
    private String name;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "icon_url")
    private String icon;

    @OneToMany
    private List<Game> games;

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

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
