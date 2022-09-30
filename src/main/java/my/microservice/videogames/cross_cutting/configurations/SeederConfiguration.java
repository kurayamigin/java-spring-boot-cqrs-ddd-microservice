package my.microservice.videogames.cross_cutting.configurations;

import my.microservice.videogames.infrastructure.seeding.CqrsSeeder;
import my.microservice.videogames.infrastructure.seeding.SeederAnnotation;
import org.springframework.context.ApplicationContext;

import java.util.Map;

public class SeederConfiguration {
    public static void seed(ApplicationContext context) {
        Map<String, Object> seeders = context.getBeansWithAnnotation(SeederAnnotation.class);
        for (Object o : seeders.values()) {
            CqrsSeeder<?,?,?> seeder = (CqrsSeeder<?,?,?>) o;
            seeder.seed();
        }
    }
}
