package my.microservice.videogames.application.commands.events.company;

import my.microservice.videogames.cross_cutting.cqrs.Event;
import my.microservice.videogames.cross_cutting.events.CompanyEvents;
import my.microservice.videogames.domain.models.Company;

public class CompanyUpdatedEvent extends Event {
    private Company company;

    public CompanyUpdatedEvent(Company company) {
        super(CompanyEvents.UPDATED);
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}
