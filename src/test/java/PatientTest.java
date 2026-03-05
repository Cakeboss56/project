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
}