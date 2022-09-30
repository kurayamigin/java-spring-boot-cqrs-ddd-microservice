package my.microservice.videogames.application.commands.services;

import com.github.fge.jsonpatch.JsonPatch;
import my.microservice.videogames.application.commands.dtos.CompanyCommand;
import my.microservice.videogames.application.commands.events.company.CompanyCreatedEvent;
import my.microservice.videogames.application.commands.events.company.CompanyDeletedEvent;
import my.microservice.videogames.application.commands.events.company.CompanyUpdatedEvent;
import my.microservice.videogames.application.commands.services.abstractions.ICompanyCommandService;
import my.microservice.videogames.cross_cutting.cqrs.IPublisher;
import my.microservice.videogames.cross_cutting.exceptions.EntityNotFoundException;
import my.microservice.videogames.cross_cutting.utils.ApplicationContextProvider;
import my.microservice.videogames.cross_cutting.utils.RestPatchUtils;
import my.microservice.videogames.domain.models.Company;
import my.microservice.videogames.infrastructure.mappers.ICompanyMapper;
import my.microservice.videogames.infrastructure.repositories.commands.ICompanyCommandRepository;
import my.microservice.videogames.infrastructure.repositories.queries.ICompanyQueryRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompanyCommandService implements ICompanyCommandService {
    private final ICompanyCommandRepository repository;
    private final ICompanyMapper mapper;
    private final IPublisher publisher;

    public CompanyCommandService(ICompanyCommandRepository repository, ICompanyMapper mapper, IPublisher publisher) {
        this.repository = repository;
        this.mapper = mapper;
        this.publisher = publisher;
    }

    @Override
    public List<CompanyCommand> get() {
        List<Company> entities = (List<Company>) repository.findAll();
        return mapper.toCommand(entities);
    }

    @Override
    public CompanyCommand getById(Long id) throws EntityNotFoundException {
        Optional<Company> company = repository.findById(id);
        return company.map(mapper::toCommand)
                .orElseThrow(() -> new EntityNotFoundException(Company.class, id));
    }

    @Override
    public void create(CompanyCommand command) {
        Company entity = mapper.toEntity(command);
        repository.save(entity);

        publisher.send(new CompanyCreatedEvent(entity));
     }

    @Override
    public void delete(Long id) throws EntityNotFoundException {
        if (!repository.existsById(id)) throw new EntityNotFoundException(Company.class, id);
        repository.deleteById(id);
        publisher.send(new CompanyDeletedEvent(id));
    }

    @Override
    public void patch(Long id, JsonPatch patch) throws EntityNotFoundException {
        Company company = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Company.class, id));
        CompanyCommand patched = RestPatchUtils.apply(patch, mapper.toCommand(company));
        mapper.patch(patched, company);
        repository.save(company);
        publisher.send(new CompanyUpdatedEvent(company));
    }
}
