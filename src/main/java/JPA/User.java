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
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.Id;
//import javax.persistence.Inheritance;
//import javax.persistence.InheritanceType;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//
///**
// *
// * @author Tomascik
// */
//@Entity
//
//public class User implements Serializable {
//    private static final long serialVersionUID = 1L;
//    @Id
//    private String userName;
//    private String name;
//    private String email;
//    private String password;
//    
////  @OneToOne(fetch=FetchType.LAZY)
////  @JoinColumn(name="ProposedSubjectsTeacher_ID")
////  private ProposedSubjectsTeacher proposedSubjectTeacher;
//    
//  @OneToOne(fetch=FetchType.LAZY)
//  @JoinColumn(name="userType")
//  private UserType userTypes;
//  
//  
//   @OneToOne(cascade=CascadeType.ALL, 
//       mappedBy="user",targetEntity=Vote.class)
//       private Vote votes;
//    
//
//  
//  // @ManyToMany//(mappedBy="id")
////    List<ProposedSubject> proposedSubjects = new ArrayList();
//   
//
//   
//    
//    public void addProposedSubjects(ProposedSubject ps){
//      proposedSubjects.add(ps);  
//    } 
//
//    public User() {
//    }
//
//
//    public UserType getUserTypes() {
//        return userTypes;
//    }
//
//    public void setUserTypes(UserType userTypes)
//    {
//        this.userTypes = userTypes;
//    }
//    
//    
//
//    public User(String userName, String name, String email, String password, UserType userTypes) {
//        this.userName = userName;
//        this.name = name;
//        this.email = email;
//        this.password = password;
//        this.userTypes = userTypes;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
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
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
////    public ArrayList<Vote> getVotes()
////    {
////        return votes;
////    }
//    
////    public Vote addVote(Vote nv){
////        votes.add(nv);
////        return nv;
////    }
//    
//    
//    
//}
