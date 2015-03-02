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
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//
///**
// *
// * @author Tomascik
// */
//@Entity
//public class Vote implements Serializable
//{
//
//    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Integer id;
//    private Integer roundNumber;
//    private Integer points;
//
////  @OneToOne(fetch=FetchType.LAZY, mappedBy="userName")
////  @JoinColumn(name="USERS_ID")
////  private User users;  
//    @OneToOne(optional = false)//(fetch=FetchType.LAZY)
//    private User user;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "SUBJECT_ID")
//    private ProposedSubject ps;
//
//    public Vote()
//    {
//    }
//
//    public Vote(Integer roundNumber, Integer points, User user, ProposedSubject ps)
//    {
//        this.roundNumber = roundNumber;
//        this.points = points;
//        this.user = user;
//        this.ps = ps;
//    }
//
//    public void setId(Integer id)
//    {
//        this.id = id;
//    }
//
//    
//
//    public Integer getRoundNumber()
//    {
//        return roundNumber;
//    }
//
//    public void setRoundNumber(Integer roundNumber)
//    {
//        this.roundNumber = roundNumber;
//    }
//
//    public Integer getPoints()
//    {
//        return points;
//    }
//
//    public void setPoints(Integer points)
//    {
//        this.points = points;
//    }
//
//    public ProposedSubject getPs()
//    {
//        return ps;
//    }
//
//    public void setPs(ProposedSubject ps)
//    {
//        this.ps = ps;
//    }
//
//    public Integer getId()
//    {
//        return id;
//    }
//
//    void add(List<Vote> votes)
//    {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//}
