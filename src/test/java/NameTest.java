import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.github.noahstillwell.Name;

public class NameTest {
    
    @Test
    void matchTest() {
        Name name1 = new Name("firstName1", "LastName1");
        Name name2 = new Name("firstName2", "LastName2");

        assertTrue(name1.match(name1));
        assertFalse(name1.match(name2));
    }

    @Test
    void isLessThanTest() {
        Name name1 = new Name("A", "A");
        Name name2 = new Name("B", "B");
        Name name3 = new Name("B", "A");
        Name name4 = new Name("A", "B");

        assertTrue(name1.isLessThan(name2));
        assertFalse(name2.isLessThan(name1));
        assertFalse(name3.isLessThan(name1));
        assertTrue(name4.isLessThan(name2));
    }
}
