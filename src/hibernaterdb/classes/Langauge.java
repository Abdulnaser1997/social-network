/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernaterdb.classes;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author User
 */
@Entity
@Table(name="langauge")
public class Langauge implements Serializable {
    
    @Id
    @Column(name="langaugename")
    private String langaugename; 
    
    
    @ManyToMany
    @JoinTable(name = "person_speaks_language",
            joinColumns = @JoinColumn(name = "langauge"),
            inverseJoinColumns = @JoinColumn(name = "personid")
    )
    private List<person> langaugeSpeakers;

    public void setLangaugename(String langaugename) {
        this.langaugename = langaugename;
    }

    public void setLangaugeSpeakers(List<person> langaugeSpeakers) {
        this.langaugeSpeakers = langaugeSpeakers;
    }

    public String getLangaugename() {
        return langaugename;
    }

    public List<person> getLangaugeSpeakers() {
        return langaugeSpeakers;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
