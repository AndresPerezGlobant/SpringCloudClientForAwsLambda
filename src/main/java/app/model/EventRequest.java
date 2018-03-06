package app.model;

import java.io.Serializable;

public class EventRequest implements Serializable {

    private String eventId;
    private String payload;

    public EventRequest() {
    }

    public EventRequest(String eventId, String payload) {
        this.eventId = eventId;
        this.payload = payload;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("{")
                .append("\"eventId\":" + eventId + ",")
                .append("\"payload\":" + payload)
                .append("}").toString();
    }
}
