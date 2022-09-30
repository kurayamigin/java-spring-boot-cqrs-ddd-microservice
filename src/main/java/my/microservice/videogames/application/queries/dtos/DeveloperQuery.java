package my.microservice.videogames.application.queries.dtos;

import my.microservice.videogames.cross_cutting.validators.ValidDate;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Document(collection = "developer")
public class DeveloperQuery extends Query<Long> {

    private String name;

    private Date founded;

    @DBRef
    private CompanyQuery parentCompanyId;

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

    public CompanyQuery getParentCompanyId() {
        return parentCompanyId;
    }

    public void setParentCompanyId(CompanyQuery parentCompanyId) {
        this.parentCompanyId = parentCompanyId;
    }
}
