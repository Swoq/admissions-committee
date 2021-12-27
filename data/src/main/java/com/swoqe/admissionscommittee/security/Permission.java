package com.swoqe.admissionscommittee.security;

public enum Permission {
    PLAIN_USER_READ("user:read"),
    PLAIN_USER_WRITE("user:write"),
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
