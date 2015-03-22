package interfaces;

import models.FinalClass;
import models.ProposedSubject;
import models.User;
import models.UserType;
import java.util.List;

public interface ControllerInterface {

    //Users
    String authenticateUser(String userName, String password);
    
    User getUser();
    
    List<User> getUsersByUserType(UserType ut);
    
    List<User> getAllUsers();
    
    //Subjects
    List<ProposedSubject> getAllAliveProposedElectiveSubjects();
    
    List<ProposedSubject> getAllProposedElectiveSubjects();
    
    ProposedSubject addProposedElectiveSubject(ProposedSubject pes);
    
    //Votes
    String addVoteFromParticularUser(String vote1, String vote2, String vote3, String vote4);

    void setSatisfactionForStudent(int a[], int b[], User student);
    
    int getOverallSatisfaction(int[] a, int[] b);
    
    List<User> getAllStudentsByUnsatisfactionRate();
    
    List<User> getAllTeachers();
    
    String addProposedSubject(ProposedSubject ps, int[] selectedIndices);
    
    String addSubjectsToPolls(int[] a, int[] b);
    
    String selectSubjectsForRound1(int[] selectedIndexes);
    
    List<User> getAllStudents ();
    
    List<ProposedSubject> getSubjectsByPool(String pool);
    
    FinalClass addNewClass(List<User> students, ProposedSubject subject);
    
    List<FinalClass> getAllClasses();
    
    List<User> getStudentsForClass(FinalClass c);
    
    String sendMail();
    
}
