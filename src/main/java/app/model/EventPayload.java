package app.model;

import app.model.payloads.CrewPayload;
import app.model.payloads.FlightPayload;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

@JsonTypeName("eventPayload")
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CrewPayload.class, name = "crewPayload"),
        @JsonSubTypes.Type(value = FlightPayload.class, name = "flightPayload"),})
@JsonDeserialize()
public class EventPayload implements Serializable {

    protected EventPayload() {

    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
