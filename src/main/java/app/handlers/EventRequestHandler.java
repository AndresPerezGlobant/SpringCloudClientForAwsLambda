package app.handlers;

import app.model.EventRequest;
import app.model.EventResponse;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

public class EventRequestHandler extends SpringBootRequestHandler<EventRequest, EventResponse> {
}
