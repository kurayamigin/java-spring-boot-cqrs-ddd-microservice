package my.microservice.videogames.infrastructure.aggregators;

import my.microservice.videogames.cross_cutting.exceptions.EntityNotFoundException;
import my.microservice.videogames.domain.models.Company;
import my.microservice.videogames.infrastructure.repositories.commands.ICompanyRepository;
import my.microservice.videogames.infrastructure.aggregators.abstractions.EntityService;
import my.microservice.videogames.infrastructure.aggregators.abstractions.ICompanyService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CompanyService extends EntityService<Company, Long> implements ICompanyService {

    public CompanyService(ICompanyRepository repository) {
        super(Company.class, repository);
    }

    protected void validate(Company company) throws EntityNotFoundException {}
}
