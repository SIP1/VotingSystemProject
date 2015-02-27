/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author Tomascik
 */
@Entity
public class ProposedSubjectsTeacher implements Serializable {
    private static final long serialVersionUID = 1L;

    
 //    @OneToOne(fetch=FetchType.LAZY, mappedBy="proposedSubjectsTeacher_proposedSubjects")
 //     private ProposedSubjects proposedSubjects;
     
 //    @OneToOne(fetch=FetchType.LAZY, mappedBy="proposedSubjectsTeacher_Users")
 //     private Users user_teacher;
     
//       @OneToOne
//    @JoinColumns({
//        @JoinColumn(name="proposedSubjects", referencedColumnName="proposedSubjectsTeacher_proposedSubjects"),
//        @JoinColumn(name="user_teacher", referencedColumnName="proposedSubjectsTeacher_Users")
//    })
    
    
    @Id
    private String userNameteacher;
    @Id
    private String proposedSubjectsID;
    
  @Column(name="IS_PROJECT_LEAD")
  private boolean isProjectLead;
  @ManyToOne
  @PrimaryKeyJoinColumn(name="USERS_ID", referencedColumnName="ID")
  /* if this JPA model doesn't create a table for the "PROJ_EMP" entity,
  *  please comment out the @PrimaryKeyJoinColumn, and use the ff:
  *  @JoinColumn(name = "employeeId", updatable = false, insertable = false)
  * or @JoinColumn(name = "employeeId", updatable = false, insertable = false, referencedColumnName = "id")
  */
  private Users users;
  @ManyToOne
  @PrimaryKeyJoinColumn(name="PROPPOSEDSUBJECT_ID", referencedColumnName="ID")
  private ProposedSubjects proposedSubjects;
    
    
    
    
    
    
    
    
    
    
    
    

    public ProposedSubjectsTeacher() {
    }

    public ProposedSubjectsTeacher(String userNameteacher, String proposedSubjectsID) {
        this.userNameteacher = userNameteacher;
        this.proposedSubjectsID = proposedSubjectsID;
    }

    public String getUserNameteacher() {
        return userNameteacher;
    }

    public void setUserNameteacher(String userNameteacher) {
        this.userNameteacher = userNameteacher;
    }

    public String getProposedSubjectsID() {
        return proposedSubjectsID;
    }

    public void setProposedSubjectsID(String proposedSubjectsID) {
        this.proposedSubjectsID = proposedSubjectsID;
    }

  

    
}



