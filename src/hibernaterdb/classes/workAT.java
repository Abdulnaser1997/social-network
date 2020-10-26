/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernaterdb.classes;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 *
 * @author Abdulnaser Sabra
 */

@Entity
@Table(name="workat")
public class workAT {
    
     @EmbeddedId
     private person_WorkID id;
    
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("personId")
    @JoinColumn(name ="personid")
    private person person;
    
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("companyId")
    @JoinColumn(name = "companyid")
    private Company company;

    @Column(name ="workyear")
    private int classYear;
     
    public workAT(){}
    public workAT(person person, Company company, int classYear) {
        this.person = person;
        this.company = company;
        this.classYear = classYear;
        this.id = new person_WorkID(person.getPersonid(),company.getID());
    }

    public void setId(person_WorkID id) {
         this.id = id;
    }

    public void setPerson(person person) {
        this.person = person;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setClassYear(int classYear) {
        this.classYear = classYear;
    }

   public person_WorkID getId() {
         return id;
    }

    public person getPerson() {
        return person;
    }

    public Company getCompany() {
        return company;
    }

    public int getClassYear() {
        return classYear;
    }
    
    
   @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        workAT that = (workAT) o;
        return Objects.equals(person, that.person) &&
               Objects.equals(company, that.company);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(person, company);
    }    
     
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}












