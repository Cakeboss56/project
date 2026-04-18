package com.github.noahstillwell;

public interface Identity {
    boolean match(Identity otherIdentity);
    boolean isLessThan(Identity otherIdentity);
}
