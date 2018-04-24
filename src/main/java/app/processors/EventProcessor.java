package app.processors;

import app.domain.event.EventMessage;
import app.domain.response.EventResponse;

public interface EventProcessor<T extends EventMessage> {
    EventResponse process(T request);
}
