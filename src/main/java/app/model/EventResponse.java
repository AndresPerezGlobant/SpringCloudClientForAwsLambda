package app.model;

import java.io.Serializable;

public class EventResponse implements Serializable {

    private String eventId;
    private String message;
    private int requestSize;

    public EventResponse() {
    }

    public EventResponse(String eventId, String message, int requestSize) {
        this.eventId = eventId;
        this.message = message;
        this.requestSize = requestSize;
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

    public int getRequestSize() {
        return requestSize;
    }

    public void setRequestSize(int requestSize) {
        this.requestSize = requestSize;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("{")
                .append("\"eventId\":" + eventId + ",")
                .append("\"message\":" + message + ",")
                .append("\"requestSize\":" + requestSize)
                .append("}").toString();
    }
}
