package control;

import models.FinalClass;
import models.ProposedSubject;
import models.User;
import models.UserType;
import com.thoughtworks.xstream.XStream;
import java.util.ArrayList;
import java.util.List;
import mocks.ControllerMock;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import utilities.AcceptanceProtocol;

/**
 *
 * @author Marek
 */
public class ControllerTest {
    
    private ControllerMock controller;
    
    @Before
    public void setUp() {
        controller = new ControllerMock();
        System.out.println("Controller SET UP");
    }

    /**
     * Test of authenticateUser method, of class Controller.
     */
    @Test
    public void testAuthenticateUser() {
        System.out.println("authenticateUser");
        String expectedResult = AcceptanceProtocol.ACCOUNT_LOGIN_SUCCESS;
        String userName = "TestUser";
        String password = "12345";
        String actualResult = controller.authenticateUser(userName, password);
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Test of authenticateUser method, of class Controller.
     */
    @Test
    public void testFailAuthenticateUser() {
        System.out.println("failAuthenticateUser");
        String expectedResult = AcceptanceProtocol.ACCOUNT_LOGIN_ERROR;
        String userName = "TestUser12";
        String password = "";
        String actualResult = controller.authenticateUser(userName, password);
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Test of getUser method, of class Controller.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        User expResult = controller.user;
        User result = controller.getUser();
        assertEquals(expResult, result);
    }

    /**
     * Test of addProposedElectiveSubject method, of class Controller.
     */
    @Test
    public void testAddProposedElectiveSubject() {
        System.out.println("addProposedElectiveSubject");
        ProposedSubject expectedResult = new ProposedSubject("AI", "testing add", true, "A");
        ProposedSubject actualResult = controller.addProposedElectiveSubject(expectedResult);
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Test of addProposedElectiveSubject method, of class Controller.
     */
    @Test
    public void testAddNullProposedElectiveSubject() {
        System.out.println("addNullProposedElectiveSubject");
        ProposedSubject expectedResult = null;
        ProposedSubject actualResult = controller.addProposedElectiveSubject(expectedResult);
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Test of addVoteFromParticularUser method, of class Controller.
     */
    @Test
    public void testAddVoteFromParticularUser() {
        System.out.println("addVoteFromParticularUser");
        String vote1 = "Vote 1";
        String vote2 = "Vote 2";
        String vote3 = "Vote 3";
        String vote4 = "Vote 4";
        String expected = AcceptanceProtocol.VOTE_REGISTRATION_SUCCESS;
        assertEquals(expected, controller.addVoteFromParticularUser(vote1, vote2, vote3, vote4));
    }

    /**
     * Test of addVoteFromParticularUser method, of class Controller.
     */
    @Test
    public void testAddNullVoteFromParticularUser() {
        System.out.println("addNullVoteFromParticularUser");
        String vote1 = "";
        String vote2 = "";
        String vote3 = null;
        String vote4 = "";
        String expected = AcceptanceProtocol.VOTE_REGISTRATION_ERROR_AMMOUNT;
        assertEquals(expected, controller.addVoteFromParticularUser(vote1, vote2, vote3, vote4));
    }

    /**
     * Test of getUsersByUserType method, of class Controller.
     */
    @Test
    public void testGetUsersByUserType() {
        System.out.println("getUsersByUserType");
        UserType ut = controller.userTypes.get(0);
        List<User> expResult = new ArrayList<>();
        expResult.add(controller.users.get(0));
        expResult.add(controller.users.get(3));
        List<User> result = controller.getUsersByUserType(ut);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsersByUserType method, of class Controller.
     */
    @Test
    public void testGetUsersByNonexistingUserType() {
        System.out.println("getUsersByNonexistingUserType");
        UserType ut = new UserType("test");
        List<User> expResult = new ArrayList();
        List<User> result = controller.getUsersByUserType(ut);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllUsers method, of class Controller.
     */
    @Test
    public void testGetAllUsers() {
        System.out.println("getAllUsers");
        List<User> expResult = controller.users;
        List<User> result = controller.getAllUsers();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSatisfactionForStudent method, of class Controller.
     */
    @Test
    public void testSetSatisfactionForStudent() {
        System.out.println("setSatisfactionForStudent");
        int[] a = new int[]{1};
        int[] b = new int[]{2};
        User student = controller.user;
        controller.setSatisfactionForStudent(a, b, student);
        int expected = 75;
        assertEquals(expected, controller.user.getSatisfaction());
    }
    
    @Test
    public void testSetNullSatisfactionForStudent() {
        System.out.println("setSNullatisfactionForStudent");
        int[] a = new int[]{};
        int[] b = new int[]{};
        User student = controller.user;
        controller.setSatisfactionForStudent(a, b, student);
        int expected = 0;
        assertEquals(expected, controller.user.getSatisfaction());
    }

    /**
     * Test of getOverallSatisfaction method, of class Controller.
     */
    @Test
    public void testGetOverallSatisfaction() {
        System.out.println("getOverallSatisfaction");
        int[] a = new int[]{1};
        int[] b = new int[]{2};
        int expected = 50;
        int actual = controller.getOverallSatisfaction(a, b);
        assertEquals(expected, actual);
    }

    /**
     * Test of getOverallSatisfaction method, of class Controller.
     */
    @Test
    public void testGetNullOverallSatisfaction() {
        System.out.println("getNullOverallSatisfaction");
        int[] a = new int[]{};
        int[] b = new int[]{};
        int expected = 0;
        int actual = controller.getOverallSatisfaction(a, b);
        assertEquals(expected, actual);
    }

    /**
     * Test of getTop5UnsatissfiedStudents method, of class Controller.
     */
    @Test
    public void testGetTop5UnsatissfiedStudents() {
        System.out.println("getTop5UnsatissfiedStudents");
        List<User> expResult = new ArrayList<>();
        expResult.add(controller.users.get(0));
        expResult.add(controller.users.get(3));
        List<User> result = controller.getAllStudentsByUnsatisfactionRate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllTeachers method, of class Controller.
     */
    @Test
    public void testGetAllTeachers() {
        List<User> expResult = new ArrayList<>();
        expResult.add(controller.users.get(1));
        expResult.add(controller.users.get(4));
        List<User> result = controller.getAllTeachers();
        assertEquals(expResult, result);
    }

    /**
     * Test of addProposedSubject method, of class Controller.
     */
    @Test
    public void testAddProposedSubject() {
        System.out.println("addProposedSubject");
        ProposedSubject ps = new ProposedSubject("Test", "It's a test", true, null);
        int[] selectedIndices = new int[]{0};
        String expResult = AcceptanceProtocol.NEW_PROPOSED_SUBJECT_SUCCESS;
        String result = controller.addProposedSubject(ps, selectedIndices);
        assertEquals(expResult, result);
    }

    /**
     * Test of addProposedSubject method, of class Controller.
     */
    @Test
    public void testAddNullProposedSubject() {
        System.out.println("addNullProposedSubject");
        ProposedSubject ps = null;
        int[] selectedIndices = new int[]{0};
        String expResult = AcceptanceProtocol.NEW_PROPOSED_SUBJECT_FAILURE_SUBJECT;
        String result = controller.addProposedSubject(ps, selectedIndices);
        assertEquals(expResult, result);
    }

    /**
     * Test of addProposedSubject method, of class Controller.
     */
    @Test
    public void testAddProposedSubjectNullTeacher() {
        System.out.println("addNullProposedSubjectNullTeacher");
        ProposedSubject ps = new ProposedSubject("Test", "It's a test", true, null);
        int[] selectedIndices = new int[]{};
        String expResult = AcceptanceProtocol.NEW_PROPOSED_SUBJECT_FAILURE_TEACHER;
        String result = controller.addProposedSubject(ps, selectedIndices);
        assertEquals(expResult, result);
    }

    /**
     * Test of addSubjectsToPolls method, of class Controller.
     */
    @Test
    public void testAddSubjectsToPolls() {
        System.out.println("addSubjectsToPolls");
        int[] a = new int[]{1, 2, 3, 4};
        int[] b = new int[]{5, 6, 7, 8};
        String expResult = AcceptanceProtocol.SUBJECTS_ADDED_TO_POLLS_SUCCESS;
        String result = controller.addSubjectsToPolls(a, b);
        assertEquals(expResult, result);
    }

    /**
     * Test of addSubjectsToPolls method, of class Controller.
     */
    @Test
    public void testAdd0SubjectsToPolls() {
        System.out.println("add0SubjectsToPolls");
        int[] a = new int[]{};
        int[] b = new int[]{};
        String expResult = AcceptanceProtocol.SUBJECTS_ADDED_TO_POLLS_FAILURE;
        String result = controller.addSubjectsToPolls(a, b);
        assertEquals(expResult, result);
    }

    /**
     * Test of addSubjectsToPolls method, of class Controller.
     */
    @Test
    public void testAdd2SubjectsToPolls() {
        System.out.println("add2SubjectsToPolls");
        int[] a = new int[]{1};
        int[] b = new int[]{5};
        String expResult = AcceptanceProtocol.SUBJECTS_ADDED_TO_POLLS_FAILURE;
        String result = controller.addSubjectsToPolls(a, b);
        assertEquals(expResult, result);
    }

    /**
     * Test of addSubjectsToPolls method, of class Controller.
     */
    @Test
    public void testAddNullSubjectsToPolls() {
        System.out.println("addNullSubjectsToPolls");
        int[] a = new int[]{};
        int[] b = new int[]{5, 6, 8};
        String expResult = AcceptanceProtocol.SUBJECTS_ADDED_TO_POLLS_FAILURE;
        String result = controller.addSubjectsToPolls(a, b);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllAliveProposedElectiveSubjects method, of class Controller.
     */
    @Test
    public void testGetAllAliveProposedElectiveSubjects() {
        System.out.println("getAllAliveProposedElectiveSubjects");
        List<ProposedSubject> allSubjects = controller.proposedSubjects;
        List<ProposedSubject> expResult = new ArrayList<>();
        for (ProposedSubject subject : allSubjects) {
            if (subject.isItAlive()) {
                expResult.add(subject);
            }
        }
        List<ProposedSubject> result = controller.getAllAliveProposedElectiveSubjects();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllProposedElectiveSubjects method, of class Controller.
     */
    @Test
    public void testGetAllProposedElectiveSubjects() {
        System.out.println("getAllProposedElectiveSubjects");
        List<ProposedSubject> expResult = controller.proposedSubjects;
        List<ProposedSubject> result = controller.getAllProposedElectiveSubjects();
        assertEquals(expResult, result);
    }

    /**
     * Test of selectSubjectsForRound1 method, of class Controller.
     */
    @Test
    public void testSelectSubjectsForRound1() {
        System.out.println("selectSubjectsForRound1");
        int[] selectedIndexes = new int[]{1, 2, 3, 4};
        String expResult = AcceptanceProtocol.SUBJECTS_ADDED_TO_ROUND_1_SUCCESS;
        String result = controller.selectSubjectsForRound1(selectedIndexes);
        assertEquals(expResult, result);
    }

    /**
     * Test of selectSubjectsForRound1 method, of class Controller.
     */
    @Test
    public void testSelect0SubjectsForRound1() {
        System.out.println("select0SubjectsForRound1");
        int[] selectedIndexes = new int[]{};
        String expResult = AcceptanceProtocol.SUBJECTS_ADDED_TO_ROUND_1_FAILURE;
        String result = controller.selectSubjectsForRound1(selectedIndexes);
        assertEquals(expResult, result);
    }

    /**
     * Test of selectSubjectsForRound1 method, of class Controller.
     */
    @Test
    public void testSelect2SubjectsForRound1() {
        System.out.println("select2SubjectsForRound1");
        int[] selectedIndexes = new int[]{1, 2};
        String expResult = AcceptanceProtocol.SUBJECTS_ADDED_TO_ROUND_1_FAILURE;
        String result = controller.selectSubjectsForRound1(selectedIndexes);
        assertEquals(expResult, result);
    }

    /**
     * Test of selectSubjectsForRound1 method, of class Controller.
     */
    @Test
    public void testSelectNullSubjectsForRound1() {
        System.out.println("selectNullSubjectsForRound1");
        int[] selectedIndexes = new int[]{1};
        String expResult = AcceptanceProtocol.SUBJECTS_ADDED_TO_ROUND_1_FAILURE;
        String result = controller.selectSubjectsForRound1(selectedIndexes);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllStudentsByUnsatisfactionRate method, of class Controller.
     */
    @Test
    public void testGetAllStudentsByUnsatisfactionRate() {
        System.out.println("getAllStudentsByUnsatisfactionRate");
        List<User> expResult = new ArrayList<>();
        expResult.add(controller.users.get(0));
        expResult.add(controller.users.get(3));
        List<User> result = controller.getAllStudentsByUnsatisfactionRate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllStudents method, of class Controller.
     */
    @Test
    public void testGetAllStudents() {
        System.out.println("getAllStudents");
        List<User> expResult = new ArrayList<>();
        expResult.add(controller.users.get(0));
        expResult.add(controller.users.get(3));
        List<User> result = controller.getAllStudents();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSubjectsByPool method, of class Controller.
     */
    @Test
    public void testGetSubjectsByPool() {
        System.out.println("getSubjectsByPool");
        String pool = "B";
        List<ProposedSubject> expResult = new ArrayList<>();
        expResult.add(controller.proposedSubjects.get(0));
        expResult.add(controller.proposedSubjects.get(3));
        expResult.add(controller.proposedSubjects.get(4));
        List<ProposedSubject> result = controller.getSubjectsByPool(pool);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllClasses method, of class Controller.
     */
    @Test
    public void testGetAllClasses() {
        System.out.println("getAllClasses");
        List<FinalClass> expResult = controller.finalClasses;
        List<FinalClass> result = controller.getAllClasses();
        assertEquals(expResult, result);
    }

    /**
     * Test of getStudentsForClass method, of class Controller.
     */
    @Test
    public void testGetStudentsForClass() {
        System.out.println("getStudentsForClass");
        FinalClass c = controller.finalClasses.get(0);
        List<User> expResult = new ArrayList<>();
        expResult.add(controller.users.get(0));
        expResult.add(controller.users.get(3));
        List<User> result = controller.getStudentsForClass(c);
        assertEquals(expResult, result);
    }

    /**
     * Test of sendMail method, of class Controller.
     */
    @Test
    public void testSendMail() {
        System.out.println("sendMail");
        XStream x = new XStream();
        String expResult = x.toXML(controller.finalClasses);
        String result = controller.sendMail();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRoundNumber method, of class Controller.
     */
    @Test
    public void testGetRoundNumber() {
        System.out.println("getRoundNumber");
        int expResult = 0;
        int result = controller.getRoundNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of incrementRoundNumber method, of class Controller.
     */
    @Test
    public void testIncrementRoundNumber() {
        System.out.println("incrementRoundNumber");
        int expResult = 1;
        int result = controller.incrementRoundNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of addNewClass method, of class Controller.
     */
    @Test
    public void testAddNewClass() {
        System.out.println("addNewClass");
        List<User> students = controller.students;
        ProposedSubject subject = controller.proposedSubjects.get(0);
        FinalClass expResult = new FinalClass(subject);
        expResult.setStudents(students);
        FinalClass result = controller.addNewClass(students, subject);
        assertEquals(expResult.getSubject(), result.getSubject());
        assertEquals(expResult.getStudents(),result.getStudents());
    }

    /**
     * Test of addNewClass method, of class Controller.
     */
    @Test
    public void testAddNewNullClass() {
        System.out.println("testAddNewNullClass");
        List<User> students = controller.students;
        ProposedSubject subject = null;
        FinalClass expResult = null;
        FinalClass result = controller.addNewClass(students, subject);
        assertEquals(expResult, result);
    }

    /**
     * Test of addNewClass method, of class Controller.
     */
    @Test
    public void testAddNewClass0Students() {
        System.out.println("addNewClass0Students");
        List<User> students = new ArrayList();
        ProposedSubject subject = controller.proposedSubjects.get(1);;
        FinalClass expResult = null;
        FinalClass result = controller.addNewClass(students, subject);
        assertEquals(expResult, result);
    }

    /**
     * Test of editStudentsInClass method, of class Controller.
     */
    @Test
    public void testEditNullStudentsInClass() {
        System.out.println("editNullStudentsInClass");
        List<User> students = null;
        int classIndex = 0;
        FinalClass expResult = null;
        FinalClass result = controller.editStudentsInClass(students, classIndex);
        assertEquals(expResult, result);
    }

    /**
     * Test of editStudentsInClass method, of class Controller.
     */
    @Test
    public void testEditStudentsInNullClass() {
        System.out.println("editStudentsInNullClass");
        List<User> students = null;
        int classIndex = 120;
        FinalClass expResult = null;
        FinalClass result = controller.editStudentsInClass(students, classIndex);
        assertEquals(expResult, result);
    }

    /**
     * Test of editStudentsInClass method, of class Controller.
     */
    @Test
    public void testEditStudentsInClass() {
        System.out.println("editStudentsInClass");
        List<User> students = controller.students;
        int classIndex = 0;
        FinalClass expResult = null;
        FinalClass result = controller.editStudentsInClass(students, classIndex);
        assertEquals(expResult, result);
    }

    /**
     * Test of editTeachersInClass method, of class Controller.
     */
    @Test
    public void testEditNullTeachersInClass() {
        System.out.println("editNullTeachersInClass");
        List<User> teachers = null;
        int classIndex = 0;
        FinalClass expResult = null;
        FinalClass result = controller.editTeachersInClass(teachers, classIndex);
        assertEquals(expResult, result);
    }

    /**
     * Test of editTeachersInClass method, of class Controller.
     */
    @Test
    public void testEditTeachersInNullClass() {
        System.out.println("editNullTeachersInNullClass");
        List<User> teachers = controller.teachers;
        int classIndex = 120;
        FinalClass expResult = null;
        FinalClass result = controller.editTeachersInClass(teachers, classIndex);
        assertEquals(expResult, result);
    }

    /**
     * Test of editTeachersInClass method, of class Controller.
     */
    @Test
    public void testEditTeachersInClass() {
        System.out.println("editTeachersInClass");
        List<User> teachers = controller.teachers;
        int classIndex = 0;
        FinalClass expResult = null;
        FinalClass result = controller.editTeachersInClass(teachers, classIndex);
        assertEquals(expResult, result);
    }
}
