package app.domain.event.payload.impl;

import app.domain.event.payload.EventPayload;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("flightPayload")
public class FlightPayload extends EventPayload {

    private int flightNumber;
    private String departureStation;
    private String arrivalStation;

    @JsonCreator
    public FlightPayload(@JsonProperty("flightNumber") int flightNumber, @JsonProperty("departureStation") String departureStation, @JsonProperty("arrivalStation") String arrivalStation) {
        super();
        this.flightNumber = flightNumber;
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    @Override
    public String toString() {
        return "FlightPayload{" +
                "flightNumber=" + flightNumber +
                ", departureStation='" + departureStation + '\'' +
                ", arrivalStation='" + arrivalStation + '\'' +
                '}';
    }
}
