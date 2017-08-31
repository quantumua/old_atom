package com.betamedia.atom.core.fwdataaccess.repository.util;

/**
 * @author mbelyaev
 * @since 8/11/17
 */
public enum Language {
    GERMAN("DE"),
    ENGLISH("EN"),
    ARABIC("AR"),
    FRENCH("FR"),
    ITALIAN("IT"),
    SPANISH("ES"),
    RUSSIAN("RU"),
    PORTUGUESE("PT"),
    SWEDISH("SV"),
    DUTCH("NL"),
    NORWEGIAN("NB"),
    POLISH("PL");

    public final String code;

    Language(String code) {
        this.code = code;
    }

}
