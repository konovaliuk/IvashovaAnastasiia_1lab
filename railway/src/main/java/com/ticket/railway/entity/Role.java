package com.ticket.railway.entity;

import java.util.Objects;

public class Role
{
    private Integer roleId;
    private String nameRole;
    private String description;

    public Role(Integer roleId, String nameRole, String description) {
        this.roleId = roleId;
        this.nameRole = nameRole;
        this.description = description;
    }

    public Role(String nameRole, String description) {
        this.nameRole = nameRole;
        this.description = description;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(roleId, role.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId);
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", nameRole='" + nameRole + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
