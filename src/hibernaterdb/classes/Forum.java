/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernaterdb.classes;

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
import javax.persistence.Table;

/**
 *
 * @author Abdulnaser
 */
@Entity
@Table(name = "forum")
public class Forum {

    @Id
    @Column(name = "forumid", nullable = false)
    private long id;

    @Column(name = "forumtitle")
    private String title;

    @Column(name = "forum_creationdate")
    private Timestamp creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moderator")
    private person moderator;

    @ManyToMany
    @JoinTable(name = "forum_hasmember_peron",
            joinColumns = @JoinColumn(name = "forumid"),
            inverseJoinColumns = @JoinColumn(name = "personid")
    )
    private List<person> persons = new ArrayList<person>();

    @OneToMany(mappedBy = "forumid")
    private List<Post> posts = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "forum_hastag_tag",
            joinColumns = @JoinColumn(name = "forumid"),
            inverseJoinColumns = @JoinColumn(name = "tagid")
    )
    private List<Tag> tags = new ArrayList<>();

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public void setModerator(person moderator) {
        this.moderator = moderator;
    }

    public void setPersons(List<person> persons) {
        this.persons = persons;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public person getModerator() {
        return moderator;
    }

    public List<person> getPersons() {
        return persons;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<Tag> getTags() {
        return tags;
    }

}
