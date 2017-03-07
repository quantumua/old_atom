package com.betamedia.qe.af.pages.tp.login;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/15/17.
 */
public interface LoginPage /*extends PageOperation<LoginPage>*/ {
    void login(String username, String password);
}
