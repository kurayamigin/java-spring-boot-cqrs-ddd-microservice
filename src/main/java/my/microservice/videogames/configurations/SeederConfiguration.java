package my.microservice.videogames.configurations;

import my.microservice.videogames.infrastructure.seeding.ISeeder;
import my.microservice.videogames.infrastructure.seeding.SeederAnnotation;
import org.springframework.context.ApplicationContext;

import java.util.Map;

public class SeederConfiguration {
    public static void seed(ApplicationContext context) {
        Map<String, Object> seeders = context.getBeansWithAnnotation(SeederAnnotation.class);
        for (Object o : seeders.values()) {
            ISeeder seeder = (ISeeder) o;
            seeder.seed();
        }
    }
}
