/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernaterdb.classes;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Entity(name = "Person")
@Table(name = "person")
public class person {

    @Id
    @Column(name = "personid", nullable = false)
    public long personid;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "gender")
    private String gender;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "creationdate")
    private Timestamp creationdate;
    @Column(name = "locationip")
    private String locationip;
    @Column(name = "browserused") 
    private String browserused;
    
    @ManyToOne
    @JoinColumn(name = "city")
    private city city;
    
    @ManyToMany
    @JoinTable(name = "person_likes_comment",
            joinColumns = @JoinColumn(name = "personid"),
            inverseJoinColumns = @JoinColumn(name = "commentid")
    )
    private List<Comment> likedComments = new ArrayList<Comment>();
    
    
    
    @ManyToMany
    @JoinTable(name = "person_likes_post",
            joinColumns = @JoinColumn(name = "personid"),
            inverseJoinColumns = @JoinColumn(name = "postid")
    )
    private List<Post> likedPosts = new ArrayList<Post>();
   
    
    @ManyToMany
    @JoinTable(name = "knows",
            joinColumns = @JoinColumn(name = "personid1"),
            inverseJoinColumns = @JoinColumn(name = "personid2")
    )
    private List<person> friends;

    
    
    @ManyToMany
    @JoinTable(name = "knows",
            joinColumns = @JoinColumn(name = "personid2"),
            inverseJoinColumns = @JoinColumn(name = "personid1")
    )
    private List<person> friendsOf;

    
    
   @ManyToMany
   @JoinTable(name = "person_hasinterest_tag",
              joinColumns = @JoinColumn(name = "personid"),
              inverseJoinColumns = @JoinColumn(name = "tagid")
    ) 
    private List<Tag> tags = new ArrayList<Tag>();
   
   
    @OneToMany(mappedBy = "moderator")
    private List<Forum> forumsModerator;   
   
    @OneToMany(mappedBy = "person")
    private List<Email> emails;
   
    @OneToMany(mappedBy = "creator")
    private List<Comment> comments;
    
    @OneToMany(mappedBy = "creator")
    private List<Post> posts;
    
    
    
    @ManyToMany
    @JoinTable(name = "forum_hasmember_peron",
            joinColumns = @JoinColumn(name = "personid"),
            inverseJoinColumns = @JoinColumn(name = "forumid")
    )
    private List<Forum> memberOfForums;
    
    
    
    @ManyToMany
    @JoinTable(name = "person_speaks_language",
            joinColumns = @JoinColumn(name = "personid"),
            inverseJoinColumns = @JoinColumn(name = "langauge")
    )
    private List<Langauge> speakedLangauges;
   
    
    
    @OneToMany(
            mappedBy = "person"
    )
    private List<StudyAt> peron_uni = new ArrayList<StudyAt>();

    @OneToMany(
            mappedBy = "person"
    )
    private List<workAT> person_work = new ArrayList<workAT>();

     
    
    
     public void setPersonid(long personid) {
        this.personid = personid;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setCreationdate(Timestamp creationdate) {
        this.creationdate = creationdate;
    }

    public void setLocationip(String locationip) {
        this.locationip = locationip;
    }

    public void setBrowserused(String browserused) {
        this.browserused = browserused;
    }

    public void setCity(city city) {
        this.city = city;
    }
    
    
     public void setLikedComments(List<Comment> comments) {
        this.likedComments = comments;
    }

    public void setLikedPosts(List<Post> likedPosts) {
        this.likedPosts = likedPosts;
    }
    
    
    public void setFriends(List<person> friends) {
        this.friends = friends;
    }

    public void setFriendsOf(List<person> friendsOf) {
        this.friendsOf = friendsOf;
    }
    
   
    
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void setForumsModerator(List<Forum> forumsModerator) {
        this.forumsModerator = forumsModerator;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setMemberOfForums(List<Forum> memberOfForums) {
        this.memberOfForums = memberOfForums;
    }

    public void setPeron_uni(List<StudyAt> peron_uni) {
        this.peron_uni = peron_uni;
    }

    public void setPerson_work(List<workAT> person_work) {
        this.person_work = person_work;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void setSpeakedLangauges(List<Langauge> speakedLangauges) {
        this.speakedLangauges = speakedLangauges;
    }
    

    public List<Comment> getLikedComments() {
        return likedComments;
    }

    public List<Post> getLikedPosts() {
        return likedPosts;
    }

    public List<Forum> getForumsModerator() {
        return forumsModerator;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public List<Forum> getMemberOfForums() {
        return memberOfForums;
    }

   

    public city getCity() {
        return city;
    }

   
    
 
    public List<person> getFriends() {
        return friends;
    }

    public List<person> getFriendsOf() {
        return friendsOf;
    }
    
     
    public List<Comment> getComments() {
        return likedComments;
    }
    
     public List<Tag> getTags() {
        return tags;
    }
    
    
    
   public List<Post> getPosts() {
        return posts;
    }
        
    
    

    
    


    public List<workAT> getPerson_work() {
        return person_work;
    }

   
    public long getPersonid() {
        return personid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Timestamp getCreationdate() {
        return creationdate;
    }

    public String getLocationip() {
        return locationip;
    }

    public String getBrowserused() {
        return browserused;
    }

   
    public List<StudyAt> getPeron_uni() {
        return peron_uni;
    }

    public List<Langauge> getSpeakedLangauges() {
        return speakedLangauges;
    }
    
    
    
    
    
    
    
    
       @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof person)) return false;
        person person = (person) o;
        return personid == person.personid &&
                getFirstName().equals(person.getFirstName()) &&
                getLastName().equals(person.getLastName()) &&
                getGender().equals(person.getGender()) &&
                getBirthday().equals(person.getBirthday()) &&
                getCreationdate().equals(person.getCreationdate()) &&
                getLocationip().equals(person.getLocationip()) &&
                getBrowserused().equals(person.getBrowserused()) &&
                getCity().equals(person.getCity()) &&
                Objects.equals(getComments(), person.getComments()) &&
                Objects.equals(getForumsModerator(), person.getForumsModerator()) &&
                Objects.equals(getEmails(), person.getEmails()) &&
                Objects.equals(getPeron_uni(), person.getPeron_uni()) &&
                Objects.equals(getPerson_work(), person.getPerson_work()) &&
                Objects.equals(getTags(), person.getTags()) &&
                Objects.equals(getMemberOfForums(), person.getMemberOfForums()) &&
                Objects.equals(getFriends(), person.getFriends()) &&
                Objects.equals(getFriendsOf(), person.getFriendsOf()) &&
                Objects.equals(getLikedComments(), person.getLikedComments()) &&
                Objects.equals(getLikedPosts(), person.getLikedPosts()) &&
                Objects.equals(getSpeakedLangauges(), person.getSpeakedLangauges());
    }

    @Override
    public int hashCode() {
        return Objects.hash(personid, getFirstName(), getLastName(), getGender(), getBirthday(), getCreationdate(), getLocationip(), getBrowserused(), getCity(), getComments(), getForumsModerator(), getEmails(), getPeron_uni(), getPerson_work(), getTags(), getMemberOfForums(), getFriends(), getFriendsOf(), getLikedComments(), getLikedPosts(), getSpeakedLangauges());
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
