/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imp;

import APIs.StatisticAPI;
import HelpClasses.Outputformatter;
import hibernaterdb.HibernateUtil;
import hibernaterdb.classes.Comment;
import hibernaterdb.classes.Tagclass;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
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
public class StatisticImpl implements StatisticAPI {

    Outputformatter outputformatter = new Outputformatter();

    /**
     * Prints the TagClass hierarchy in form of a taxonomy, in which a TagClass
     * is represented by the name and the level number.
     */
    @Override
    public void getTagClassHierarchy() {
        System.out.println("Calling the function  " + "TagClassHierarchy ");
        
        Tagclass thing = new Tagclass();

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        try {
            //retrieves the root tagClass.
            Query getThingTagClass = session.createQuery("FROM Tagclass \n"
                    + "WHERE name='Thing' \n"
            );

            //pre-order traversal algorithm is being used here.
            Stack<Tagclass> tagclasses = new Stack<>();

            //stors the level number for each tagclass stored in the tagclassess stack
            //with each extraction of a tagclass from the tagclassess stack a coressponded 
            //level number will be poped out.
            Stack<String> levelNumbers = new Stack<>();

            thing = (Tagclass) getThingTagClass.uniqueResult();

            levelNumbers.push("0");

            tagclasses.push(thing);

            while (!tagclasses.isEmpty()) {
                Tagclass current = tagclasses.pop();
                String currentNumber = levelNumbers.pop();
                System.out.println(currentNumber + " " + current.getName());

                if (current.getSubtagclasses() != null) {
                    //pushs all the tagclass in the stack , which are subclass  
                    //of the current tag class 
                    for (int index = current.getSubtagclasses().size() - 1; index >= 0; index--) {
                        tagclasses.push(current.getSubtagclasses().get(index));
                    }
                    //pushs all the level numbers  coressponded to the 
                    //subtagclassess of the current tagclass in the number stack. 
                    for (int i = current.getSubtagclasses().size(); i > 0; i--) {
                        levelNumbers.push(currentNumber + "." + i);
                    }
                }

            }
        } catch (HibernateException hibEx) {
            tx.rollback();
            hibEx.printStackTrace();
        } finally {
            session.close();
        }
        
        System.out.println("=======================================");

    }

    /**
     * Prints the comments , with a number of 'likes' greater than the given
     * number 'k'.
     */
    @Override
    public void getPopularComments(long k) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        try {

            Query query = session.createQuery("SELECT c "
                    + "FROM Comment c JOIN c.persons p \n"
                    + "Group by c.id \n"
                    + "HAVING COUNT(p.personid)>= :Number_of_likes\n"
                    + "ORDER BY COUNT(p.personid) DESC ) \n");

            query.setParameter("Number_of_likes", k);
            List<Comment> pupolarComments = query.list();
            outputformatter.printPopularComments(pupolarComments);

        } catch (HibernateException hibEx) {
            tx.rollback();
            hibEx.printStackTrace();
        } finally {
            session.close();
        }

    }

    /**
     * prints the countries with the max sum of comments and posts.
     */
    @Override
    public void getMostPostingCountry() {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();

        HashMap<String, Long> countryWithSum = new HashMap<>();

        List<Object[]> infos = new ArrayList<>();

        try {

            Query getMostCountryPerCommernt = session.createQuery("SELECT co.name,COUNT(c.id) AS NumberOfComments\n"
                    + "FROM Comment c RIGHT JOIN c.location co \n"
                    + "GROUP BY co.name \n"
                    + "ORDER BY 2 DESC \n"
            );
            infos = getMostCountryPerCommernt.list();
            for (Object[] obj : infos) {
                countryWithSum.put((String) obj[0], (long) obj[1]);
            }

            Query getMostCountryPerPost = session.createQuery("SELECT co.name,COUNT(c.id) AS NumberOfPosts\n"
                    + "FROM Post c RIGHT JOIN c.location co \n"
                    + "GROUP BY co.name \n"
                    + "ORDER BY 2 DESC \n"
            );

            infos.clear();
            infos = getMostCountryPerPost.list();
            for (Object[] obj : infos) {
                for (Map.Entry<String, Long> entry : countryWithSum.entrySet()) {
                    if (entry.getKey().equals((String) obj[0])) {
                        countryWithSum.put(entry.getKey(), (entry.getValue() + (long) obj[1]));
                    }
                }
            }

            long max = countryWithSum.entrySet()
                    .stream()
                    .max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1)
                    .get()
                    .getValue();

            List listOfMax = countryWithSum.entrySet()
                    .stream()
                    .filter(entry -> entry.getValue() == max)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            outputformatter.printMostPostingCountry(listOfMax, max);

        } catch (HibernateException hibEx) {
            tx.rollback();
            hibEx.printStackTrace();
        } finally {
            session.close();
        }

    }

}
