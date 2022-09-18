package my.microservice.videogames.domain.entities;

import my.artifacts.models.AuditableLogicEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.net.URI;
import java.util.Date;

@Entity
public class Company extends AuditableLogicEntity<Long> {
    private String name;
    private Date founded;

    @Column(name = "website_url")
    private String website;

    @Column(name = "icon_url")
    private String icon;

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
}
