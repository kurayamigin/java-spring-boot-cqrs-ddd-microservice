package my.microservice.videogames.application.queries.services;

import my.microservice.videogames.application.queries.dtos.*;
import my.microservice.videogames.application.queries.services.abstractions.IGeneralQueryService;
import my.microservice.videogames.domain.models.*;
import my.microservice.videogames.infrastructure.mappers.*;
import my.microservice.videogames.infrastructure.repositories.commands.*;
import my.microservice.videogames.infrastructure.repositories.queries.*;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GeneralQueryService implements IGeneralQueryService {

    private final IBackupQueryRepository backupRepository;
    private final ApplicationContext context;

    public GeneralQueryService(IBackupQueryRepository backupRepository, ApplicationContext context) {
        this.backupRepository = backupRepository;
        this.context = context;
    }

    @Override
    @Transactional
    public void syncQueryStorage() {

        // update companies
        ICompanyQueryRepository companyQueryRepository = (ICompanyQueryRepository) context.getBean("ICompanyQueryRepository");
        companyQueryRepository.deleteAll();
        ICompanyRepository companyRepository = (ICompanyRepository) context.getBean("ICompanyRepository");
        ICompanyMapper companyMapper = (ICompanyMapper) context.getBean("ICompanyMapperImpl");
        List<Company> companies = (List<Company>) companyRepository.findAll();
        List<CompanyQuery> companyQueries = companyMapper.toQuery(companies);
        companyQueryRepository.saveAll(companyQueries);

        // update developers
        IDeveloperRepository developerRepository = (IDeveloperRepository) context.getBean("IDeveloperRepository");
        IDeveloperQueryRepository developerQueryRepository = (IDeveloperQueryRepository) context.getBean("IDeveloperQueryRepository");
        developerQueryRepository.deleteAll();
        IDeveloperMapper developerMapper = (IDeveloperMapper) context.getBean("IDeveloperMapperImpl");
        List<Developer> developers = (List<Developer>) developerRepository.findAll();
        List<DeveloperQuery> developerQueries = developerMapper.toQuery(developers);
        developerQueryRepository.saveAll(developerQueries);
        
        // update publisher
        IPublisherRepository publisherRepository = (IPublisherRepository) context.getBean("IPublisherRepository");
        IPublisherQueryRepository publisherQueryRepository = (IPublisherQueryRepository) context.getBean("IPublisherQueryRepository");
        publisherQueryRepository.deleteAll();
        IPublisherMapper publisherMapper = (IPublisherMapper) context.getBean("IPublisherMapperImpl");
        List<Publisher> publishers = (List<Publisher>) publisherRepository.findAll();
        List<PublisherQuery> publisherQueries = publisherMapper.toQuery(publishers);
        publisherQueryRepository.saveAll(publisherQueries);

        // update games
        IGameRepository gameRepository = (IGameRepository) context.getBean("IGameRepository");
        IGameQueryRepository gameQueryRepository = (IGameQueryRepository) context.getBean("IGameQueryRepository");
        gameQueryRepository.deleteAll();
        IGameMapper gameMapper = (IGameMapper) context.getBean("IGameMapperImpl");
        List<Game> games = gameRepository.findAll();
        List<GameQuery> gameQueries = gameMapper.toQuery(games);
        gameQueryRepository.saveAll(gameQueries);

        // update series
        ISeriesRepository seriesRepository = (ISeriesRepository) context.getBean("ISeriesRepository");
        ISeriesQueryRepository seriesQueryRepository = (ISeriesQueryRepository) context.getBean("ISeriesQueryRepository");
        seriesQueryRepository.deleteAll();
        ISeriesMapper seriesMapper = (ISeriesMapper) context.getBean("ISeriesMapperImpl");
        List<Series> series = (List<Series>) seriesRepository.findAll();
        List<SeriesQuery> seriesQueries = seriesMapper.toQuery(series);
        seriesQueryRepository.saveAll(seriesQueries);

        //save backup information
        backupRepository.save(new BackupQuery());
    }
}
