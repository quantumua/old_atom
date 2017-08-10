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
 * @author Leonid Artemiev
 * @since 7/11/17
 */
@Entity
@Table(name = "ContactBase")
public class ContactBase {
    
    private static final String BIRTH_DATE_FORMAT = "MM/dd/yyyy";

    @Id
    @Column(name = "ContactId")
    private String contactId;
    @Column(name = "DoNotBulkEMail")
    private String doNotBulkEMail;
    @Column(name = "EMailAddress1")
    private String emailAddress1;
	@Column(name = "BirthDate", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthDate;
    @Column(name = "DoNotPhone")
    private String doNotPhone;

     public String getBirthDate() {
       SimpleDateFormat format = new SimpleDateFormat(BIRTH_DATE_FORMAT);
       return format.format(birthDate);
     }
    public String getEmailAddress1() {
        return emailAddress1;
    }

    public void setEmailAddress1(String emailAddress1) {
        this.emailAddress1 = emailAddress1;
    }

    public String getDoNotBulkEMail() {
        return doNotBulkEMail;
    }

    public void setDoNotBulkEMail(String doNotBulkEMail) {
        this.doNotBulkEMail = doNotBulkEMail;
    }

    public String getDoNotPhone() {
        return doNotPhone;
    }

    public void setDoNotPhone(String doNotPhone) {
        this.doNotPhone = doNotPhone;
    }

    public String getContactId() {
        return contactId;
    }
}
