/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelpClasses;

import Imp.PersonRelatedImpl;
import Imp.StatisticImpl;
import hibernaterdb.HibernateUtil;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Abdulnaser
 */
public class InputHelper {

    // private Scanner in = new Scanner(System.in);
    PersonRelatedImpl pImp = new PersonRelatedImpl();

    private final ArrayList<Long> ids;

    public InputHelper() {
        ids = getPersonIDs();
    }

    /**
     * retrieves a personId from the keyboard for the function personProfile.
     */
    public void getInputForPersonProfile() {
        getOneInput("getProfile");
    }

    /**
     * retrieves a personId from the keyboard for the function CommonInterests.
     */
    public void getInputForCommonInterests() {
        getOneInput("getCommonInterests");
    }

    /**
     * retrieves a personId from the keyboard for the function commonFriends.
     */
    public void getInputForCommonFriends() {
        getTwoInputs("getCommonFriends");
    }

    /**
     * retrieves a personId from the keyboard for the function
     * PersonsWithMostCommonInterests..
     */
    public void getInputForPersonsWithMostCommonInterests() {
        getOneInput("getPersonsWithMostCommonInterests");
    }

    /**
     * retrieves a personId from the keyboard for the function
     * JobRecommendation.
     */
    public void getInputForJobRecommendation() {
        getOneInput("getJobRecommendations");
    }

    /**
     * retrieves a personId from the keyboard for the function shortestPath.
     */
    public void getInputShortestPath() {
        getTwoInputs("getShortestPath");
    }

    /**
     * retrieves a personId from the keyboard for the function PopularComments.
     */
    public void getInputForPopularComments() {
        System.out.println("Calling the function  " + "getForPopularComments");
        System.out.println("Please enter a number : ");
        long minimalNumber = 0;

        while (true) {
            try {
                Scanner in = new Scanner(System.in);
                minimalNumber = in.nextLong();
            } catch (InputMismatchException e) {
                System.out.println("you entered an invalid number : ");
                System.out.println("Please enter aother one : ");
                continue;
            }
            break;
        }

        StatisticImpl sImp = new StatisticImpl();
        sImp.getPopularComments(minimalNumber);

    }

    /**
     * help function for all functions with one input it retrieves an input from
     * the keyboard and call the coressponded function based on the given
     * functionName
     *
     * @param functionName the name of the function , for which the input should
     * be retrieved.
     */
    private void getOneInput(String functionName) {

        System.out.println("Calling the function  " + functionName);
        System.out.println("Please enter a personId : ");
        long personId = getInput();

        if (functionName.equals("getCommonInterests")) {
            pImp.getCommonInterestsOfMyFriends(personId);
        } else if (functionName.equals("getPersonsWithMostCommonInterests")) {
            pImp.getPersonsWithMostCommonInterests(personId);
        } else if (functionName.equals("getJobRecommendations")) {
            pImp.getJobRecommendation(personId);
        } else if (functionName.endsWith("getProfile")) {
            pImp.getProfile(personId);
        }
        System.out.println("================================================");
    }

    /**
     * help function for all functions with two input it retrieves the inputs
     * from the keyboard and call the coressponded function based on the given
     * functionName
     *
     * @param functionName the name of the function , for which the input should
     * be retrieved.
     */
    private void getTwoInputs(String functionName) {

        System.out.println("Calling the function  " + functionName);
        System.out.println("Please enter the first personId : ");
        long personid1 = getInput();

        System.out.println("Please enter the second personId : ");
        long personid2 = getInput();

        if (personid1 == personid2) {
            System.out.println("The two given ids are identical!");
        } else if (functionName.equals("getCommonFriends")) {
            pImp.getCommonFriends(personid1, personid2);
        } else if (functionName.equals("getShortestPath")) {
            pImp.getShorthestFriendshipPath(personid1, personid2);
        }
        System.out.println("================================================");

    }

    /**
     * basic function for getting input from the keyboard it checks additonally
     * ,whether the inputed personid is a long number or not and if it is a long
     * number , then checks out if it is stored in the database or not.
     */
    private Long getInput() {

        long personid = 0;

        while (true) {
            try {
                Scanner in = new Scanner(System.in);
                personid = in.nextLong();
                if (!existis(personid)) {
                    System.out.println("The personid does not exist in the databasse : ");
                    System.out.println("Please chose another one : ");
                    continue;
                }

            } catch (InputMismatchException e) {
                System.out.println("you entered an invalid personid : ");
                System.out.println("Please enter aother one : ");
                continue;
            }

            break;
        }
        return personid;
    }

    /**
     * checks out ,wether the given personid is contained in the database or
     * not.
     */
    private boolean existis(long personid) {
        return ids.contains(personid);
    }

    /**
     * retrieves a lit of the personids stored in the database.
     */
    private ArrayList<Long> getPersonIDs() {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        List<Long> ids = new ArrayList<>();

        Query hql = session.createQuery("SELECT personid FROM  Person");
        ids = hql.list();
        return (ArrayList<Long>) ids;
    }

}
