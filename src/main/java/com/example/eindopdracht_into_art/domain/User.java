package com.example.eindopdracht_into_art.domain;

public abstract class User implements Account, Role {

    public void create() {
    }

    public abstract Role assign(UserFactory.RoleType type);

//    protected abstract void assign(UserFactory.RoleType type);
}
