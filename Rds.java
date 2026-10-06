import java.util.ArrayList;

public class Rds {

    public static void main(String[] args) {

        /*
            Program starts here at main loop
        */

        ArrayList<Incident> incidents = new ArrayList<>();

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
            IncidentStatus.DISPATCHED
        ));

        incidents.add(new Incident(
            4,
            "Market Street",
            IncidentType.OTHER,
            2,
            IncidentStatus.WAITING
        ));

        ConsoleMenu.display(incidents);
    }
}
