/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernaterdb.classes;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;

/**
 *
 * @author Abdulnaser Sabra
 */

@Embeddable
public class Person_UniversityID implements Serializable  {
    
  //  @JoinColumn(name = "personid")
    private long personID;
    
  //  @JoinColumn(name = "uniid")
    private int uniID;
    
    
     public Person_UniversityID(){}
    
     public Person_UniversityID(long personid, int uniid) {
        super();
        this.personID = personid;
        this.uniID = uniid;
    }
    

    public long getPersonID() {
        return personID;
    }

    public int getUniID() {
        return uniID;
    }

    public void setPersonID(long personid) {
        this.personID = personid;
    }

    public void setUniID(int uniid) {
        this.uniID = uniid;
    }
    
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        Person_UniversityID that = (Person_UniversityID) o;
        return Objects.equals(personID, that.personID) &&
               Objects.equals(uniID, that.uniID);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(personID, uniID);
    }

   
    
    
    
}
