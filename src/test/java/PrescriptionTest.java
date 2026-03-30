import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import com.github.noahstillwell.Prescription;

public class PrescriptionTest {

    @Test
    void toStringTest() {
        String name = "name";
        Date dateOfIssue = new Date(0);
        int dosage = 1;
        String prescriber = "prescriber";
        Prescription prescription = new Prescription(name, dateOfIssue, dosage, prescriber);

        String prescriptionString = prescription.toString();

        assertEquals("name, 1969-12-31, 1, prescriber", prescriptionString);
    }

    @Test
    void makePrescriptionTest() {
        String line = "null, null, null, name, 1969-12-31, 1, prescriber";
        Prescription prescription = Prescription.makePrescription(line);

        assertNotNull(prescription);
    }
}
