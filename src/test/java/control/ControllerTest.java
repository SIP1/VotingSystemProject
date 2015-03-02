package control;

import control.Controller.ProposedElectiveSubject;
import control.Controller.User;
import control.Controller.Vote;
import java.util.ArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import utilities.AcceptanceProtocol;

public class ControllerTest {

    private Controller controller;

    public ControllerTest() {
    }

    @Before
    public void before() {
        controller = new Controller();
    }

    @Test
    public void testRegisterUser() {
        String userName = "boyko";
        String name = "Bobanka";
        String password = "67890";
        String email = "bobanka@bulgaria.bg";
        User user = controller.new User(1, userName, name, password, email);
        String expectedResult = AcceptanceProtocol.ACCOUNT_REGISTRATION_SUCCESS;
        String actualResult = controller.registerUser(user);
        assertThat("registerUser() should return confirmation string", actualResult,
                is(expectedResult));
    }

    @Test
    public void testAuthenticateUser() {
        String expectedResult = AcceptanceProtocol.ACCOUNT_LOGIN_SUCCESS;
        String userName = "bobkoo";
        String password = "12345";
        String actualResult = controller.authenticateUser(userName, password);
        assertThat("AuthenticateUser() should return confirmation string", actualResult,
                is(expectedResult));
    }

    @Test
    public void testDeleteParticularUser() {
        //Actual Deletion Test
        String expected = AcceptanceProtocol.ACCOUNT_DELETION;
        String actualResult = controller.deleteParticularUser();
        assertThat("deleteParticularUser() should return a confirmation if acc is deleted", actualResult, is(expected));
    }

    @Test
    public void testUpdateParticularUser() {
        //Register User
        String userName = "boyko";
        String name = "Bobanka";
        String password = "67890";
        String email = "bobanka@bulgaria.bg";
        User user = controller.new User(1, userName, name, password, email);
        controller.registerUser(user);
        //Authenticate User
        controller.authenticateUser(user.getUserName(), user.getPassword());
        //Actual Update Test
        String expectedResult = AcceptanceProtocol.ACCOUNT_UPDATE_SUCCESS;
        String actualResult = controller.updateParticularUser(user.getPassword(),
                controller.new User(1, "boyko", "changedName", "changedPassword",
                        "changedEmail@email"));
        assertThat("updateParticularUser() should return a confirmation String",
                actualResult, is(expectedResult));
    }

    @Test
    public void testAddSubject() {
        assertEquals((Integer) 0, controller.addProposedElectiveSubject(controller.new ProposedElectiveSubject("AI", "make it think", Boolean.TRUE)).getId());
    }

    @Test
    public void testGetAllAvailableSubjects() {
        ArrayList<ProposedElectiveSubject> testSubjects = new ArrayList();
        testSubjects.add(controller.new ProposedElectiveSubject("AI", "make it think", Boolean.TRUE));
        testSubjects.add(controller.new ProposedElectiveSubject("C#", "java like", Boolean.TRUE));
        testSubjects.add(controller.new ProposedElectiveSubject("C++", "complicated", Boolean.FALSE));
        testSubjects.add(controller.new ProposedElectiveSubject("Game Design", "WOW", Boolean.TRUE));
        controller.addProposedElectiveSubject(testSubjects.get(0));
        controller.addProposedElectiveSubject(testSubjects.get(1));
        controller.addProposedElectiveSubject(testSubjects.get(2));
        controller.addProposedElectiveSubject(testSubjects.get(3));
        assertEquals(testSubjects.size(), controller.getAllAvailableProposedElectiveSubjects().size());
    }

    @Test
    public void testAddVoteFromParticularUser() {
        String expected = AcceptanceProtocol.VOTE_REGISTRATION_SUCCESS;
        ArrayList<Vote> testVotes = new ArrayList();
        testVotes.add(controller.new Vote("bobkoo", 1, 4, 1));
        testVotes.add(controller.new Vote("bobkoo", 2, 3, 1));
        testVotes.add(controller.new Vote("bobkoo", 1, 2, 1));
        testVotes.add(controller.new Vote("bobkoo", 2, 5, 1));
        assertEquals("addVoteFromParticularUser() should return a confirmation String",
                expected, controller.addVoteFromParticularUser(testVotes));
    }

    @Test
    public void testGetAllVotesOfParticularUser() {
        ArrayList<Vote> expectedVotes = new ArrayList();
        expectedVotes.add(controller.new Vote("bobkoo", 1, 4, 1));
        expectedVotes.add(controller.new Vote("bobkoo", 2, 3, 1));
        expectedVotes.add(controller.new Vote("bobkoo", 1, 2, 1));
        expectedVotes.add(controller.new Vote("bobkoo", 2, 5, 1));
        controller.addVoteFromParticularUser(expectedVotes);
        assertThat("getAllVotesOfParticularUser() should return a list full with all votes",
                controller.getAllVotesOfParticularUser(), is(expectedVotes));
    }

    @Test
    public void updateUserVotes() {
        ArrayList<Vote> votes = new ArrayList();
        votes.add(controller.new Vote("bobkoo", 1, 4, 1));
        votes.add(controller.new Vote("bobkoo", 2, 3, 1));
        votes.add(controller.new Vote("bobkoo", 1, 2, 1));
        votes.add(controller.new Vote("bobkoo", 2, 5, 1));
        controller.addVoteFromParticularUser(votes);
        ArrayList<Vote> existingVotes = controller.getAllVotesOfParticularUser();
        Vote expectedVote = controller.new Vote("bobkoo", 2, 5, 2);
        expectedVote.setId(existingVotes.get(0).getId());
        Vote actualResult = controller.updateParticularVoteOfParticularUser(expectedVote);
        assertThat("updateUserVotes() should update the votes of a user", actualResult, is(expectedVote));
    }

    @Test
    public void testDeleteUserVotes() {
        String expected = AcceptanceProtocol.VOTE_DELETION_SUCCESS;
        String actual = controller.deleteAllVotesOfParticularUser();
        assertThat("deleteUserVotes() should delete ALL votes of that user", actual, is(expected));
    }
}
