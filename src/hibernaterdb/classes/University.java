/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernaterdb.classes;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Abdulnaser Sabra
 */
@Entity
@Table(name = "university")
public class University extends Organisation {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location")
    private city islocatedin;

    @OneToMany(
            mappedBy = "university"
    )
    private List<StudyAt> students = new ArrayList<StudyAt>();

    public University() {
    }

    public University(int id, String name, city city, List<StudyAt> students) {
        super(id, name);
        this.islocatedin = city;
        this.students = students;
    }

    public void setStudents(List<StudyAt> students) {
        this.students = students;
    }

    public void setLocation(city islocatedin) {

        this.islocatedin = islocatedin;
    }

    public List<StudyAt> getStudents() {
        return students;
    }

    public city getLocation() {

        return islocatedin;
    }

}
