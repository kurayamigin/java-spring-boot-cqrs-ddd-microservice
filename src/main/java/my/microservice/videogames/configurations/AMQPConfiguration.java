package my.microservice.videogames.configurations;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AMQPConfiguration {
    // Default queue
    @Bean
    @Primary
    public Queue queue() {
        return new Queue("default");
    }

    @Bean
    public RabbitTemplate template(ConnectionFactory factory) {
        return new RabbitTemplate(factory);
    }

}
