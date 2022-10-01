package com.eindopdracht_into_art.models.entities;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    private String username;

    private String password;

    private String email;

    private boolean locked;
    //
    private boolean enabled;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "users_authorities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authorities_id")
    )
    private Set<Authority> authorities = new HashSet<>();

    public void addAuthorities(Authority authorities) {
        this.authorities.add(authorities);
    }

    //region Getters
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean isLocked() {
        return locked;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }
    //endregion

    //region Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //endregion

}
