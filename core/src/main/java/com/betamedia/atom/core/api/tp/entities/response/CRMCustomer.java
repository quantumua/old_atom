package com.betamedia.atom.core.api.tp.entities.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import static com.betamedia.atom.core.api.tp.entities.response.CRMAccount.PlatformType.PANDA;

/**
 * @author Maksym Tsybulskyy
 *         Date: 11/30/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CRMCustomer {

    private String id;
    private String displayId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String userName;
    private String language;
    private CRMAccount[] accounts;

    public CRMCustomer() {
    }

    @JsonCreator
    public CRMCustomer(@JsonProperty("Id") String id, @JsonProperty("DisplayId") String displayId, @JsonProperty("FirstName") String firstName, @JsonProperty("LastName") String lastName,
                       @JsonProperty("Email") String email, @JsonProperty("Phone") String phone, @JsonProperty("UserName") String userName, @JsonProperty("Accounts") CRMAccount[] accounts,
                       @JsonProperty("Language") String language) {
        this.id = id;
        this.displayId = displayId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.userName = userName;
        this.accounts = accounts;
        this.language = language;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayId() {
        return displayId;
    }

    public void setDisplayId(String displayId) {
        this.displayId = displayId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public CRMAccount[] getAccounts() {
        return accounts;
    }

    public void setAccounts(CRMAccount[] accounts) {
        this.accounts = accounts;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public CRMAccount getFXCFDAccount(){
        return Optional.ofNullable(getAccounts())
                .map(Arrays::stream)
                .orElseGet(Stream::empty)
                .filter(acc-> (PANDA.getValue()).equals(acc.getPlatform()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return "CRMCustomer{" +
                "id='" + id + '\'' +
                ", displayId='" + displayId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", userName='" + userName + '\'' +
                ", language='" + language + '\'' +
                ", accounts=" + Arrays.toString(accounts) +
                '}';
    }
}
