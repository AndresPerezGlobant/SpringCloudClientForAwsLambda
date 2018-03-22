package app.model.payloads;

import app.model.EventPayload;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("crewPayload")
public class CrewPayload extends EventPayload {

    private int crewNumber;
    private String crewType;
    private String crewName;

    @JsonCreator
    public CrewPayload(@JsonProperty("crewNumber") int crewNumber, @JsonProperty("crewType") String crewType, @JsonProperty("crewName") String crewName) {
        super();
        this.crewNumber = crewNumber;
        this.crewType = crewType;
        this.crewName = crewName;
    }

    public int getCrewNumber() {
        return crewNumber;
    }

    public void setCrewNumber(int crewNumber) {
        this.crewNumber = crewNumber;
    }

    public String getCrewType() {
        return crewType;
    }

    public void setCrewType(String crewType) {
        this.crewType = crewType;
    }

    public String getCrewName() {
        return crewName;
    }

    public void setCrewName(String crewName) {
        this.crewName = crewName;
    }

    @Override
    public String toString() {
        return "CrewPayload{" +
                "crewNumber=" + crewNumber +
                ", crewType='" + crewType + '\'' +
                ", crewName='" + crewName + '\'' +
                '}';
    }
}
