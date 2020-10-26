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
 * @author Abdulnaser Sabra.
 */
@Entity
@Table(name = "country")
public class country extends place {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ispartof")
    private continent ispartof;

    @OneToMany(mappedBy = "ispartof")
    private List<city> cities;

    @OneToMany(mappedBy = "location")
    private List<Comment> comments;

    @OneToMany(mappedBy = "location")
    private List<Post> posts;

    public country() {
    }

    public country(int id, String name) {
        super(id, name);
    }

    public void setIspartof(continent ispartof) {
        this.ispartof = ispartof;
    }

    public void setCities(List<city> cities) {
        this.cities = cities;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public continent getIspartof() {
        return ispartof;
    }

    public List<city> getCities() {
        return cities;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<Post> getPosts() {
        return posts;
    }

}
