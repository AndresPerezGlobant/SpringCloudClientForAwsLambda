package app.processors.impl;

import app.config.CrewProfileCondition;
import app.model.EventRequest;
import app.model.EventResponse;
import app.model.payloads.CrewPayload;
import app.processors.EventProcessor;
import app.utilities.Profiles;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Conditional(CrewProfileCondition.class)
public class CrewEventProcessor implements EventProcessor {

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
        String message = "EventRequest processed by: " + Profiles.CREW_PROFILE;
        int requestSize = request.getMessage() == null ? 0 : request.getMessage().length();
        if (request.getEventPayload() instanceof CrewPayload) {
            CrewPayload crewPayload = (CrewPayload) request.getEventPayload();
            message += " OK [" + crewPayload.toString() + "]";
        } else {
            message += " FAIL [" + request.getEventPayload().toString() + "] class: " + request.getEventPayload().getClass().getCanonicalName();
        }
        response = new EventResponse(request.getEventId(), message, requestSize);
        return response;
    }
}
