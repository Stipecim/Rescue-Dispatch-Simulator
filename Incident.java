public class Incident {

    private int incidentId;
    private String location;
    private IncidentType type;
    private int severity;
    private IncidentStatus status;

    public Incident(int incidentId, String location, IncidentType type,
                    int severity, IncidentStatus status)
    {

        if (location == null || location.isBlank()) {
            throw new IllegalArgumentException(
                "Location cannot be blank."
            );
        }

        if (severity < 1 || severity > 5) {
            throw new IllegalArgumentException(
                "Severity must be between 1 and 5."
            );
        }

        if (type == null) {
            throw new IllegalArgumentException(
                "Incident type cannot be null."
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

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public void setStatus(IncidentStatus status) {
        this.status = status;
    }
}
