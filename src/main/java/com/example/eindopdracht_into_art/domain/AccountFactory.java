package com.example.eindopdracht_into_art.domain;

public interface AccountFactory {

    Account create();

    static AccountFactory create(AccountType type) {
        // Replace if-statement with switch when multiple types are implemented

        if (type == AccountType.USER) {
            new UserFactory();
        }
        throw new IllegalArgumentException();
    }
}

enum AccountType {
    USER,
    //  SUPPLIER will be implemented in future release
}
