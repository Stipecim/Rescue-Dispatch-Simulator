/**
 * Represents an emergency incident and stores the information
 * of incident; id, location, type, severity and status.
 *
 * @param incidentId unique ID of the incident
 * @param location location of the incident
 * @param type type of incident
 * @param severity severity from 1 to 5
 * @param status current incident status
 */
public class Incident {

    private int incidentId;
    private String location;
    private IncidentType type;
    private int severity;
    private IncidentStatus status;


    /**
        * Creates an incident.
    */
    public Incident(int incidentId, String location, IncidentType type,
                    int severity, IncidentStatus status)
    {

        if (location == null || location.isBlank()) {
            throw new DispatchException(
                "Location cannot be blank."
            );
        }

        if (severity < 1 || severity > 5) {
            throw new DispatchException(
                "Severity must be between 1 and 5."
            );
        }

        this.incidentId = incidentId;
        this.location = location;
        this.type = type;
        this.severity = severity;
        this.status = status;
    }

    public int getIncidentId() {
        return incidentId;
    }

    public String getLocation() {
        return location;
    }

    public IncidentType getType() {
        return type;
    }

    public int getSeverity() {
        return severity;
    }

    public IncidentStatus getStatus() {
        return status;
    }

    public void setStatus(IncidentStatus status) {
        this.status = status;
    }
}
