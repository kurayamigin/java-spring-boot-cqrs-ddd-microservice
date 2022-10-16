package my.microservice.videogames.application.queries.dtos;

import my.artifacts.Query;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "company")
public class CompanyQuery extends Query<Long> {

    private String name;

    private Date founded;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFounded() {
        return founded;
    }

    public void setFounded(Date founded) {
        this.founded = founded;
    }}
