package com.eindopdracht_into_art.controller.helpers;

import java.net.URI;

import static com.eindopdracht_into_art.controller.helpers.GlobalConstant.BASE_URL_V1;
import static com.eindopdracht_into_art.controller.helpers.GlobalConstant.LOCALHOST8080;

public class UriCreator {
    public static URI createUriById(String endMapping, Long id) {

        return URI.create(
                LOCALHOST8080
                + BASE_URL_V1
                + endMapping
                + "/%s".formatted(id)
        );
    }

}
