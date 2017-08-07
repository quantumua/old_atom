package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.messages;

/**
 * Created by vsnigur on 5/15/17.
 */
public interface DialogBox {
    public void close();
    public String getTitle();
    public String getMessageText();
    public Boolean exists();
    void assertTitle(String title);
    void assertMessage(String message);
}
