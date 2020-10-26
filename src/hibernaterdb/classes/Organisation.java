/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernaterdb.classes;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 * @author Abdulnaser Sabra
 */
@Entity
@Table(name = "organisation")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Organisation implements Serializable {

    @Id
    @Column(name = "id")
    protected int id;

    @Column(name = "name")
    protected String name;

    public Organisation() {
    }

    public Organisation(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setID(int id) {

        this.id = id;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getID() {

        return id;
    }

    public String getName() {

        return name;
    }

}
