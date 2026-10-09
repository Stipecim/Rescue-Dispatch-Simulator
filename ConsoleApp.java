import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class ConsoleApp {

    public static void main(String[] args) {

        /*
            Program starts here at main loop
        */

        DispatchCentre dc = new DispatchCentre();

        dc.dispatchNextIncident();
        display(dc);
    }


    private void promptUserCreateIncident (DispatchCentre dc) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter incident ID: ");
        int incidentId = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter location: ");
        String location = scanner.nextLine();

        System.out.print("Enter type (MEDICAL, FIRE, TRAFFIC, OTHER): ");
        IncidentType incidentType =
            IncidentType.valueOf(scanner.nextLine().trim().toUpperCase());

        int severity = Integer.parseInt(scanner.nextLine());

        if (severity < 1 || severity > 5) {
            throw new IllegalArgumentException(
                "Severity must be between 1 and 5."
            );
        }

        Incident incident = new Incident(
            incidentId,
            location,
            incidentType,
            severity,
            IncidentStatus.WAITING
        );

        dc.addIncident(incident);

        System.out.println("Incident added successfully.");

    }

    private static void display(DispatchCentre dc) {

        System.out.println();

        printWaitingIncidents(dc.getIncidents());

        System.out.println();

        printDispatchHistory(dc.getDispatchHistory());
    }

    public static void printWaitingIncidents(ArrayList<Incident> incidents) {

        System.out.println("Waiting incidents");
        System.out.println("-----------------");
        if(incidents.isEmpty()) {

            System.out.println("No awaiting incidents.");

        } else {
            for (Incident incident : incidents) {
                printIncident(incident);
            }
        }

    }

    public static void printDispatchHistory(Deque<Incident> incidents) {

        System.out.println("Dispatched incidents");
        System.out.println("-----------------");

        if(incidents.isEmpty()) {

            System.out.println("No dispatched incidents.");

        } else {
            for (Incident incident : incidents) {
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
