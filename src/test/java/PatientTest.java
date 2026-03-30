import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import com.github.noahstillwell.Name;
import com.github.noahstillwell.PatientIdentity;
import com.github.noahstillwell.Patient;

public class PatientTest {

    @Test
    void patientConstructorTest() {
        Name name = new Name("firstName", "lastName");
        Date dateOfBirth = new Date(0);
        PatientIdentity patientIdentity = new PatientIdentity(name, dateOfBirth);

        Patient patient = new Patient(patientIdentity);

        assertTrue(patient.getPatientIdentity().match(patientIdentity));
    }

    @Test
    void toCSVTest() {
        String firstName = "firstName";
        String lastName = "LastName";
        Name name = new Name(firstName, lastName);
        Date dateOfBirth = new Date(0);
        PatientIdentity patientIdentity = new PatientIdentity(name, dateOfBirth);
        Patient patient = new Patient(patientIdentity);
        
        String csv = patient.toCSV();

        assertEquals(lastName + ", " + firstName + ", " + "1969-12-31", csv);
    }

    @Test
    void makePatientTest() {
        String line = "lastName, firstName, 1969-12-31";
        Patient patient = Patient.makePatient(line);
        assertNotNull(patient);
    }
}