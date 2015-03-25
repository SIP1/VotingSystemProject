package interfaces;

import models.FinalClass;
import models.ProposedSubject;
import models.User;
import models.UserType;
import java.util.List;

public interface ControllerInterface {

    String authenticateUser(String userName, String password);

    int getRoundNumber();

    int incrementRoundNumber();

    User getUser();

    List<User> getUsersByUserType(UserType ut);

    List<User> getAllUsers();

    List<ProposedSubject> getAllAliveProposedElectiveSubjects();

    List<ProposedSubject> getAllProposedElectiveSubjects();

    ProposedSubject addProposedElectiveSubject(ProposedSubject pes);

    String addVoteFromParticularUser(String vote1, String vote2, String vote3, String vote4);

    void setSatisfactionForStudent(int a[], int b[], User student);

    int getOverallSatisfaction(int[] a, int[] b);

    List<User> getAllStudentsByUnsatisfactionRate();

    List<User> getAllTeachers();

    String addProposedSubject(ProposedSubject ps, int[] selectedIndices);

    String addSubjectsToPools(int[] a, int[] b);

    String selectSubjectsForRound1(int[] selectedIndexes);

    List<User> getAllStudents();

    List<ProposedSubject> getSubjectsByPool(String pool);

    FinalClass addNewClass(List<User> students, ProposedSubject subject);

    FinalClass editStudentsInClass(List<User> students, int classIndex);

    FinalClass editTeachersInClass(List<User> teachers, int classIndex);

    List<FinalClass> getAllClasses();

    List<User> getStudentsForClass(FinalClass c);

    String sendMail();

}
