import java.util.ArrayList;

public class ConsoleMenu {

    public static void display(ArrayList<Incident> incidents) {

        System.out.println();

        printWaitingIncidents(incidents);

        System.out.println();

        printDispatchedIncidents(incidents);
    }

    public static void printWaitingIncidents(ArrayList<Incident> incidents) {

        System.out.println("Waiting incidents");
        System.out.println("-----------------");
        for (Incident incident : incidents) {

            if (incident.getStatus().equals(IncidentStatus.WAITING)){
                printIncident(incident);
            }
        }
    }

    public static void printDispatchedIncidents(ArrayList<Incident> incidents) {

        System.out.println("Dispatched incidents");
        System.out.println("-----------------");
        for (Incident incident : incidents) {

            if (incident.getStatus().equals(IncidentStatus.DISPATCHED)){
                printIncident(incident);
            }
        }
    }

    private static void printIncident (Incident incident) {
        System.out.printf(
            "%d | %s | %s | severity %d%n",
            incident.getIncidentId(),
            incident.getLocation(),
            incident.getType(),
            incident.getSeverity()
        );
    }

}
