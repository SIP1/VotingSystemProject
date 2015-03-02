package interfaces;

import JPA2.ProposedSubject;
import JPA2.User;
import JPA2.UserType;
import JPA2.Vote;
import java.util.ArrayList;
import java.util.List;

public interface ControllerInterface {

    //Users
    String authenticateUser(String userName, String password);
    
    String registerUser(User u);

    String deleteParticularUser();
    
    String updateParticularUser(String password, User newUserInfo);
    
    User getUser();
    
    //UserTypes
    String addUserType(String name);
    
    String updateParticularUserType(Integer id);
    
    String deleteParticularUserType(Integer id);
    
    List<UserType> getAllUserTypes();
    
    //Subjects
    List<ProposedSubject> getAllAvailableProposedElectiveSubjects();
    
    ProposedSubject addProposedElectiveSubject(ProposedSubject pes);
    
    String updateParticularElectiveSubject(Integer id);
    
    String deleteParticularElectiveSubject(Integer id);
    
    //Votes
    String addVoteFromParticularUser(ArrayList<Vote> votes);

    List<Vote> getAllVotesOfParticularUser();

    String deleteAllVotesOfParticularUser();

    Vote updateParticularVoteOfParticularUser(Vote nv);
}
