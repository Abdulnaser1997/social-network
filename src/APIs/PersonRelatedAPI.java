/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APIs;

/**
 *
 * @author Abdulnaser Sabra 
 */
public interface PersonRelatedAPI {
    
   public void getProfile(long personid);
    
   public abstract void  getCommonInterestsOfMyFriends(long personid);
    
   public abstract void  getCommonFriends( long personid1, long personid2 );
    
   public abstract void getPersonsWithMostCommonInterests( long personid ); 
    
   public abstract void getJobRecommendation( long personid ); 
    
   public abstract void getShorthestFriendshipPath(long personid1, long personid2); 
    
    
    
}
