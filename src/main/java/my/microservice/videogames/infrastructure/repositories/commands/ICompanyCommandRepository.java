package my.microservice.videogames.infrastructure.repositories.commands;

import my.microservice.videogames.domain.models.Company;
import my.microservice.videogames.domain.models.Game;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompanyCommandRepository extends ICommandRepository<Company, Long> {
}
