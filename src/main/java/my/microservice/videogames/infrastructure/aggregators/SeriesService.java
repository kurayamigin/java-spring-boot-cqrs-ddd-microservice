package my.microservice.videogames.infrastructure.aggregators;

import my.microservice.videogames.cross_cutting.exceptions.EntityNotFoundException;
import my.microservice.videogames.domain.models.Series;
import my.microservice.videogames.infrastructure.repositories.commands.ISeriesRepository;
import my.microservice.videogames.infrastructure.aggregators.abstractions.EntityService;
import my.microservice.videogames.infrastructure.aggregators.abstractions.ISeriesService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SeriesService extends EntityService<Series, Long> implements ISeriesService {

    public SeriesService(ISeriesRepository repository) {
        super(Series.class, repository);
    }

    protected void validate(Series series) throws EntityNotFoundException {}
}
