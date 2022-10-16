package my.microservice.videogames.configurations;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


@Component
public class Startup implements InitializingBean {
    private final ApplicationContext context;

    public Startup(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
       SeederConfiguration.seed(context);
    }
}
