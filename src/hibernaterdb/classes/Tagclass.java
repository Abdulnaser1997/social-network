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
 * @author Abdulnaser
 */
@Entity
@Table(name = "tagclass")
public class Tagclass implements Serializable {

    @Id
    @Column(name = "tagclassid", nullable = false)
    private int id;

    @Column(name = "tagclassname", nullable = false)
    private String name;

    @Column(name = "url", nullable = false)
    private String url;

    @ManyToMany
    @JoinTable(name = "issubclassof",
            joinColumns = @JoinColumn(name = "tagclassid2"),
            inverseJoinColumns = @JoinColumn(name = "tagclassid1")
    )
    private List<Tagclass> subtagclasses;

    @ManyToMany
    @JoinTable(name = "issubclassof",
            joinColumns = @JoinColumn(name = "tagclassid1"),
            inverseJoinColumns = @JoinColumn(name = "tagclassid2")
    )
    private List<Tagclass> subtagclassof;

    @ManyToMany
    @JoinTable(name = "tag_hastype_tagclass",
            joinColumns = @JoinColumn(name = "tagclassid"),
            inverseJoinColumns = @JoinColumn(name = "tagid")
    )
    private List<Tag> tags;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSubtagclassess(List<Tagclass> subtagclasses) {
        this.subtagclasses = subtagclasses;
    }

    public void setTagclassof(List<Tagclass> subtagclassof) {
        this.subtagclassof = subtagclassof;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public List<Tagclass> getSubtagclasses() {
        return subtagclasses;
    }

    public List<Tagclass> getSubTagclassof() {
        return subtagclassof;
    }

    public List<Tag> getTags() {
        return tags;
    }

}
