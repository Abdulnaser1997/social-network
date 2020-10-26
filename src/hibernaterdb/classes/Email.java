/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernaterdb.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author User
 */
@Entity
@Table(name="person_emailaddress")
public class Email {
    @Id
    @Column(name = "emailaddress")
    private String emailAddress;
    @ManyToOne
    @JoinColumn(name = "personid")
    private person person;

    public person getPerson() {
        return person;
    }

    public void setPerson(person person) {
        this.person = person;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Email() {}

    public Email(person person, String emailAddress) {
        this.person = person;
        this.emailAddress = emailAddress;
    }
}

    

