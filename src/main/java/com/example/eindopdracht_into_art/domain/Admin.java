package com.example.eindopdracht_into_art.domain;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Admin extends User {

    @Override
    public Role assign(UserFactory.RoleType type) {
        return null;
    }

    @Override
    public void assign() {

    }

//    @Override
//    public void assign(UserFactory.RoleType type) {
//        var role = UserFactory.RoleType.ADMIN;
//    }
}
