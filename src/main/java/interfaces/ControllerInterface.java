//package interfaces;
//
//import JPA.ProposedSubject;
//import JPA.User;
//import JPA.Vote;
//import java.util.ArrayList;
//
//public interface ControllerInterface {
//
//    //Users
//    String authenticateUser(String userName, String password);
//    
//    String registerUser(User u);
//
//    String deleteParticularUser();
//    
//    String updateParticularUser(String password, User newUserInfo);
//    
//    User getUser();
//    
//    //Subjects
//    ArrayList<ProposedSubject> getAllAvailableProposedElectiveSubjects();
//    
//    ProposedSubject addProposedElectiveSubject(ProposedSubject pes);
//    
//    String updateParticularElectiveSubject(Integer id);
//    
//    String deleteParticularElectiveSubject(Integer id);
//    
//    //Votes
//    String addVoteFromParticularUser(ArrayList<Vote> votes);
//
//    ArrayList<Vote> getAllVotesOfParticularUser();
//
//    String deleteAllVotesOfParticularUser();
//
//    Vote updateParticularVoteOfParticularUser(Vote nv);
//}
