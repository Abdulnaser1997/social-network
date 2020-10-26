/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelpClasses;

import hibernaterdb.classes.Comment;
import hibernaterdb.classes.Organisation;
import hibernaterdb.classes.Tag;
import hibernaterdb.classes.person;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Abdulnaser Sabra.
 */
public class Outputformatter {

    /**
     * Prints all the relevant infos of the given person.
     *
     * @param person
     */
    public void printPersonProfile(person person) {

        System.out.println("profile data: \n"
                + "name: " + person.getFirstName() + " " + person.getLastName() + "\n"
                + "ID: " + person.getPersonid() + "\n"
                + "gender: " + person.getGender() + "\n"
                + "birthday: " + person.getBirthday() + "\n"
                + "creationDate: " + person.getCreationdate() + "\n"
                + "locationIP: " + person.getLocationip() + "\n"
                + "browserUsed: " + person.getBrowserused() + "\n"
                + "city: " + person.getCity().getName() + "\n"
        );

        //prints the langauges  
        if (!person.getSpeakedLangauges().isEmpty()) {
            System.out.println("the langauges are  : ");

            person.getSpeakedLangauges().stream()
                    .forEach(langauge -> System.out.println(langauge.getLangaugename()));
        } else {
            System.out.println("There is no common friends");
        }

        //prints the Emails  
        if (!person.getEmails().isEmpty()) {
            System.out.println("the emails are  : ");

            person.getEmails().stream()
                    .forEach(email -> System.out.println(email.getEmailAddress()));
        } else {
            System.out.println("There is no Emails");
        }

    }

    /**
     * Prints the list of common tags between the given user and his friends
     *
     * @param commonTags list of commonTags.
     */
    public void printCommonTags(List<Tag> commonTags) {

        System.out.println("Number of common tags is : " + commonTags.size());

        if (!commonTags.isEmpty()) {

            commonTags.stream()
                    .forEach(commonTag -> System.out.println(
                    "TagID : " + commonTag.getId() + " " + "TagName : " + commonTag.getName()
            ));
        } else {
            System.out.println("No shared tags !");
        }

    }

    /**
     * Prints the list of common friends between the given users.
     *
     * @param commonFriends list of commonFriends.
     */
    public void printCommonFriends(List<person> commonFriends) {

        //prints the commonFriends  
        if (!commonFriends.isEmpty()) {
            System.out.println("the commonFriends are  : ");

            commonFriends.stream()
                    .forEach(commonFriend -> System.out.println(
                    commonFriend.getPersonid() + " "
                    + commonFriend.getFirstName() + " "
                    + commonFriend.getLastName()));

        } else {
            System.out.println("There is no common friends");
        }

    }

    /**
     * Prints a list of persons , who have the maximum number of matched tags
     * with the given person.
     *
     * @param friendsWithMaxInterests list of persons.
     * @param maxCountMatch max match number
     */
    public void printPersonsWithMostCommonInterests(List<person> friendsWithMaxInterests, int maxCountMatch) {
        if (!friendsWithMaxInterests.isEmpty()) {
            System.out.println("Person with the most matched tags is : ");

            friendsWithMaxInterests.stream()
                    .forEach(friend -> System.out.println(
                    friend.getPersonid() + " "
                    + friend.getFirstName() + " "
                    + friend.getLastName() + " "
                    + maxCountMatch));
        } else {
            System.out.println("There is no common tags between the person and his friends! ");
        }

    }

    /**
     * Prints a list of recommended jobs for the given user.
     *
     * @param recommendedJobs list of recommended jobs.
     */
    public void printJobRecommendations(Set<Organisation> recommendedJobs) {

        if (!recommendedJobs.isEmpty()) {
            System.out.println("Recommended Jobs are : ");

            recommendedJobs.stream()
                    .forEach(recommendedJob -> System.out.println(
                    "OrganisationTyp  : " + recommendedJob.getClass().getSimpleName() + " "
                    + "organisation name : " + recommendedJob.getName()
            ));
        } else {
            System.out.println("There is no recomended organisations");
        }
    }

    /**
     * Prints the comments ,whereas the number of likes is at least equal to the
     * given number.
     *
     * @param comments
     */
    public void printPopularComments(List<Comment> comments) {

        if (!comments.isEmpty()) {
            System.out.println("popular comments are : ");

            comments.stream()
                    .forEach(popularComment -> System.out.println(
                    "CommentID is : " + popularComment.getId() + " " + "firstName : " + popularComment.getCreator().getFirstName() + " "
                    + "lastName : " + popularComment.getCreator().getLastName() + " "
                    + "Number Of Likes is : " + popularComment.getPersons().size()
            ));
        } else {
            System.out.println("There is no popular comments!");
        }
        System.out.println("=======================================");
    }

    /**
     * Prints the countries from which the greatest number of posts and comments
     * originate.
     *
     * @param countries
     * @param maxSum
     */
    public void printMostPostingCountry(List<String> countries, long maxSum) {
        System.out.println("Calling the function  " + "mostPostingCountry ");
        if (!countries.isEmpty()) {
            System.out.println("Countries with max sum are : ");

            countries.stream()
                    .forEach(country -> System.out.println(
                    "Country name : " + country + " "
                    + "Max sum : " + maxSum
            ));
        } else {
            System.out.println("There is no countries with max sum!");
        }
        System.out.println("==================================");

    }

}
