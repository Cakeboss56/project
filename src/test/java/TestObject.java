import com.github.noahstillwell.IdentifiedObject;
import com.github.noahstillwell.ObjectIdentity;

public class TestObject implements IdentifiedObject {
    private TestIdentity testIdentity;

    public TestObject(TestIdentity testIdentity) {
        this.testIdentity = testIdentity;
    }

    public TestIdentity getTestIdentity() {
        return this.testIdentity;
    }

    @Override
    public ObjectIdentity getObjectIdentity() {
        return getTestIdentity();
    }
}
