package com.betamedia.qe.af.common.entities;

/**
 * Created by mbelyaev on 3/29/17.
 */
public class FindBy {
    public final String how;
    public final String value;

    public FindBy(String how, String value) {
        this.how = how;
        this.value = value;
    }
}