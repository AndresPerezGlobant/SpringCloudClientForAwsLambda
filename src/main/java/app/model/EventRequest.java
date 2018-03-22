package app.model;

import java.io.Serializable;

public class EventRequest implements Serializable {

    private String eventId;
    private String message;
    protected EventPayload eventPayload;

    public EventRequest() {
    }

    public EventRequest(String eventId, String message, EventPayload eventPayload) {
        this.eventId = eventId;
        this.message = message;
        this.eventPayload = eventPayload;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EventPayload getEventPayload() {
        return eventPayload;
    }

    public void setEventPayload(EventPayload eventPayload) {
        this.eventPayload = eventPayload;
    }

    @Override
    public String toString() {
        return "EventRequest{" +
                "eventId='" + eventId + '\'' +
                ", message='" + message + '\'' +
                ", eventPayload=" + eventPayload.toString() +
                '}';
    }
}
