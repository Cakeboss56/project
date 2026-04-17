import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import com.github.noahstillwell.*;

public class BinarySearchTreeTest {

    @Test
    void ConstructorTest() {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        assertNotNull(binarySearchTree);
    }

    @Test
    void AddObjectTest() {
        BinarySearchTree binarySearchTree = new BinarySearchTree();

        Name name = new Name("firstName", "lastName");
        Date dateOfBirth = PatientIdentity.parseDate("2000,01,01");
        PatientIdentity patientIdentity = new PatientIdentity(name, dateOfBirth);
        Patient patient = new Patient(patientIdentity);

        binarySearchTree.addObject(patient);
        Patient foundPatient = (Patient) binarySearchTree.findObject(patientIdentity);

        assertEquals(patient, foundPatient);
    }

    @Test
    void FindObjectTest() {
        BinarySearchTree binarySearchTree = new BinarySearchTree();

        Name name = new Name("firstName", "lastName");
        Date dateOfBirth = PatientIdentity.parseDate("2000,01,01");
        PatientIdentity patientIdentity = new PatientIdentity(name, dateOfBirth);
        Patient patient = new Patient(patientIdentity);

        binarySearchTree.addObject(patient);
        Patient foundPatient = (Patient) binarySearchTree.findObject(patientIdentity);

        assertNotNull(foundPatient);
    }
}
