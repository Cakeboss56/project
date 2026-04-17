import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import com.github.noahstillwell.Name;
import com.github.noahstillwell.PatientIdentity;
import com.github.noahstillwell.Patient;
import com.github.noahstillwell.PatientList;

public class PatientListTest {

    @Test
    void addPatientTest() {
        Name name1 = new Name("firstName1", "lastName1");
        Name name2 = new Name("fistName2", "lastName2");
        Date date1 = new Date(1);
        Date date2 = new Date(2);
        PatientIdentity patientIdentity1 = new PatientIdentity(name1, date1);
        PatientIdentity patientIdentity2 = new PatientIdentity(name2, date2);
        Patient patient1 = new Patient(patientIdentity1);
        Patient patient2 = new Patient(patientIdentity2);
        PatientList patientList = new PatientList();

        assertTrue(patientList.addPatient(patient2));
        assertTrue(patientList.addPatient(patient1));
        assertTrue(patientList.getPatient(0) == patient1);
    }

    @Test
    void findPatientTest() {
        Name name1 = new Name("firstName1", "lastName1");
        Name name2 = new Name("fistName2", "lastName2");
        Date date1 = new Date(1);
        Date date2 = new Date(2);
        PatientIdentity patientIdentity1 = new PatientIdentity(name1, date1);
        PatientIdentity patientIdentity2 = new PatientIdentity(name2, date2);
        Patient patient1 = new Patient(patientIdentity1);
        Patient patient2 = new Patient(patientIdentity2);
        PatientList patientList = new PatientList();

        patientList.addPatient(patient1);
        patientList.addPatient(patient2);

        assertTrue(patientList.findPatient(patientIdentity1) == patient1);
        assertTrue(patientList.findPatient(patientIdentity2) == patient2);
    }

    @Test
    void initializeIterationTest() {
        Name name1 = new Name("firstName1", "lastName1");
        Name name2 = new Name("fistName2", "lastName2");
        Date date1 = new Date(1);
        Date date2 = new Date(2);
        PatientIdentity patientIdentity1 = new PatientIdentity(name1, date1);
        PatientIdentity patientIdentity2 = new PatientIdentity(name2, date2);
        Patient patient1 = new Patient(patientIdentity1);
        Patient patient2 = new Patient(patientIdentity2);
        PatientList patientList = new PatientList();

        patientList.initializeIndexOfIteration();
        assertTrue(patientList.getIndexOfIteration() == -1);

        patientList.addPatient(patient1);
        patientList.addPatient(patient2);

        patientList.initializeIndexOfIteration();
        assertTrue(patientList.getIndexOfIteration() == 0);
    }

    @Test
    void nextPatientTest() {
        Name name1 = new Name("firstName1", "lastName1");
        Name name2 = new Name("fistName2", "lastName2");
        Date date1 = new Date(1);
        Date date2 = new Date(2);
        PatientIdentity patientIdentity1 = new PatientIdentity(name1, date1);
        PatientIdentity patientIdentity2 = new PatientIdentity(name2, date2);
        Patient patient1 = new Patient(patientIdentity1);
        Patient patient2 = new Patient(patientIdentity2);
        PatientList patientList = new PatientList();

        assertNull(patientList.nextPatient());

        patientList.addPatient(patient2);
        patientList.addPatient(patient1);
        patientList.initializeIndexOfIteration();

        assertNotNull(patientList.nextPatient());
    }

    @Test
    void saveToFileTest() {
        Name name1 = new Name("firstName1", "lastName1");
        Name name2 = new Name("fistName2", "lastName2");
        Date date1 = new Date(1);
        Date date2 = new Date(2);
        PatientIdentity patientIdentity1 = new PatientIdentity(name1, date1);
        PatientIdentity patientIdentity2 = new PatientIdentity(name2, date2);
        Patient patient1 = new Patient(patientIdentity1);
        Patient patient2 = new Patient(patientIdentity2);
        PatientList patientList = new PatientList();

        patientList.addPatient(patient1);
        patientList.addPatient(patient2);

        assertTrue(patientList.savePatients("testFile.csv"));
    }

    @Test
    void loadFromFileTest() {
        PatientList patientList = new PatientList();

        assertTrue(patientList.importPatients("patients1000.csv"));
    }
}