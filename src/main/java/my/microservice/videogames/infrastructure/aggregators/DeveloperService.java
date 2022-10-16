package my.microservice.videogames.infrastructure.aggregators;

import my.microservice.videogames.cross_cutting.exceptions.EntityNotFoundException;
import my.microservice.videogames.domain.models.Developer;
import my.microservice.videogames.domain.models.Publisher;
import my.microservice.videogames.infrastructure.repositories.commands.ICompanyRepository;
import my.microservice.videogames.infrastructure.repositories.commands.IDeveloperRepository;
import my.microservice.videogames.infrastructure.aggregators.abstractions.EntityService;
import my.microservice.videogames.infrastructure.aggregators.abstractions.IDeveloperService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DeveloperService extends EntityService<Developer, Long> implements IDeveloperService {

    private final ICompanyRepository companyRepository;
    public DeveloperService(IDeveloperRepository repository, ICompanyRepository companyRepository) {
        super(Developer.class, repository);
        this.companyRepository = companyRepository;
    }

    protected void validate(Developer developer) throws EntityNotFoundException {
        Long id;
        if ((id = developer.getParentCompany().getId()) == null || !companyRepository.existsById(id))
            throw new EntityNotFoundException(Publisher.class, id);
    }
}
