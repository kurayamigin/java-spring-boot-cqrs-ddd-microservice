package my.microservice.videogames.application.commands.services;

import com.github.fge.jsonpatch.JsonPatch;
import my.artifacts.abstractions.IPublisher;
import my.microservice.videogames.application.commands.dtos.CompanyCommand;
import my.microservice.videogames.application.commands.events.company.CompanyCreatedEvent;
import my.microservice.videogames.application.commands.events.company.CompanyDeletedEvent;
import my.microservice.videogames.application.commands.events.company.CompanyUpdatedEvent;
import my.microservice.videogames.application.commands.publishers.CompanyPublisher;
import my.microservice.videogames.application.commands.services.abstractions.ICompanyCommandService;
import my.microservice.videogames.cross_cutting.exceptions.EntityNotFoundException;
import my.microservice.videogames.cross_cutting.utils.RestPatchUtils;
import my.microservice.videogames.domain.models.Company;
import my.microservice.videogames.infrastructure.mappers.ICompanyMapper;
import my.microservice.videogames.infrastructure.aggregators.abstractions.ICompanyService;
import org.springframework.stereotype.Service;

@Service
public class CompanyCommandService implements ICompanyCommandService {
    private final ICompanyService service;
    private final ICompanyMapper mapper;
    private final IPublisher publisher;

    public CompanyCommandService(ICompanyService service, ICompanyMapper mapper, CompanyPublisher publisher) {
        this.service = service;
        this.mapper = mapper;
        this.publisher = publisher;
    }

    @Override
    public Long create(CompanyCommand command) {
        Company entity = mapper.toEntity(command);
        service.create(entity);
        publisher.send(new CompanyCreatedEvent(entity));
        return entity.getId();
     }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        service.delete(id);
        publisher.send(new CompanyDeletedEvent(id));
    }

    @Override
    public void patch(Long id, JsonPatch patch) throws EntityNotFoundException {
        Company company = service.get(id);
        CompanyCommand patched = RestPatchUtils.apply(patch, mapper.toCommand(company));
        mapper.patch(patched, company);
        service.update(company);
        publisher.send(new CompanyUpdatedEvent(company));
    }
}
