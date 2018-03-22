package app.processors;

import app.model.EventRequest;
import app.model.EventResponse;

public interface EventProcessor<T extends EventRequest> {
    EventResponse process(T request);
}
