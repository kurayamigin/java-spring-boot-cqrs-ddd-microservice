package my.microservice.videogames.application.commands.events.company;

import my.microservice.videogames.cross_cutting.cqrs.Event;
import my.microservice.videogames.cross_cutting.events.CompanyEvents;
import my.microservice.videogames.cross_cutting.events.DeveloperEvents;
import my.microservice.videogames.domain.models.Company;
import my.microservice.videogames.domain.models.Developer;

public class CompanyCreatedEvent extends Event {
    private Company company;

    public CompanyCreatedEvent(Company company) {
        super(CompanyEvents.CREATED);
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
