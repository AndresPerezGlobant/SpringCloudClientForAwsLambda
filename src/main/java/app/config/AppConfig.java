package app.config;

import app.domain.event.EventMessage;
import app.domain.response.EventResponse;
import app.processors.EventProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class AppConfig {

    @Autowired
    private EventProcessor eventProcessor;

    @Bean
    public Function<EventMessage, EventResponse> function() {
        return eventRequest -> eventProcessor.process(eventRequest);
    }

}
