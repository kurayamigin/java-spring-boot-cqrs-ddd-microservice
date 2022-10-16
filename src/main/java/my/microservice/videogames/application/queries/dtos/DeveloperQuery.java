package my.microservice.videogames.application.queries.dtos;

import my.artifacts.Query;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "developer")
public class DeveloperQuery extends Query<Long> {

    private String name;

    private Date founded;

    @DBRef
    private CompanyQuery parentCompany;

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

    public CompanyQuery getParentCompany() {
        return parentCompany;
    }

    public void setParentCompany(CompanyQuery parentCompany) {
        this.parentCompany = parentCompany;
    }
}
