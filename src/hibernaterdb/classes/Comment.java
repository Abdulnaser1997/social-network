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
 * @author Abdulnaser Sabra
 */
@Entity
@Table(name = "comment_")
public class Comment {

    @Id
    @Column(name = "commentid")
    private long id;

    @Column(name = "comment_creationdate")
    private Timestamp creationDate;

    @Column(name = "locationip")
    private String locationip;

    @Column(name = "browserused")
    private String browserUsed;

    @Column(name = "comcontent")
    private String content;

    @Column(name = "comlength")
    private int length;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator")
    private person creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "islocatedin")
    private country location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "replyofpost", nullable = true)
    private Post replyOfPost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "replyofcomment", nullable = true)
    private Comment replyOfComment;

    @OneToMany(mappedBy = "replyOfComment")
    private List<Comment> comments;

    @ManyToMany
    @JoinTable(name = "comment_hastag_tag",
            joinColumns = {
                @JoinColumn(name = "commentid")},
            inverseJoinColumns = {
                @JoinColumn(name = "tagid")})
    private List<Tag> tags = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "person_likes_comment",
            joinColumns = @JoinColumn(name = "commentid"),
            inverseJoinColumns = @JoinColumn(name = "personid")
    )
    List<person> persons = new ArrayList<>();

    public void setId(long id) {
        this.id = id;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public void setLocationip(String locationip) {
        this.locationip = locationip;
    }

    public void setBrowserUsed(String browserUsed) {
        this.browserUsed = browserUsed;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setCreator(person creator) {
        this.creator = creator;
    }

    public void setLocation(country location) {
        this.location = location;
    }

    public void setReplyOfPost(Post replyOfPost) {
        this.replyOfPost = replyOfPost;
    }

    public void setReplyOfComment(Comment replyOfComment) {
        this.replyOfComment = replyOfComment;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setPersons(List<person> persons) {
        this.persons = persons;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public long getId() {
        return id;
    }

    public List<person> getPersons() {
        return persons;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public String getLocationip() {
        return locationip;
    }

    public String getBrowserUsed() {
        return browserUsed;
    }

    public String getContent() {
        return content;
    }

    public int getLength() {
        return length;
    }

    public person getCreator() {
        return creator;
    }

    public country getLocation() {
        return location;
    }

    public Post getReplyOfPost() {
        return replyOfPost;
    }

    public Comment getReplyOfComment() {
        return replyOfComment;
    }

    public List<Tag> getTags() {
        return tags;
    }

}
