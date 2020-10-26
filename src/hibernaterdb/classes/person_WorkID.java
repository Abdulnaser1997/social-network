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
public class person_WorkID implements Serializable {
    
     
     private long personId;
     
     
     private int companyId;
     
     
      public person_WorkID(){}
    
      public person_WorkID(long personid, int companyid) {
          super();
          this.personId = personid;
          this.companyId = companyid;
     }

    public void setPersonID(long personID) {
        this.personId = personID;
    }

    public void setCompanyID(int companyID) {
        this.companyId = companyID;
    }

    public long getPersonID() {
        return this.personId;
    }

    public int getCompanyID() {
        return companyId;
    }
     
     
     
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        person_WorkID that = (person_WorkID) o;
        return Objects.equals(personId, that.personId) &&
               Objects.equals(companyId, that.companyId);
    }
     
     
     
    @Override
    public int hashCode() {
        return Objects.hash(personId, companyId);
    }

     
     
     
     
     
     
     
    
    
    
    
    
    
    
}
