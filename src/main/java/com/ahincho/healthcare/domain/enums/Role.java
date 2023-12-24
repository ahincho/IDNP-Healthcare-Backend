package com.ahincho.healthcare.domain.enums;

public enum Role {
    ADMIN("ADMIN"),
    VIEWER("VIEWER");
    private final String name;
    Role(String name) {
        this.name = name;
    }
    public String getRoleName() {
        return this.name;
    }
    @Override
    public String toString() {
        return this.name;
    }
}
