package com.github.noahstillwell;

public interface ObjectIdentity {
    boolean match(ObjectIdentity otherObjectIdentity);
    boolean isLessThan(ObjectIdentity otherObjectIdentity);
}
