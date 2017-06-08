package com.betamedia.atom.core.api.tp.entities.namingstrategies.customer;

/**
 * @author Maksym Tsybulskyy
 *         Date: 6/8/17.
 */
public interface UserNamingStrategy {

    String getFirstName(String firstName);

    String getLastName(String lastName);

    String getEmail(String email);

}
