import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;
import java.util.InputMismatchException;

public class ConsoleApp {

    public static void main(String[] args) {

        /*
            Program starts here at main loop
        */
        Scanner scanner = new Scanner(System.in);
        DispatchCentre dc = new DispatchCentre();

        if (args.length > 0 && args[0].equalsIgnoreCase("-samples")) {
            addSampleIncidents(dc);
        }

        boolean exit = false;

        String status = "";

        while(!exit) {

            try {
                display(dc, status);

                int userChoice = promptUserMenu(scanner);



                switch (userChoice) {

                    case 1:
                        System.out.println("Create incident:");
                        promptUserCreateIncident(scanner, dc);
                        status = "Incident created successfully.";
                        break;

                    case 2:
                        System.out.println("Dispatch next incident:");
                        if (dc.dispatchNextIncident() != null) {

                            status = "Incident dispatched successfully.";
                            break;
                        }

                        status = "There are no incidents to be dispatched.";
                        break;

                    case 3:
                        System.out.println("Undo last dispatch:");
                        if (dc.undoLastDispatch() != null) {

                            status = "Incident successfully undone!";
                            break;
                        }

                        status = "There are no incidents that can be undone!";
                        break;

                    case 4:
                        System.out.println("Search incident by ID:");
                        Incident incident = promptSearchById(scanner, dc);

                        if (incident != null) {
                            status = incidentToStringFormat(incident);
                            break;
                        }


                        status = "There is no incident with that ID";
                        break;

                    case 5:
                        status = dc.showSessionSummary();
                        break;

                    case 6:
                        System.out.println("Exit");
                        exit = dc.Exit(scanner);
                        break;

                }

            } catch (DispatchException e) {
                status = e.getMessage();
            }

        }
        scanner.close();
        //promptUserCreateIncident(dc);
    }


    private static void promptUserCreateIncident (Scanner scanner, DispatchCentre dc) {

        int incidentId = promptIncidentId(scanner);

        String location = promptLocation(scanner);

        IncidentType incidentType = promptIncidentType(scanner);

        int severity = promptSeverity(scanner);

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

    private static int promptIncidentId(Scanner scanner) {

        int incidentId;

        System.out.print("Enter incident ID: ");

        try {
            incidentId = scanner.nextInt();
        } catch (NumberFormatException e) {
            throw new DispatchException("Number must be an integer.");
        }

        return incidentId;

    }

    private static String promptLocation(Scanner scanner) {
        System.out.print("Enter location: ");

        scanner.nextLine();                                         // This is to prevent reading "/n" which skips user prompt after use of nextInt

        String location = scanner.nextLine();

        return location;
    }

    private static IncidentType promptIncidentType(Scanner scanner) {


        try {

            System.out.print("Enter type (MEDICAL, FIRE, TRAFFIC, OTHER): ");
            IncidentType incidentType = IncidentType.valueOf(scanner.nextLine().trim().toUpperCase());

            return incidentType;

        } catch (IllegalArgumentException e) {

            throw new DispatchException("type must be of (MEDICAL, FIRE, TRAFFIC, OTHER)");

        } catch (NullPointerException e) {

            throw new DispatchException("type name cannot be empty.");

        }

    }

    private static int promptSeverity(Scanner scanner) {

        System.out.print("Enter severity (1-5): ");

        int severity;

        try {
            severity = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new DispatchException("Number must be an integer.");
        }


        return severity;
    }

    private static Incident promptSearchById(Scanner scanner, DispatchCentre dc) {

        Incident incident;
        try {

            int incidentId = scanner.nextInt();

            incident = dc.searchById(incidentId);

        } catch (NumberFormatException e) {
            throw new DispatchException("Number must be an integer.");
        }

        return incident;
    }

    private static void display(DispatchCentre dc, String status) {

        System.out.println();

        printWaitingIncidents(dc.getIncidents());

        System.out.println();

        printDispatchHistory(dc.getDispatchHistory());

        System.out.println();

        System.out.println("status: " + status + "\n");

    }

    private static int promptUserMenu(Scanner scanner) {

        System.out.println("Following options are:");
        System.out.printf(
            "1. Add incident%n" +
            "2. Dispatch next incident%n" +
            "3. Undo last dispatch%n" +
            "4. Search incident by ID%n" +
            "5. Show session summary%n" +
            "6. Exit%n"
        );

        System.out.print("Input: ");

        int choice;

        try {
            choice = scanner.nextInt();

        } catch (InputMismatchException e) {

            scanner.nextLine();                                        // this is due to avoiding a loop

            throw new DispatchException(
                "Make sure the input is a number."
            );
        }

        if (choice > 6 || choice < 0) throw new DispatchException("Make sure sure your input is within option range.");

        return choice;
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

    private static String incidentToStringFormat(Incident incident) {
        return String.format(
            "%d | %s | %s | severity %d | STATE: %s",
            incident.getIncidentId(),
            incident.getLocation(),
            incident.getType(),
            incident.getSeverity(),
            incident.getStatus()
        );
    }

    private static void addSampleIncidents(DispatchCentre dc) {

        dc.addIncident(new Incident(
            1,
            "Union Street",
            IncidentType.FIRE,
            1,
            IncidentStatus.WAITING
        ));

        dc.addIncident(new Incident(
            2,
            "King Street",
            IncidentType.MEDICAL,
            2,
            IncidentStatus.WAITING
        ));

        dc.addIncident(new Incident(
            3,
            "George Street",
            IncidentType.TRAFFIC,
            3,
            IncidentStatus.WAITING
        ));

        dc.addIncident(new Incident(
            4,
            "Market Street",
            IncidentType.OTHER,
            4,
            IncidentStatus.WAITING
        ));

        dc.addIncident(new Incident(
            5,
            "Holburn Street",
            IncidentType.MEDICAL,
            4,
            IncidentStatus.WAITING
        ));

        dc.addIncident(new Incident(
            6,
            "Great Northern Road",
            IncidentType.FIRE,
            5,
            IncidentStatus.WAITING
        ));

        dc.addIncident(new Incident(
            7,
            "Rosemount Place",
            IncidentType.TRAFFIC,
            4,
            IncidentStatus.WAITING
        ));

        dc.addIncident(new Incident(
            8,
            "Queens Road",
            IncidentType.MEDICAL,
            5,
            IncidentStatus.WAITING
        ));

        dc.addIncident(new Incident(
            9,
            "Broad Street",
            IncidentType.FIRE,
            4,
            IncidentStatus.WAITING
        ));

        dc.dispatchNextIncident();
        dc.dispatchNextIncident();
        dc.dispatchNextIncident();
    }

}
