import com.github.noahstillwell.ObjectIdentity;

public class TestIdentity implements ObjectIdentity {
    private Integer integer;

    public TestIdentity(Integer integer) {
        this.integer = integer;
    }

    public boolean isLessThan(TestIdentity other) {
        return this.integer < other.integer;
    }

    @Override
    public boolean isLessThan(ObjectIdentity identity) {
        return isLessThan((TestIdentity) identity);
    }

    public boolean match(TestIdentity other) {
        return this.integer.equals(other.integer);
    }

    @Override
    public boolean match(ObjectIdentity identity) {
        return match((TestIdentity) identity);
    }
}
