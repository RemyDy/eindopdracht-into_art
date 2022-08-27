package com.example.eindopdracht_into_art.domain;

public class UserFactory implements AccountFactory {

    public Account create() {

        return new User() {

            @Override
            public void assign() {

            }

            @Override
            public Role assign(RoleType type) {

                switch (type) {
                    case ARTIST -> new Artist();
                    case SUBSCRIBER -> new Subscriber();
                    case ADMIN -> new Admin();
                }
                throw new IllegalArgumentException();
            }
        };
    }

    enum RoleType {
        ARTIST,
        SUBSCRIBER,
        ADMIN
    }
}
