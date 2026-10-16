import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * Manages waiting incidents and dispatch history.
 * Provides operations for adding, dispatching, undoing
 * and searching incidents.
 */
public class DispatchCentre {

    private ArrayList<Incident> incidents;
    private Deque<Incident> dispatchHistory;


    /**
     * Creates an empty dispatch centre. Initializes collection and stack.
     */
    public DispatchCentre() {
        incidents = new ArrayList<>();
        dispatchHistory = new ArrayDeque<>();

    }

    /**
     * Adds an incident to the waiting list if its ID is unique.
     *
     * @param incident the incident to add
     * @throws DispatchException if an incident with the same ID already exists
     */
    public void addIncident(Incident incident) {

        if(searchById(incident.getIncidentId()) == null)
            incidents.add(incident);
        else
            throw new DispatchException("There is already incident with the same ID");
    }


    /**
     * Finds the highest-priority incident in the waiting list.
     *
     * @return the next incident to dispatch, or null if none are waiting
     */
    private Incident findNextIncident() {

        if (incidents.isEmpty()) return null;
        Incident highest = incidents.get(0);

        for (Incident incident : incidents) {

            // Tie-break rule:If severity is equal, the incident already stored in
            // highest keeps priority because it was added first. 
            if (highest.getSeverity() < incident.getSeverity()) {
                highest = incident;
            }

        }

        return highest;
    }

    /**
     * Dispatches the next highest-priority waiting incident.
     *
     * @return the dispatched incident, or null if none are waiting
     */
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

    /**
     * Undoes the most recent dispatch and returns the incident
     * to the waiting list.
     *
     * @return the undone incident, or null if there is no dispatch to undo
     */
    public Incident undoLastDispatch() {

        Incident incident = dispatchHistory.poll();

        if (incident != null) {
            incident.setStatus(IncidentStatus.WAITING);
            addIncident(incident);

            return incident;
        }

        return null;
    }

    /**
     * Searches both waiting and dispatched incidents by ID.
     *
     * @param incidentId the incident ID to search for
     * @return the matching incident, or null if none is found
     */
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

    /**
     * Creates a summary of the incidents in the current session.
     *
     * @return a formatted session summary
     */
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

    /**
     * Counts incidents of a specified type.
     *
     * @param incidentType the type of incident to count
     * @return the number of incidents of that type
     */
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


    /**
     * Asks the user to confirm whether the program should terminate.
     *
     * @param scanner scanner used to read user input
     * @return true if the user confirms exit, otherwise false
     */
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
