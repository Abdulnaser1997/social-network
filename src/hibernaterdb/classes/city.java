/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernaterdb.classes;

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
@Table(name = "city")
public class city extends place {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ispartof")
    private country ispartof;

    @OneToMany(mappedBy = "city")
    private List<person> inhabitants;

    @OneToMany(mappedBy = "islocatedin")
    private List<University> universities;

    public city() {
    }

    public city(int id, String name) {
        super(id, name);
    }

    public void setispartof(country ispartof) {

        this.ispartof = ispartof;
    }

    public void setInhabitants(List<person> inhabitants) {
        this.inhabitants = inhabitants;
    }

    public void setUniversities(List<University> universities) {
        this.universities = universities;
    }

    public country getispartof() {
        return ispartof;
    }

    public List<person> getInhabitants() {
        return inhabitants;
    }

    public List<University> getUniversities() {
        return universities;
    }

}
