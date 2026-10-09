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

        addSamples();
    }

    public void addIncident(Incident incident) {

        if(searchById(incident.getIncidentId()) != null)
            incidents.add(incident);

        System.out.println("Incident not found.");
    }



    private Incident findNextIncident() {

        if (incidents.isEmpty()) return null;
        Incident highest = incidents.get(0);

        for (Incident incident : incidents) {

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


        return null;
    }

    public void showSessionSummary() {
        System.out.printf(
            "Waiting incidents:      %d%n" +
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

    public boolean Exit() {
        System.out.println("Are you sure you want to terminate this program? (y/n)");
        Scanner scanner = new Scanner(System.in);

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

    private void addSamples() {

        incidents.add(new Incident(
            1,
            "Union Street",
            IncidentType.FIRE,
            5,
            IncidentStatus.WAITING
        ));

        incidents.add(new Incident(
            2,
            "King Street",
            IncidentType.MEDICAL,
            4,
            IncidentStatus.WAITING
        ));

        incidents.add(new Incident(
            3,
            "George Street",
            IncidentType.TRAFFIC,
            3,
            IncidentStatus.WAITING
        ));

        incidents.add(new Incident(
            4,
            "Market Street",
            IncidentType.OTHER,
            2,
            IncidentStatus.WAITING
        ));

        incidents.add(new Incident(
            5,
            "Holburn Street",
            IncidentType.MEDICAL,
            5,
            IncidentStatus.WAITING
        ));

        incidents.add(new Incident(
            6,
            "Great Northern Road",
            IncidentType.FIRE,
            3,
            IncidentStatus.WAITING
        ));

        dispatchHistory.push(new Incident(
            7,
            "Rosemount Place",
            IncidentType.TRAFFIC,
            4,
            IncidentStatus.DISPATCHED
        ));

        dispatchHistory.push(new Incident(
            8,
            "Queens Road",
            IncidentType.MEDICAL,
            5,
            IncidentStatus.DISPATCHED
        ));
    }
}
