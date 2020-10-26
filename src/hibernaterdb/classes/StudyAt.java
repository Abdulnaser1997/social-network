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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 *
 * @author Abdulnaser Sabra
 */

@Entity(name ="StudyAt")
@Table(name="studyat")
public class StudyAt {
    
    @EmbeddedId
    private Person_UniversityID id = new Person_UniversityID();
    
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="personid",updatable=false,insertable=false)
    private person person;
    
    

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="uniid",updatable=false,insertable=false)
    private University university;

    @Column(name ="classyear")
    private int classYear;
  
    
    public StudyAt(){}
    
    
    public StudyAt(person p, University uni, int classYear) {
        this.person = p;
        this.university = uni;
        this.classYear = classYear;
        this.id = new Person_UniversityID(person.getPersonid(), university.getID());
    }
    
    
    
    
    
   
    public person getPerson() {
        return person;
    }

    public University getUniversity() {
        return university;
    }

    public int getClassYear() {
        return classYear;
    }


    public void setPerson(person person) {
        this.person = person;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    public void setUniversity(University university) {
        this.university = university;
    }

    public void setClassYear(int classYear) {
        this.classYear = classYear;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        StudyAt that = (StudyAt) o;
        return Objects.equals(person, that.person) &&
               Objects.equals(university, that.university);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(person, university);
    }    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
