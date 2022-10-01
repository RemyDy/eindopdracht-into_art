package com.eindopdracht_into_art.models.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Entity
@Table(name = "authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long userId;

    private String authority;

    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;

    public Authority(Long userId, String authority) {
        this.userId = userId;
        this.authority = authority;
    }

    protected Authority() { // JPA vereist een public of protected no-args constructor
    }

    //region Getters

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getAuthority() {
        return authority;
    }

    public Set<User> getUsers() {
        return users;
    }

    //endregion

    //region Setters

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void setRole(String authority) {
        this.authority = authority;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    //endregion

    public enum Role {
        USER,
        ADMIN,
        ARTIST
    }
}
