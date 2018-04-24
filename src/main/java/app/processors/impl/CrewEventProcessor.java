package app.processors.impl;

import app.diagnostics.CloudWatchMetricsReporter;
import app.domain.event.EventMessage;
import app.domain.event.payload.impl.CrewPayload;
import app.domain.response.EventResponse;
import app.processors.EventProcessor;
import app.profile.Profiles;
import app.profile.conditions.CrewCondition;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Conditional(CrewCondition.class)
public class CrewEventProcessor implements EventProcessor {

    private static Log LOGGER = LogFactory
            .getLog(CrewEventProcessor.class);

    @Autowired
    private CloudWatchMetricsReporter cloudWatchMetricsReporter;

    @PostConstruct
    public void init() {
        System.out.println(this.getClass().getSimpleName() + " created");
    }

    @Override
    public EventResponse process(EventMessage request) {
        cloudWatchMetricsReporter.registerReceivedEvent(request);
        EventResponse response = new EventResponse();
        if (request == null) {
            response.setMessage("The request is NULL");
            cloudWatchMetricsReporter.registerFailedEvent(request);
            return response;
        }
        String message = "EventMessage processed by: " + Profiles.CREW_PROFILE;
        int requestSize = request.getMessage() == null ? 0 : request.getMessage().length();
        if (request.getEventPayload() instanceof CrewPayload) {
            CrewPayload crewPayload = (CrewPayload) request.getEventPayload();
            message += " OK [" + crewPayload.toString() + "]";
            cloudWatchMetricsReporter.registerSuccessfulEvent(request);
        } else {
            message += " FAIL [" + request.getEventPayload().toString() + "] class: " + request.getEventPayload().getClass().getCanonicalName();
            cloudWatchMetricsReporter.registerFailedEvent(request);
        }
        response = new EventResponse(request.getEventId(), message, requestSize);
        return response;
    }
}
