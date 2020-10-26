/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernaterdb.classes;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Abdulnaser
 */
@Entity
@Table(name = "post")
public class Post implements Serializable {

    @Id
    @Column(name = "postid")
    private long id;

    @Column(name = "post_creationdate")
    private Timestamp creationDate;

    @Column(name = "post_locationip")
    private String locationIp;

    @Column(name = "browserused")
    private String browserUsed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator")
    private person creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forumid")
    private Forum forumid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "islocatedin")
    private country location;

    @ManyToMany
    @JoinTable(name = "person_likes_post",
            joinColumns = @JoinColumn(name = "postid"),
            inverseJoinColumns = @JoinColumn(name = "personid")
    )
    private List<person> persons = new ArrayList<person>();

    @ManyToMany
    @JoinTable(name = "post_hastag_tag",
            joinColumns = {
                @JoinColumn(name = "postid")},
            inverseJoinColumns = {
                @JoinColumn(name = "tagid")})
    private List<Tag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "replyOfPost")
    private List<Comment> comments;

    public void setId(long id) {
        this.id = id;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public void setLocationIp(String locationIp) {
        this.locationIp = locationIp;
    }

    public void setBrowserUsed(String browserUsed) {
        this.browserUsed = browserUsed;
    }

    public void setCreator(person creator) {
        this.creator = creator;

    }

    public void setForumid(Forum forumid) {
        this.forumid = forumid;
    }

    public void setLocation(country location) {
        this.location = location;
    }

    public void setPersons(List<person> persons) {
        this.persons = persons;
    }

    public void setPosts(List<Tag> tags) {
        this.tags = tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public long getId() {
        return id;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public String getLocationIp() {
        return locationIp;
    }

    public String getBrowserUsed() {
        return browserUsed;
    }

    public person getCreator() {
        return creator;
    }

    public Forum getForumid() {
        return forumid;
    }

    public country getLocation() {
        return location;
    }

    public List<Tag> getPosts() {
        return tags;
    }

    public List<person> getPersons() {
        return persons;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public List<Comment> getComments() {
        return comments;
    }

}
