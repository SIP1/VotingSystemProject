package control;

import JPA2.ProposedSubject;
import JPA2.User;
import JPA2.UserType;
import JPA2.Vote;
import java.util.ArrayList;
import java.util.List;
import mocks.ControllerMock;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import utilities.AcceptanceProtocol;

public class ControllerTestBoyko
{

    private ControllerMock controller;

    public ControllerTestBoyko()
    {
    }

    @Before
    public void before()
    {
        controller = new ControllerMock();
    }

     @Test
    public void testAuthenticateUser()
    {
        String expectedResult = AcceptanceProtocol.ACCOUNT_LOGIN_SUCCESS;
        String userName = "bobkoo";
        String password = "12345";
        String actualResult = controller.authenticateUser(userName, password);
        assertThat("AuthenticateUser() should return confirmation string", actualResult,
                   is(expectedResult));
    }
    
    @Test
    public void testRegisterUser()
    {
        String userName = "mada";
        String name = "mada";
        String password = "qwerty";
        String email = "bobanka@bulgaria.bg";
        User user = new User(userName, name, email, password, new UserType("Student"));
        String expectedResult = AcceptanceProtocol.ACCOUNT_REGISTRATION_SUCCESS;
        String actualResult = controller.registerUser(user);
        assertThat("registerUser() should return confirmation string", actualResult,
                   is(expectedResult));
    }

    @Test
    public void testDeleteParticularUser()
    {
        //Actual Deletion Test
        String expected = AcceptanceProtocol.ACCOUNT_DELETION;
        String actualResult = controller.deleteParticularUser();
        assertThat("deleteParticularUser() should return a confirmation if acc is deleted", actualResult, is(expected));
    }

    @Test
    public void testUpdateParticularUser()
    {
        String userName = "boyko";
        String password = "67890a";
        //Authenticate User
        String haha = controller.authenticateUser(userName, password);
        //Actual Update Test
        String expectedResult = AcceptanceProtocol.ACCOUNT_UPDATE_SUCCESS;
        String actualResult = controller.updateParticularUser(password, new User("bobkoo", "boyko", "user@email", "12345", new UserType("Student")));
        assertThat("updateParticularUser() should return a confirmation String",
                   actualResult, is(expectedResult));
    }

    //Test get User
    
    //Test addUserType
    
    //Test updateParticularUserType
    
    //Test deleteParticularUserType
    
    //Test getAllUserTypes
    
    @Test
    public void testAddSubject()
    {
        ProposedSubject ps1 = new ProposedSubject("AI", "makeasasd", Boolean.TRUE, "A");
        ps1.setId(0);
        Integer expectedResult = ps1.getId();
        Integer actualResult = controller.addProposedElectiveSubject(ps1).getId();
        assertThat("addSubject() should return a new ProposedSubject object", actualResult, is(expectedResult));

    }

    @Test
    public void testGetAllAvailableSubjects()
    {
        ArrayList<ProposedSubject> testSubjects = new ArrayList();
        testSubjects.add(new ProposedSubject("AI", "makeasasd", Boolean.TRUE, "A"));
        testSubjects.add(new ProposedSubject("C#", "java like", Boolean.TRUE, "B"));
        testSubjects.add(new ProposedSubject("C++", "complicated", Boolean.FALSE, "A"));
        testSubjects.add(new ProposedSubject("Game Design", "WOW", Boolean.TRUE, "A"));
        controller.addProposedElectiveSubject(testSubjects.get(0));
        controller.addProposedElectiveSubject(testSubjects.get(1));
        controller.addProposedElectiveSubject(testSubjects.get(2));
        controller.addProposedElectiveSubject(testSubjects.get(3));
        assertEquals(testSubjects.size(), controller.getAllAvailableProposedElectiveSubjects().size());
    }
    
    //Test Update ParticularElectiveSubject

    //Test Delete ParticularES
//    @Test
//    public void testDeleteParticularElectiveSubject()
//    {
//        ArrayList<ProposedSubject> testSubjects = new ArrayList();
//        testSubjects.add(new ProposedSubject("AI", "makeasasd", Boolean.TRUE, "A"));
//        testSubjects.add(new ProposedSubject("C#", "java like", Boolean.TRUE, "B"));
//        controller.addProposedElectiveSubject(testSubjects.get(0));
//        controller.addProposedElectiveSubject(testSubjects.get(1));
//
//    }

    @Test
    public void testAddVoteFromParticularUser()
    {
        ArrayList<Vote> testVotes = votesGenerator();
        String expected = AcceptanceProtocol.VOTE_REGISTRATION_SUCCESS;
        assertEquals("addVoteFromParticularUser() should return a confirmation String",
                     expected, true);
    }

    @Test
    public void testGetAllVotesOfParticularUser()
    {
        //First we have to add votes
        List<Vote> testVotes = votesGenerator();
       // controller.addVoteFromParticularUser((ArrayList<Vote>) testVotes);
        assertThat("getAllVotesOfParticularUser() should return a list full with all votes",
                   controller.getAllVotesOfParticularUser(), is(testVotes));
    }
    @Test
    public void updateUserVotes()
    {
        //First we have to add votes
        List<Vote> testVotes = votesGenerator();
        //controller.addVoteFromParticularUser((ArrayList<Vote>) testVotes);
        //Then we get them
        List<Vote> existingVotes = controller.getAllVotesOfParticularUser();
        //Now we set the new values of the vote
        User newUser = new User("bobkoo", "boyko", "user@email", "12345", new UserType("Student"));
        ProposedSubject newps = new ProposedSubject("AI", "makeasasd", Boolean.TRUE, "B");
        Vote expectedVote = new Vote(newUser, newps, 1, 2);
        //We use the same ID as the one we received from getAll
        expectedVote.setId(existingVotes.get(0).getId());
        //We update and save the result
        Vote actualResult = controller.updateParticularVoteOfParticularUser(expectedVote);
        assertThat("updateUserVotes() should update the votes of a user", actualResult,
                   is(expectedVote));
    }

    @Test
    public void testDeleteUserVotes()
    {
        //First we have to add votes
        ArrayList<Vote> testVotes = votesGenerator();
//        controller.addVoteFromParticularUser(testVotes);
        //Then we try to delete them
        String expectedResult = AcceptanceProtocol.VOTE_DELETION_SUCCESS;
        String actualResult = controller.deleteAllVotesOfParticularUser();
        assertThat("deleteUserVotes() should delete ALL votes of that user", actualResult,
                   is(expectedResult));
    }

    private ArrayList<Vote> votesGenerator()
    {
        User user1 = new User("bobkoo", "boyko", "user@email", "12345", new UserType("Student"));
        ProposedSubject ps1 = new ProposedSubject("Game Design", "WOW", Boolean.TRUE, "A");
        ProposedSubject ps2 = new ProposedSubject("C#", "java like", Boolean.TRUE, "A");
        ProposedSubject ps3 = new ProposedSubject("C++", "complicated", Boolean.FALSE, "B");
        ProposedSubject ps4 = new ProposedSubject("AI", "makeasasd", Boolean.TRUE, "B");
        ps1.setId(0);
        ps2.setId(1);
        ps3.setId(2);
        ps4.setId(3);
        ArrayList<Vote> testVotes = new ArrayList();
        testVotes.add(new Vote(user1, ps1, 1, 2));
        testVotes.add(new Vote(user1, ps2, 1, 1));
        testVotes.add(new Vote(user1, ps3, 1, 1));
        testVotes.add(new Vote(user1, ps4, 1, 2));
        return testVotes;
    }
}
