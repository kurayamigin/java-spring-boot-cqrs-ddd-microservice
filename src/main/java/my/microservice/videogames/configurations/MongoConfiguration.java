package my.microservice.videogames.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "my.microservice.videogames.infrastructure.repositories")
public class MongoConfiguration {
}
