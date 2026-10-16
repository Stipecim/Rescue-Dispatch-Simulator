import static org.junit.Assert.*;
import org.junit.Test;

public class DispatchCentreTest {

    private void addSampleIncidents(DispatchCentre dc) {

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

    @Test
    public void addValidIncident() {

        DispatchCentre dc = new DispatchCentre();

        addSampleIncidents(dc);

        Incident incident = new Incident(
            20,
            "Test Location",
            IncidentType.MEDICAL,
            4,
            IncidentStatus.WAITING
        );

        dc.addIncident(incident);

        assertEquals(incident, dc.searchById(20));
    }

    @Test(expected = DispatchException.class)
    public void rejectsDuplicateId() {
        DispatchCentre dc = new DispatchCentre();

        addSampleIncidents(dc);

        Incident incident = new Incident(
            1,
            "Test Street",
            IncidentType.FIRE,
            4,
            IncidentStatus.WAITING
        );

        dc.addIncident(incident);
    }

    @Test
    public void dispatchHighestSeverity() {

        DispatchCentre dc = new DispatchCentre();

        addSampleIncidents(dc);

        Incident incident = dc.dispatchNextIncident();

        assertEquals(5, incident.getIncidentId());
        
    }

    @Test 
    public void firstAddedWinsBreakTie() {

        DispatchCentre dc = new DispatchCentre();


        dc.addIncident(new Incident(
            20,
            "First Street",
            IncidentType.FIRE,
            5,
            IncidentStatus.WAITING
        ));

        dc.addIncident(new Incident(
            10,
            "Second Street",
            IncidentType.MEDICAL,
            5,
            IncidentStatus.WAITING
        ));

        Incident incident = dc.dispatchNextIncident();

        assertEquals(20, incident.getIncidentId());
    }

    @Test
    public void undoLastIncidentOnDispatchHistory() {

        DispatchCentre dc = new DispatchCentre();

        addSampleIncidents(dc);

        Incident incident = dc.undoLastDispatch();

        assertEquals(4, incident.getIncidentId());
    }

    @Test
    public void dispatchWhenNoWaitingIncidents() {

        DispatchCentre dc = new DispatchCentre();

        Incident incident = dc.dispatchNextIncident();

        assertEquals(null, incident);
    }

    @Test 
    public void undohWhenNoDispatchedIncidents() {

        DispatchCentre dc = new DispatchCentre();

        Incident incident = dc.undoLastDispatch();

        assertEquals(null, incident);
    }

    @Test(expected = DispatchException.class)
    public void rejectsInvalidSeverity() {

        new Incident(
            20,
            "Test Street",
            IncidentType.FIRE,
            7,
            IncidentStatus.WAITING
        );
    }
}