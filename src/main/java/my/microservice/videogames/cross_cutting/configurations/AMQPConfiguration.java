package my.microservice.videogames.cross_cutting.configurations;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfiguration {

    @Bean
    public Queue hello() {
        return new Queue("hello");
    }

    @Bean
    public RabbitTemplate template(ConnectionFactory factory) {
        return new RabbitTemplate(factory);
    }
//    @Bean
//    public ISubscriber receiver() {
//        return new GameSubscriber();
//    }

}
