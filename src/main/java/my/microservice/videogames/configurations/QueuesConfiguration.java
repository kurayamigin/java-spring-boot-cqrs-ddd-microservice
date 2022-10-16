package my.microservice.videogames.configurations;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueuesConfiguration {
    @Bean("games-queue")
    public Queue gamesQueue() {
        return new Queue("games");
    }
    @Bean("devs-queue")
    public Queue devsQueue() {
        return new Queue("developers");
    }
    @Bean("companies-queue")
    public Queue companiesQueue() {
        return new Queue("companies");
    }
    @Bean("series-queue")
    public Queue seriesQueue() {
        return new Queue("series");
    }
    @Bean("publishers-queue")
    public Queue publishersQueue() {
        return new Queue("publishers");
    }
}
