public class Incident {

    private int incidentId;
    private String location;
    private IncidentType type;
    private int severity;
    private IncidentStatus status;

    public Incident(int incidentId, String location, IncidentType type,
                    int severity, IncidentStatus status)
    {

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
