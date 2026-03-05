import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import com.github.noahstillwell.Name;
import com.github.noahstillwell.PatientIdentity;

public class PatientIdentityTest {

    @Test
    void patientIdentityConstructorTest() {
        Name name = new Name("firstName", "lastName");
        Date dateOfBirth = new Date(0);

        PatientIdentity patientIdentity = new PatientIdentity(name, dateOfBirth);

        assertTrue(patientIdentity.getName().match(name));
        assertTrue(patientIdentity.getDateOfBirth().equals(dateOfBirth));
    }

    @Test
    void patientIdentityMatchTest() {
        Name name1 = new Name("firstName1", "lastName1");
        Name name2 = new Name("firstName2", "lastName2");
        Date dateOfBirth1 = new Date(1);
        Date dateOfBirth2 = new Date(2);

        PatientIdentity patientIdentity1 = new PatientIdentity(name1, dateOfBirth1);
        PatientIdentity patientIdentity2 = new PatientIdentity(name2, dateOfBirth2);

        assertFalse(patientIdentity1.match(patientIdentity2));
        assertTrue(patientIdentity1.match(patientIdentity1));
    }

    @Test
    void patientIdentityIsLessThanTest() {
        Name name1 = new Name("firstName1", "lastName1");
        Name name2 = new Name("firstName2", "lastName2");
        Date dateOfBirth1 = new Date(1);
        Date dateOfBirth2 = new Date(2);

        PatientIdentity patientIdentity1 = new PatientIdentity(name1, dateOfBirth1);
        PatientIdentity patientIdentity2 = new PatientIdentity(name2, dateOfBirth2);
        PatientIdentity patientIdentity3 = new PatientIdentity(name1, dateOfBirth2);

        assertTrue(patientIdentity1.isLessThan(patientIdentity2));
        assertFalse(patientIdentity2.isLessThan(patientIdentity1));
        assertTrue(patientIdentity1.isLessThan(patientIdentity3));
        assertFalse(patientIdentity3.isLessThan(patientIdentity1));
    }
}
