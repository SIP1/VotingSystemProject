/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeLogic;

import JPA.UserTypes;
import JPA.Users;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Tomascik
 */
public class FacadeLogic {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("SIP_PU");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tr;
    
    private static FacadeLogic instance = null;
    
     public static FacadeLogic getInstance() {
        if (instance == null) {
            instance = new FacadeLogic();
        }
        return instance;
    }
     
      private void initializeTransactions() {
        tr = em.getTransaction();
    }
      
    public UserTypes addUserTypes(UserTypes ut) {
        initializeTransactions();
        tr.begin();
        em.persist(ut);
        tr.commit();
        return ut;
    }
      
      
    public static void main(String[] args) {
          new FacadeLogic().base();
        
    }
      
      public void base()
      {
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("SIP_PU");
       EntityManager em = emf.createEntityManager();
        
        UserTypes ut = new UserTypes ("teacher");
        UserTypes ut1 = new UserTypes ("headOfProgram");
       // UserTypes ut2 = new UserTypes ("administrator");
        UserTypes ut3 = new UserTypes ("student");
  
        addUserTypes(ut);
        addUserTypes(ut1);
       // addUserTypes(ut2);
        addUserTypes(ut3);
       
 

      }
    
}
