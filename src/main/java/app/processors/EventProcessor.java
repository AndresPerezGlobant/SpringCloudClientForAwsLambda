package app.processors;

import app.model.EventRequest;
import app.model.EventResponse;

public interface EventProcessor {
    EventResponse process(EventRequest request);
}
