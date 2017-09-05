package com.betamedia.atom.core.api.web.form;

/**
 * Created by vsnigur on 9/4/17.
 */
public enum RGB {
    DUSTY_GRAY("rgb(153, 153, 153)"),
    GRAY("rgb(180, 180, 180)"),
    WHITE("rgb(255, 255, 255)"),
    COD_GRAY("rgb(25, 25, 25)");

    public final String rgbColor;

    RGB(String rgbColor) {
        this.rgbColor = rgbColor;
    }

    public String getCode() {
        return rgbColor;
    }
}
