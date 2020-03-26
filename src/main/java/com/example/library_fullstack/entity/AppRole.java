package com.example.library_fullstack.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class AppRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appRoleId;
    private String role;

    public AppRole(String role) {
        this.role = role;
    }

    public AppRole(){}

    public int getAppRoleId() {
        return appRoleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppRole appRole = (AppRole) o;
        return appRoleId == appRole.appRoleId &&
                Objects.equals(role, appRole.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appRoleId, role);
    }

    @Override
    public String toString() {
        return "AppRole{" +
                "appRoleId=" + appRoleId +
                ", role='" + role + '\'' +
                '}';
    }
}
