/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imp;

import HelpClasses.Outputformatter;
import APIs.PersonRelatedAPI;
import hibernaterdb.HibernateUtil;
import hibernaterdb.classes.Comment;
import hibernaterdb.classes.Company;
import hibernaterdb.classes.Organisation;
import hibernaterdb.classes.StudyAt;
import hibernaterdb.classes.Tag;
import hibernaterdb.classes.University;
import hibernaterdb.classes.person;
import hibernaterdb.classes.workAT;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Abdulnaser Sabra
 */
public class PersonRelatedImpl implements PersonRelatedAPI {

    //helps in formatting the result to the user.
    private Outputformatter outputformatter = new Outputformatter();

    /**
     * gets the list of a given user's friends.
     *
     * @param personid the peresonid
     * @return a list of recommended organisations.
     */
    private List<person> getFriends(long id) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Session session = sessionFactory.openSession();

        session.beginTransaction();
        List<person> allFriends = new ArrayList<>();

        String hql = "SELECT p.friends FROM Person p WHERE p.personid = :id";
        String hql2 = "SELECT p.friendsOf FROM Person p WHERE p.personid = :id";
        Query query = session.createQuery(hql);
        Query query2 = session.createQuery(hql2);
        List<person> friends = query.setParameter("id", id).list();
        List<person> friendsOf = query2.setParameter("id", id).list();
        allFriends.addAll(friends);
        allFriends.addAll(friendsOf);
        return allFriends;

    }

    @Override
    public void getProfile(long personid) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            Query getPerson = session.createQuery("FROM Person WHERE personid = :personid");
            getPerson.setParameter("personid", personid);

            person person = (person) getPerson.uniqueResult();
            outputformatter.printPersonProfile(person);
            tx.commit();
        } catch (HibernateException hibEx) {
            tx.rollback();
        } finally {
            session.close();
        }
    }

    /**
     * prints the intersected set of common interests of the given user and his
     * friends.
     *
     * @param personid the peresonid
     * @return a list of recommended organisations.
     */
    @Override
    public void getCommonInterestsOfMyFriends(long personid) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        //stors the tags, in  which the given peson is interessted.
        List<Tag> interesstedTagsbyGivenPerson = new ArrayList<>();

        //stors the tags, in  which the given peson is interessted.
        List<Tag> interesstedTagsbyFriends = new ArrayList<>();

        //stors the shared tags.
        List<Tag> commonTags = new ArrayList<>();

        //get the List of all friends of the given person. 
        List<person> friends = getFriends(personid);

        try {
            //retrievs the tags, in  which the user is interessted.
            Query getTags = session.createQuery("SELECT t \n"
                    + "FROM Person p JOIN p.tags t \n"
                    + "WHERE p.personid= :person_ID \n");

            interesstedTagsbyGivenPerson = getTags.setParameter("person_ID", personid).list();

            for (person friend : friends) {

                interesstedTagsbyFriends.addAll(getTags.setParameter("person_ID", friend.getPersonid()).list());
            }

            Set<Integer> ids = interesstedTagsbyFriends.stream().map(obj -> obj.getId()).collect(Collectors.toSet());

            commonTags = interesstedTagsbyGivenPerson.stream()
                    .filter(obj -> ids.contains(obj.getId()))
                    .collect(Collectors.toList());

            outputformatter.printCommonTags(commonTags);
            tx.commit();
        } catch (HibernateException hibEx) {
            tx.rollback();
            hibEx.printStackTrace();
        } finally {
            session.close();
        }

    }

    /**
     * prints the intersected set of common interests of the given user and his
     * friends using the pure hibernate query language.
     *
     * @param personid the peresonid
     * @return a list of recommended organisations.
     */
    public void getCommonInterestsOfMyFriendsHQL(long personid) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        try {
            //from Portailuser u join u.portailroles r where r.name=:roleName
            Query query = session.createQuery("SELECT DISTINCT t \n"
                    + "FROM Tag t join t.persons p \n"
                    + "WHERE ( p.personid IN (\n"
                    + "SELECT f.personid \n"
                    + "FROM Person p join p.friends f \n"
                    + "WHERE p.personid = :person_ID  \n"
                    + " ) \n"
                    + "OR p.personid IN (\n"
                    + "SELECT f.personid \n"
                    + "FROM Person p join p.friendsOf f \n"
                    + "WHERE p.personid = :person_ID \n"
                    + " ) ) \n"
                    + "AND t.id IN (\n"
                    + "SELECT t.id \n"
                    + "FROM Person p join p.tags t \n"
                    + "WHERE p.personid = :person_ID \n" + " ) ");

            query.setParameter("person_ID", personid);
            List<Tag> commonTags = query.list();

            outputformatter.printCommonTags(commonTags);
        } catch (HibernateException hibEx) {
            tx.rollback();
            hibEx.printStackTrace();
        } finally {
            session.close();
        }

    }

    /**
     * prints the common friends of the given 2 users
     *
     * @param personid1 the first person
     * @param personid2 the second person
     */
    @Override
    public void getCommonFriends(long personid1, long personid2) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        //stores the friends of person1
        List<person> friendsOfPerson1 = new ArrayList<>();

        //stors the friends of person2
        List<person> friendsOfPerson2 = new ArrayList<>();

        //stors the commonFriends of both person1 and person2
        List<person> commonFriends = new ArrayList<>();

        //retrievs the friends of person 1
        friendsOfPerson1 = getFriends(personid1);

        //retrieves the friends of person 2
        friendsOfPerson2 = getFriends(personid2);

        //pulls out the ids of the friends of person2
        Set<Long> ids = friendsOfPerson2.stream().map(obj -> obj.getPersonid()).collect(Collectors.toSet());

        //retrieves the intersection of both lists 
        commonFriends = friendsOfPerson1.stream()
                .filter(obj -> ids.contains(obj.getPersonid()))
                .collect(Collectors.toList());

        outputformatter.printCommonFriends(commonFriends);
        tx.commit();
        session.close();
    }

    /**
     * prints the user-name of the user , who has the most common interests with
     * the given user.
     *
     * @param personid the peresonid
     */
    @Override
    public void getPersonsWithMostCommonInterests(long personid) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        //List of tags of the given User 
        List<Tag> givenUserInterests = new ArrayList<>();

        //List of tags of the maxmatchfriend 
        List<Tag> maxMatchTags = new ArrayList<>();

        //A temprary map used for calculation purposes.
        Map<person, Integer> personWithTagsCount = new HashMap<person, Integer>();

        //stors all persons 
        List<person> allPersons = new ArrayList<>();

        try {

            Query getpersons = session.createQuery("FROM Person \n"
                    + "WHERE personid != :Person_ID");

            allPersons = getpersons.setParameter("Person_ID", personid).list();

            //retrievs the tags, in  which the user is interessted.
            Query getTags = session.createQuery("SELECT t \n"
                    + "FROM Person p JOIN p.tags t \n"
                    + "WHERE p.personid= :person_ID \n");

            givenUserInterests = getTags.setParameter("person_ID", personid).list();

            for (person friend : allPersons) {

                maxMatchTags = getTags.setParameter("person_ID", friend.getPersonid())
                        .list();

                //gets the intersection.
                Set<Integer> ids = maxMatchTags.stream().map(obj -> obj.getId()).collect(Collectors.toSet());

                List<Tag> intersectedList = givenUserInterests.stream()
                        .filter(obj -> ids.contains(obj.getId()))
                        .collect(Collectors.toList());

                //Adds into the Map
                personWithTagsCount.put(friend, intersectedList.size());

            }

            List<person> friendsWithMaxInterests = personWithTagsCount.entrySet()
                    .stream()
                    .filter(entry -> entry.getValue() == Collections.max(personWithTagsCount.values()))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            int maxCountMatch = Collections.max(personWithTagsCount.values());

            outputformatter.printPersonsWithMostCommonInterests(friendsWithMaxInterests, maxCountMatch);
        } catch (HibernateException hibEx) {
            tx.rollback();
            hibEx.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * prints and returns a list of recommended companies and
     * universities,whereas the recommended companies are in the same country as
     * the country of the company the person is currently working and the
     * recomended universities are in the same city as the city of the
     * university the person is currently studying in.
     *
     * @param personid the peresonid
     * @return a list of recommended organisations.
     */
    @Override
    public void getJobRecommendation(long personid) {

        //Set up the connection with postgresql 
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        //stors the organisations of the given User
        List<Organisation> userOrganisations = new ArrayList<>();

        //List of  the friends of the given User
        List<person> friends = new ArrayList<>();

        //stors the recommended jobs 
        Set<Organisation> recommendedJobs = new HashSet<>();

        try {

            //gets the companies, in which the given User is working 
            Query companies = session.createQuery("SELECT w.company \n"
                    + "FROM  Person p JOIN p.person_work w \n"
                    + "WHERE p.personid= :Person_ID \n"
            );

            //adds all the companies of the given User
            userOrganisations.addAll(companies.setParameter("Person_ID", personid).list());

            //gets the universities in which the user is studying  
            Query universities = session.createQuery("SELECT u.university \n"
                    + "FROM  Person p JOIN p.peron_uni u \n"
                    + "WHERE p.personid= :Person_ID \n");

            //adds all the Universities of the given User
            userOrganisations.addAll(universities.setParameter("Person_ID", personid).list());

            //gets the friends of the given User                                                     
            friends = getFriends(personid);

            //Iterate over the list of the friends 
            for (person friend : friends) {
                //stors the companies of a user's friend.
                List<Company> tempCompany = new ArrayList<>();
                //stors the Universities of a user's friend.
                List<University> tempUni = new ArrayList<>();

                tempCompany = companies.setParameter("Person_ID", friend.getPersonid()).list();
                tempUni = universities.setParameter("Person_ID", friend.getPersonid()).list();

                //Iterate over the list of a user's friend companies.
                for (Company company : tempCompany) {
                    //Iterate over the list of the given user's organisations.
                    for (Organisation o : userOrganisations) {
                        if (o instanceof Company) {
                            Company t = (Company) o;
                            if (((Company) o).getLocation().getID() == company.getLocation().getID()) {
                                //The recommended job should not be already included in the given user's organisations list. 
                                if (!userOrganisations.contains(company)) {
                                    recommendedJobs.add(company);
                                    break;
                                }
                            }
                        }
                    }
                }

                //Iterate over the list of a user's friend universities.
                for (University uni : tempUni) {
                    //Iterate over the list of the given user's organisations.
                    for (Organisation o : userOrganisations) {
                        if (o instanceof University) {
                            University t = (University) o;
                            if (t.getLocation().getID() == uni.getLocation().getID()) {
                                //The recommended job should not be already included in the given user's organisations list.  
                                if (!userOrganisations.contains(uni)) {
                                    recommendedJobs.add(uni);
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            outputformatter.printJobRecommendations(recommendedJobs);
        } catch (HibernateException hibEx) {
            tx.rollback();
            hibEx.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * prints the samllest distance and the Shortestpath between the two given
     * users.
     *
     * @param personid1 the first person
     * @param personid2 the second person.
     */
    @Override
    public void getShorthestFriendshipPath(long personid1, long personid2) {

        //Set up the connection with postgresql 
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Object[]> result = new ArrayList<>();
        try {
            Query query = session.createSQLQuery("SELECT * FROM shortestPath(:from,:to)");
            query.setParameter("from", personid1);
            query.setParameter("to", personid2);
            result = query.list();
            if (personid1 == personid2) {
                System.out.println("Shortest friendship path to " + personid1 + " \ndistance: 0 \npaths: {{}}\n");
            } else if (result.get(0)[0] != null) {
                int distance = (int) result.get(0)[0];
                System.out.println("Shortest friendship path to " + personid2 + "\ndistance: " + distance + "\npaths: " + result.get(0)[1]);
            } else {
                System.out.println("No path between " + personid1 + " and " + personid1 + " found.");
            }
            tx.commit();
        } catch (Exception hibEx) {
            tx.rollback();
            System.out.println("No path between " + personid1 + " and " + personid2 + " found.");
        } finally {
            session.close();
        }

    }

}
