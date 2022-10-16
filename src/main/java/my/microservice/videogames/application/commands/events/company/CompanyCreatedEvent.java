package my.microservice.videogames.application.commands.events.company;

import my.artifacts.Event;
import my.microservice.videogames.infrastructure.events.CompanyEvents;
import my.microservice.videogames.domain.models.Company;

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
