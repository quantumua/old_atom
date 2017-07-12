package com.betamedia.atom.core.persistence.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Created by Leonid.a on 4/27/17.
 */
@Entity
@Table(name = "ContactBase")
public class ContactBase {

    @Id
    @Column(name = "ContactId")
    private String contactId;
    @Column(name = "EMailAddress1")
    private String emailAddress1;
	@Column(name = "BirthDate", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthdate;
	
     public String getBirthdate() {  
       SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
       return format.format(birthdate);
     }
    public String getEmailAddress1() {
        return emailAddress1;
    }

    public void setEmailAddress1(String emailAddress1) {
        this.emailAddress1 = emailAddress1;
    }
 
}
