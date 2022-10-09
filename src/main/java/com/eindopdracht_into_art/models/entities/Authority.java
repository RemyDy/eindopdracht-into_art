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

    private String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

    @ManyToMany(mappedBy = "authorities")
    private Set<UserAccount> userAccounts;

    protected Authority() {
    }

    //region Getters
    public Long getId() {
        return id;
    }

    public String getAuthority() {
        return authority;
    }

    public Set<UserAccount> getUserAccounts() {
        return userAccounts;
    }
    //endregion
}
