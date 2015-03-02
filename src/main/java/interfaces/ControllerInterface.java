/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

import control.Controller.ProposedElectiveSubject;
import control.Controller.User;
import control.Controller.Vote;
import java.util.ArrayList;

/**
 *
 * @author root
 */
public interface ControllerInterface {

    //Users
    String authenticateUser(String userName, String password);
    
    String registerUser(User u);

    String deleteParticularUser();
    
    String updateParticularUser(String password, User newUserInfo);
    
    //Subjects
    ArrayList<ProposedElectiveSubject> getAllAvailableProposedElectiveSubjects();
    
    ProposedElectiveSubject addProposedElectiveSubject(ProposedElectiveSubject pes);
    //missing update, delete subject
    
    //Votes
    String addVoteFromParticularUser(ArrayList<Vote> votes);

    ArrayList<Vote> getAllVotesOfParticularUser();

    String deleteAllVotesOfParticularUser();

    Vote updateParticularVoteOfParticularUser(Vote nv);
}
