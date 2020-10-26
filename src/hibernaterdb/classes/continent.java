/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernaterdb.classes;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Abdulnaser Sabra
 */
@Entity
@Table(name = "continent")
public class continent extends place {

    @OneToMany(mappedBy = "ispartof")
    private List<country> countries;

    public continent() {
    }

    public continent(int id, String name) {
        super(id, name);
    }

    public void setCountries(List<country> countries) {
        this.countries = countries;
    }

    public List<country> getCountries() {
        return countries;
    }

}
