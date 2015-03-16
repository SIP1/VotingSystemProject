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
    
    User updateParticularUser(String password, User newUserInfo);
    
    User getUser();
    
    List<User> getUsersByUserType(UserType ut);
    
    List<User> getAllUsers();
    
    //UserTypes
    String addUserType(String name);
    
    UserType updateParticularUserType(Integer id, UserType newUserType);
    
    String deleteParticularUserType(Integer id);
    
    List<UserType> getAllUserTypes();
    
    //Subjects
    List<ProposedSubject> getAllAliveProposedElectiveSubjects();
    
    List<ProposedSubject> getAllProposedElectiveSubjects();
    
    ProposedSubject addProposedElectiveSubject(ProposedSubject pes);
    
    ProposedSubject updateParticularElectiveSubject(Integer id, ProposedSubject newProposedSubject);
    
    String deleteParticularElectiveSubject(Integer id);
    
    //Votes
    String addVoteFromParticularUser(String vote1, String vote2, String vote3, String vote4, int roundNumber);

    List<Vote> getAllVotesOfParticularUser();

    String deleteAllVotesOfParticularUser();

    Vote updateParticularVoteOfParticularUser(Vote nv);
    
    void setSatisfactionForStudent(int a[], int b[], User student);
    
    int getOverallSatisfaction(int[] a, int[] b);
    
    List<User> getAllStudentsByUnsatisfactionRate();
    
    List<User> getAllTeachers();
    
    String addProposedSubject(ProposedSubject ps, int[] selectedIndices);
    
    String addSubjectsToPolls(int[] a, int[] b);
    
    String selectSubjectsForRound1(int[] selectedIndexes);
    
    List<User> getAllStudents ();
    
    List<ProposedSubject> getSubjectsByPool(String pool);
}
