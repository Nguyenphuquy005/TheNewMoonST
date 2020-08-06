package com.example.demo.enity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "App_Role")
public class AppRole {
    @Id
    @GeneratedValue
    @Column(name = "Role_Id", nullable = false)
    private Long roleId;

    @Column(name = "Role_Name", length = 30, nullable = false)
    private String roleName;

    @OneToMany(targetEntity = UserRole.class,  mappedBy = "appRole", cascade = CascadeType.ALL)
    List<UserRole> userRoles ;

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
