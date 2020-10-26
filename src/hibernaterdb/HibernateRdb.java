/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernaterdb;



import HelpClasses.InputHelper;
import java.net.UnknownHostException;
import Imp.StatisticImpl;

/**
 *
 * @author Abdulnaser Sabra
 */
public class HibernateRdb {

    /*
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException {
    //Call the functions 
    InputHelper iHelper = new InputHelper();
    iHelper.getInputForPersonProfile();
    iHelper.getInputForCommonInterests();
    iHelper.getInputForCommonFriends();
    iHelper.getInputForPersonsWithMostCommonInterests();
    iHelper.getInputForJobRecommendation();
    iHelper.getInputShortestPath();
      
    StatisticImpl test = new StatisticImpl();
    
    test.getTagClassHierarchy();
    iHelper.getInputForPopularComments();
    test.getMostPostingCountry();
       

      
       
       
       
     
     
     

    }


}