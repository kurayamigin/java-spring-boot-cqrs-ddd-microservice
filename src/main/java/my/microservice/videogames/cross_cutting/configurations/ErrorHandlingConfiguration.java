package my.microservice.videogames.cross_cutting.configurations;

import my.microservice.videogames.application.middlewares.ResponseErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ErrorHandlingConfiguration {
    @Bean
    public ResponseErrorAttributes errorAttributes() {
        return new ResponseErrorAttributes();
    }
}
