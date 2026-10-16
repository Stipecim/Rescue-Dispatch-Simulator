import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class DispatchCentre {

    private ArrayList<Incident> incidents;
    private Deque<Incident> dispatchHistory;

    public DispatchCentre() {
        incidents = new ArrayList<>();
        dispatchHistory = new ArrayDeque<>();

    }

    public void addIncident(Incident incident) {

        if(searchById(incident.getIncidentId()) == null)
            incidents.add(incident);
        else
            throw new DispatchException("There is already incident with the same ID");
    }



    private Incident findNextIncident() {

        if (incidents.isEmpty()) return null;
        Incident highest = incidents.get(0);

        for (Incident incident : incidents) {

            // Tie-brak rule is if next incident equals current highest current highest still
            // takes precedence due to being added before to the waiting list.
            if (highest.getSeverity() < incident.getSeverity()) {
                highest = incident;
            }

        }

        return highest;
    }

    public Incident dispatchNextIncident() {

        Incident incident = findNextIncident();

        if (incident != null) {
            incident.setStatus(IncidentStatus.DISPATCHED);
            dispatchHistory.push(incident);
            incidents.remove(incident);

            return incident;
        }

        return null;
    }

    public Incident undoLastDispatch() {

        Incident incident = dispatchHistory.poll();

        if (incident != null) {
            incident.setStatus(IncidentStatus.WAITING);
            addIncident(incident);

            return incident;
        }

        return null;
    }

    public Incident searchById(int incidentId) {

        for (Incident incident : incidents) {
            if (incident.getIncidentId() == incidentId)
                return incident;
        }

        for (Incident incident : dispatchHistory) {
            if (incident.getIncidentId() == incidentId)
                return incident;
        }

        return null;
    }

    public String showSessionSummary() {
        return String.format(
            "%nWaiting incidents:      %d%n" +
            "Completed dispatches:   %d%n" +
            "MEDICAL:                %d%n" +
            "FIRE:                   %d%n" +
            "TRAFFIC:                %d%n" +
            "OTHER:                  %d%n",
            incidents.size(),
            dispatchHistory.size(),
            dispatchesByIncidentType(IncidentType.MEDICAL),
            dispatchesByIncidentType(IncidentType.FIRE),
            dispatchesByIncidentType(IncidentType.TRAFFIC),
            dispatchesByIncidentType(IncidentType.OTHER)
        );
    }

    private int dispatchesByIncidentType(IncidentType incidentType) {

        int incidentTypeCounter = 0;

        for (Incident incident : incidents) {
            if (incident.getType() == incidentType) incidentTypeCounter ++;
        }

        for (Incident dispatch : dispatchHistory) {
            if (dispatch.getType() == incidentType) incidentTypeCounter ++;
        }

        return incidentTypeCounter;
    }

    public ArrayList<Incident> getIncidents() {
        return incidents;
    }

    public Deque<Incident> getDispatchHistory() {
        return dispatchHistory;
    }

    public boolean Exit(Scanner scanner) {

        String response = scanner.nextLine();

        while (true) {

            System.out.println(
                "Are you sure you want to terminate this program? (y/n)"
            );

            response = scanner.nextLine();

            if (response.equalsIgnoreCase("n")) {
                return false;
            }

            else if (response.equalsIgnoreCase("y")) {
                return true;
            }

            else {
                System.out.println("Input must be y or n.");
            }
        }
    }
}
