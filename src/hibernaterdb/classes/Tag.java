/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernaterdb.classes;

import java.util.ArrayList;
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
 * @author Abdulnaser Sabra
 */


@Entity
@Table(name="tag")
public class Tag {
   
   @Id
   @Column(name= "tagid")
   private int id; 
   
   @Column(name = "tag_name")
   private String name; 
    
   @Column(name= "url")
   private String url; 
    
   @ManyToMany
   @JoinTable(name = "person_hasinterest_tag",
              joinColumns = @JoinColumn(name = "tagid"),
              inverseJoinColumns = @JoinColumn(name = "personid")
    ) 
    private List<person> persons = new ArrayList<person>();
   
   
   
    @ManyToMany
    @JoinTable(name = "comment_hastag_tag",
            joinColumns = {@JoinColumn(name = "tagid")},
            inverseJoinColumns = {@JoinColumn(name = "commentid")})
    private List<Comment> comments = new ArrayList<>();
    
    
    @ManyToMany
    @JoinTable(name = "post_hastag_tag",
            joinColumns = {@JoinColumn(name = "tagid")},
            inverseJoinColumns = {@JoinColumn(name = "postid")})
    private List<Post> posts = new ArrayList<>();

    
    @ManyToMany
    @JoinTable(name = "tag_hastype_tagclass",
            joinColumns = @JoinColumn(name = "tagid"),
            inverseJoinColumns = @JoinColumn(name = "tagclassid")
    )
    private List<Tagclass> tagclasses;
    
    
    @ManyToMany
    @JoinTable(name = "forum_hastag_tag",
            joinColumns = @JoinColumn(name = "tagid"),
            inverseJoinColumns = @JoinColumn(name = "forumid")
    )
    private List<Forum> forums;
    
    
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPersons(List<person> persons) {
        this.persons = persons;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setTagclasses(List<Tagclass> tagclasses) {
        this.tagclasses = tagclasses;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void setForums(List<Forum> forums) {
        this.forums = forums;
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

    public List<person> getPersons() {
        return persons;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<Tagclass> getTagclasses() {
        return tagclasses;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<Forum> getForums() {
        return forums;
    }
    
    
    
    
    
    
}
