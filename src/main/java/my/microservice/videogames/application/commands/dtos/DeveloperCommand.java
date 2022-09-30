package my.microservice.videogames.application.commands.dtos;

import my.microservice.videogames.cross_cutting.validators.ValidDate;
import my.microservice.videogames.domain.models.Company;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class DeveloperCommand implements ICommand {

    @NotNull
    @Length(max = 250)
    private String name;

    @ValidDate
    private Date founded;

    @NotNull
    private Long parentCompanyId;

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

    public Long getParentCompanyId() {
        return parentCompanyId;
    }

    public void setParentCompanyId(Long parentCompanyId) {
        this.parentCompanyId = parentCompanyId;
    }
}