import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import com.github.noahstillwell.*;

public class PatientTreeTest {
    @Test
    void ConstructorTest() {
        PatientTree patientTree = new PatientTree();
        assertNotNull(patientTree);
    }

    @Test
    void AddPatientTest() {
        PatientTree patientTree = new PatientTree();

        Name name1 = new Name("firstName1", "lastName1");
        Date dateOfBirth1 = PatientIdentity.parseDate("1000,01,01");
        PatientIdentity patientIdentity1 = new PatientIdentity(name1, dateOfBirth1);
        Patient patient1 = new Patient(patientIdentity1);

        Name name2 = new Name("firstName2", "lastName2");
        Date dateOfBirth2 = PatientIdentity.parseDate("2000, 02, 02");
        PatientIdentity patientIdentity2 = new PatientIdentity(name2, dateOfBirth2);
        Patient patient2 = new Patient(patientIdentity2);

        boolean addedPatient = patientTree.addPatient(patient1);
        assertTrue(addedPatient);
        assertEquals(1, patientTree.getPatientCount());

        addedPatient = patientTree.addPatient(patient1);
        assertFalse(addedPatient);
        assertEquals(1, patientTree.getPatientCount());

        addedPatient = patientTree.addPatient(null);
        assertFalse(addedPatient);
        assertEquals(1, patientTree.getPatientCount());

        addedPatient = patientTree.addPatient(patient2);
        assertTrue(addedPatient);
        assertEquals(2, patientTree.getPatientCount());
    }

    @Test
    void FindPatientTest() {
        PatientTree patientTree = new PatientTree();

        Name name = new Name("firstName", "lastName");
        Date dateOfBirth = PatientIdentity.parseDate("2000,01,01");
        PatientIdentity patientIdentity = new PatientIdentity(name, dateOfBirth);
        Patient patient = new Patient(patientIdentity);

        patientTree.addPatient(patient);
        Patient foundPatient = patientTree.findPatient(patientIdentity);

        assertEquals(patient, foundPatient);

        foundPatient = patientTree.findPatient(null);
        assertNull(foundPatient);
    }
}
