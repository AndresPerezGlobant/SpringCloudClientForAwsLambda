package app.domain.event;

import app.domain.event.payload.EventPayload;

import java.io.Serializable;

public class EventMessage implements Serializable {

    private String eventId;
    private String eventType;
    private String message;
    protected EventPayload eventPayload;

    public EventMessage() {
    }

    public EventMessage(String eventId, String eventType, String message, EventPayload eventPayload) {
        this.eventId = eventId;
        this.eventType = eventType;
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

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Override
    public String toString() {
        return "EventMessage{" +
                "eventId='" + eventId + '\'' +
                ", eventType='" + message + '\'' +
                ", message='" + message + '\'' +
                ", eventPayload=" + eventPayload.toString() +
                '}';
    }
}
