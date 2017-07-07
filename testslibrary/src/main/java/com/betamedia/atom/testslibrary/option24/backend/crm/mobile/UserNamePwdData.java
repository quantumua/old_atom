package com.betamedia.atom.testslibrary.option24.backend.crm.mobile;

import com.betamedia.atom.core.testlink.TestLinkDisplayIdHolder;
import com.opencsv.bean.CsvBindByName;

/**
 * Created by sergeyg on 05.07.17.
 */
public class UserNamePwdData implements TestLinkDisplayIdHolder{
    @CsvBindByName
    private String userName;
    @CsvBindByName
    private String userPwd;
    @CsvBindByName
    private String testLinkDisplayId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getDisplayId() {
        return testLinkDisplayId;
    }

    @Override
    public String toString() {
        return "userName=" + userName + ", userPwd=" + userPwd;
    }
}
