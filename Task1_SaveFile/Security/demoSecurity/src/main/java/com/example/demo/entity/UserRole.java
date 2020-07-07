package com.example.demo.entity;


import javax.persistence.*;

@Entity
@Table(name = "User_Role")
public class UserRole {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_Id", nullable = false)
     private AppUser appUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Role_Id", nullable = false)
    private AppRole appRole;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public AppRole getAppRole() {
        return appRole;
    }

    public void setAppRole(AppRole appRole) {
        this.appRole = appRole;
    }

}

