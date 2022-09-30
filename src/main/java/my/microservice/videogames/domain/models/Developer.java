package my.microservice.videogames.domain.models;

import my.artifacts.models.AuditableLogicEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Developer extends AuditableLogicEntity<Long> {
    private String name;
    private Date founded;

    @Column(name = "website_url")
    private String website;

    @Column(name = "icon_url")
    private String icon;

    @ManyToOne
    @JoinColumn(name = "parent_company_id")
    private Company parentCompany;

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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Company getParentCompany() {
        return parentCompany;
    }

    public void setParentCompany(Company parentCompany) {
        this.parentCompany = parentCompany;
    }
}
