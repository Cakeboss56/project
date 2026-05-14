import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.github.noahstillwell.BinarySearchTree;

public class BinarySearchTreeTest {
    @Test
    public void TestBinarySearchTree() {
        BinarySearchTree<TestObject> tree = new BinarySearchTree<>();

        int objectCount = 100;

        for (int index = 0; index < objectCount; index++) {
            int integer = (index * 37) % objectCount;
            TestIdentity identity = new TestIdentity(integer);
            TestObject object = new TestObject(identity);

            assertTrue(tree.add(object));
        }

        tree.initializeIteration();

        for (int index = 0; index < objectCount; index++) {
            TestObject nextObject = tree.next();
            
            assertNotNull(nextObject);
            assertEquals(index, nextObject.getTestIdentity().getInteger());
        }

        assertNull(tree.next());
    }
}
