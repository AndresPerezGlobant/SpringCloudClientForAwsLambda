package app.processors.impl;

import app.config.FlightProfileCondition;
import app.model.EventRequest;
import app.model.EventResponse;
import app.processors.EventProcessor;
import app.utilities.Profiles;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Conditional(FlightProfileCondition.class)
public class FlightEventProcessor implements EventProcessor {

    @PostConstruct
    public void init() {
        System.out.println(this.getClass().getSimpleName() + " created");
    }

    @Override
    public EventResponse process(EventRequest request) {
        EventResponse response = new EventResponse();
        if (request == null) {
            response.setMessage("The request is NULL");
            return response;
        }
        String message = "EventRequest processed by: " + Profiles.FLIGHT_PROFILE;
        int requestSize = request.getPayload() == null ? 0 : request.getPayload().length();
        response = new EventResponse(request.getEventId(), message, requestSize);
        return response;
    }
}
