/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FacadeLogic;

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
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mySIP_SIP1_jar_1.0-SNAPSHOTPU");
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
      
    public String addUser(Users u) {
        initializeTransactions();
        tr.begin();
        em.persist(u);
        tr.commit();
        return u.toString();
    }
      
      
          public static void main(String[] args) {
          new FacadeLogic().base();
        
    }
      
      public void base(){
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_TestingPU");
       EntityManager em = emf.createEntityManager();
        
        Users u = new Users ("userName1","name1","email1","passW1");
  
        addUser(u);
       
         System.out.println("Adding user : "+ u);  

      }
    
}
