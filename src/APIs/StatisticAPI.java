/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APIs;

import java.util.List;

/**
 *
 * @author Abdulnaser
 */
public interface StatisticAPI {
    
    void getTagClassHierarchy();
   
    void getPopularComments(long k);
    
    void getMostPostingCountry();
    
    
}
