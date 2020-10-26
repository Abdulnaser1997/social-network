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
@Table(name = "company")
public class Company extends Organisation {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "islocatedin")
    private country islocatedin;

    @OneToMany(
            mappedBy = "company"
    )
    private List<workAT> company_person = new ArrayList<workAT>();

    public Company() {
    }

    public Company(int id, String name, List<workAT> company_person, country islocatedin) {
        super(id, name);
        this.company_person = company_person;
        this.islocatedin = islocatedin;
    }

    public void setLocation(country islocatedin) {

        this.islocatedin = islocatedin;
    }

    public void setCompany_person(List<workAT> company_person) {
        this.company_person = company_person;
    }

    public country getLocation() {

        return islocatedin;
    }

    public List<workAT> getCompany_person() {
        return company_person;
    }

}
