package my.microservice.videogames.application.queries.dtos;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "publisher")
public class PublisherQuery extends Query<Long> {
    private String name;
    private Date founded;
    private String address;

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
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
