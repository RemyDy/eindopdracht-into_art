package com.eindopdracht_into_art.config.security;

public class SecurityConstants {
    public static final long EXPIRE_MIN_15 = 1000 * 60 * 15; // 15 mins
    public static long EXPIRE_HRS_24 = 1000 * 60 * 60 * 24; // token is 24 uur geldig (in milliseconds)
    public static final String PREFIX_BEARER = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

}
