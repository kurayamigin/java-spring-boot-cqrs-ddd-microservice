package my.microservice.videogames.application.queries.dtos;

import my.artifacts.Query;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Document(collection = "game")
public class GameQuery extends Query<Long> {

    private String name;

    @DBRef
    private PublisherQuery publisher;
    @DBRef
    private CompanyQuery company;
    @DBRef
    private DeveloperQuery developer;

    private Set<GenreQuery> genres;

    private Date releaseDate;

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

    public CompanyQuery getCompany() {
        return company;
    }

    public void setCompany(CompanyQuery company) {
        this.company = company;
    }

    public DeveloperQuery getDeveloper() {
        return developer;
    }

    public void setDeveloper(DeveloperQuery developer) {
        this.developer = developer;
    }

    public Set<GenreQuery> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenreQuery> genres) {
        this.genres = genres;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
