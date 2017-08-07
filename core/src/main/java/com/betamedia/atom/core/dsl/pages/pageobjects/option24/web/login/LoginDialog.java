package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.login;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/15/17.
 */
public interface LoginDialog {
    void login(String username, String password);
    
    boolean isSubmitBtnExists();
}
