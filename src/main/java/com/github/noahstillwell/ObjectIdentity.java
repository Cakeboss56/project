package com.github.noahstillwell;

public interface ObjectIdentity {
    boolean match(ObjectIdentity otherIdentity);
    boolean isLessThan(ObjectIdentity otherIdentity);
}
