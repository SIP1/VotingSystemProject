///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package JPA;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToMany;
//
//
///**
// *
// * @author Tomascik
// */
//@Entity
//
//public class ProposedSubject implements Serializable {
//    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Integer id;
//    private String name;
//    private String description;
//    private Boolean isAlive;
//    private String poolOption;
//    
//
//    
//   @ManyToMany(mappedBy="proposedSubject")
//    private List<User> users = new ArrayList();
//    
//    public void addUsers(User u){
//      users.add(u);  
//    } 
//    
//   
//    // MAKES JOIN TABLES BETWEEN PROPOSED SUBJ and VOTES
//    
////  @OneToMany//(mappedBy="id")
////    private List<Votes> votes;
////
////    public void addVote(Votes v){
////        votes.add(v);
////    }
//    
//
//    public ProposedSubject() {
//    }
//
//    public ProposedSubject(String name, String description, Boolean isAlive, String poolOption) {
//        this.name = name;
//        this.description = description;
//        this.isAlive = isAlive;
//        this.poolOption = poolOption;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public Boolean getIsAlive() {
//        return isAlive;
//    }
//
//    public void setIsAlive(Boolean isAlive) {
//        this.isAlive = isAlive;
//    }
//
//    public String getPoolOption() {
//        return poolOption;
//    }
//
//    public void setPoolOption(String poolOption) {
//        this.poolOption = poolOption;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//
//   
//    
//}
