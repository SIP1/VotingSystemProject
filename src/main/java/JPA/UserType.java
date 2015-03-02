///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package JPA;
//
//import java.io.Serializable;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToOne;
//
///**
// *
// * @author Tomascik
// */
//@Entity
//public class UserType implements Serializable {
//    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    private String type;
//    
//  @OneToOne(fetch=FetchType.LAZY, mappedBy="userType")
//  private User users;
//
//    public UserType() {
//    }
//
//    public UserType(String type) {
//        this.type = type;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//   
//}
