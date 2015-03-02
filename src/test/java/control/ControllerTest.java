//package control;
//
//import JPA.ProposedSubject;
//import JPA.User;
//import JPA.UserType;
//import JPA.Vote;
//import java.util.ArrayList;
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.*;
//import org.junit.Before;
//import org.junit.Test;
//import utilities.AcceptanceProtocol;
//
//public class ControllerTest
//{
//
//    private ControllerMock controller;
//
//    public ControllerTest()
//    {
//    }
//
//    @Before
//    public void before()
//    {
//        controller = new ControllerMock();
//    }
//
//    @Test
//    public void testRegisterUser()
//    {
//        String userName = "boyko";
//        String name = "Bobanka";
//        String password = "67890";
//        String email = "bobanka@bulgaria.bg";
//        User user = new User(userName, name, email, password, new UserType("Student"));
//        String expectedResult = AcceptanceProtocol.ACCOUNT_REGISTRATION_SUCCESS;
//        String actualResult = controller.registerUser(user);
//        assertThat("registerUser() should return confirmation string", actualResult,
//                   is(expectedResult));
//    }
//
//    @Test
//    public void testAuthenticateUser()
//    {
//        String expectedResult = AcceptanceProtocol.ACCOUNT_LOGIN_SUCCESS;
//        String userName = "bobkoo";
//        String password = "12345";
//        String actualResult = controller.authenticateUser(userName, password);
//        assertThat("AuthenticateUser() should return confirmation string", actualResult,
//                   is(expectedResult));
//    }
//
//    @Test
//    public void testDeleteParticularUser()
//    {
//        //Actual Deletion Test
//        String expected = AcceptanceProtocol.ACCOUNT_DELETION;
//        String actualResult = controller.deleteParticularUser();
//        assertThat("deleteParticularUser() should return a confirmation if acc is deleted", actualResult, is(expected));
//    }
//
//    @Test
//    public void testUpdateParticularUser()
//    {
//        //Register User
//        String userName = "boyko";
//        String name = "Bobanka";
//        String password = "67890";
//        String email = "bobanka@bulgaria.bg";
//        User user = new User(userName, name, email, password, new UserType("Student"));
//        controller.registerUser(user);
//        //Authenticate User
//        controller.authenticateUser(user.getUserName(), user.getPassword());
//        //Actual Update Test
//        String expectedResult = AcceptanceProtocol.ACCOUNT_UPDATE_SUCCESS;
//        String actualResult = controller.updateParticularUser(user.getPassword(),
//                                                              new User("bobkoo", "boyko", "user@email", "12345", new UserType("Student")));
//        assertThat("updateParticularUser() should return a confirmation String",
//                   actualResult, is(expectedResult));
//    }
//
//    @Test
//    public void testAddSubject()
//    {
//        assertEquals((Integer) 0, new ProposedSubject("AI", "makeasasd", Boolean.TRUE, "A").getId());
//
//    }
//
//    @Test
//    public void testGetAllAvailableSubjects()
//    {
//        ArrayList<ProposedSubject> testSubjects = new ArrayList();
//        testSubjects.add(new ProposedSubject("AI", "makeasasd", Boolean.TRUE, "A"));
//        testSubjects.add(new ProposedSubject("C#", "java like", Boolean.TRUE, "B"));
//        testSubjects.add(new ProposedSubject("C++", "complicated", Boolean.FALSE, "A"));
//        testSubjects.add(new ProposedSubject("Game Design", "WOW", Boolean.TRUE, "A"));
//        controller.addProposedElectiveSubject(testSubjects.get(0));
//        controller.addProposedElectiveSubject(testSubjects.get(1));
//        controller.addProposedElectiveSubject(testSubjects.get(2));
//        controller.addProposedElectiveSubject(testSubjects.get(3));
//        assertEquals(testSubjects.size(), controller.getAllAvailableProposedElectiveSubjects().size());
//    }
//
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
//
//    @Test
//    public void testAddVoteFromParticularUser()
//    {
//        String expected = AcceptanceProtocol.VOTE_REGISTRATION_SUCCESS;
//        ArrayList<Vote> testVotes = new ArrayList();
//        testVotes.add(new Vote(1, 2,
//                               new User("bobkoo", "boyko", "user@email", "12345", new UserType("Student")),
//                               new ProposedSubject("Game Design", "WOW", Boolean.TRUE, "A")));
//        testVotes.add(new Vote(1, 1,
//                               new User("bobkoo", "boyko", "user@email", "12345", new UserType("Student")),
//                               new ProposedSubject("C#", "java like", Boolean.TRUE, "A")));
//        testVotes.add(new Vote(1, 1,
//                               new User("bobkoo", "boyko", "user@email", "12345", new UserType("Student")),
//                               new ProposedSubject("C++", "complicated", Boolean.FALSE, "B")));
//        testVotes.add(new Vote(1, 2,
//                               new User("bobkoo", "boyko", "user@email", "12345", new UserType("Student")),
//                               new ProposedSubject("AI", "makeasasd", Boolean.TRUE, "B")));
//
//        assertEquals("addVoteFromParticularUser() should return a confirmation String",
//                     expected, controller.addVoteFromParticularUser(testVotes));
//    }
//
//    @Test
//    public void testGetAllVotesOfParticularUser()
//    {
//        ArrayList<Vote> expectedVotes = new ArrayList();
//        expectedVotes.add(new Vote(1, 2,
//                                   new User("bobkoo", "boyko", "user@email", "12345", new UserType("Student")),
//                                   new ProposedSubject("Game Design", "WOW", Boolean.TRUE, "A")));
//        expectedVotes.add(new Vote(1, 1,
//                                   new User("bobkoo", "boyko", "user@email", "12345", new UserType("Student")),
//                                   new ProposedSubject("C#", "java like", Boolean.TRUE, "A")));
//        expectedVotes.add(new Vote(1, 1,
//                                   new User("bobkoo", "boyko", "user@email", "12345", new UserType("Student")),
//                                   new ProposedSubject("C++", "complicated", Boolean.FALSE, "B")));
//        expectedVotes.add(new Vote(1, 2,
//                                   new User("bobkoo", "boyko", "user@email", "12345", new UserType("Student")),
//                                   new ProposedSubject("AI", "makeasasd", Boolean.TRUE, "B")));
//        controller.addVoteFromParticularUser(expectedVotes);
//        assertThat("getAllVotesOfParticularUser() should return a list full with all votes",
//                   controller.getAllVotesOfParticularUser(), is(expectedVotes));
//    }
//
//    @Test
//    public void updateUserVotes()
//    {
//        ArrayList<Vote> votes = new ArrayList();
//        votes.add(new Vote(1, 2,
//                           new User("bobkoo", "boyko", "user@email", "12345", new UserType("Student")),
//                           new ProposedSubject("Game Design", "WOW", Boolean.TRUE, "A")));
//        votes.add(new Vote(1, 1,
//                           new User("bobkoo", "boyko", "user@email", "12345", new UserType("Student")),
//                           new ProposedSubject("C#", "java like", Boolean.TRUE, "A")));
//        votes.add(new Vote(1, 1,
//                           new User("bobkoo", "boyko", "user@email", "12345", new UserType("Student")),
//                           new ProposedSubject("C++", "complicated", Boolean.FALSE, "B")));
//        votes.add(new Vote(1, 2,
//                           new User("bobkoo", "boyko", "user@email", "12345", new UserType("Student")),
//                           new ProposedSubject("AI", "makeasasd", Boolean.TRUE, "B")));
//        controller.addVoteFromParticularUser(votes);
//        ArrayList<Vote> existingVotes = controller.getAllVotesOfParticularUser();
//        Vote expectedVote = new Vote(1, 2,
//                                     new User("bobkoo", "boyko", "user@email", "12345", new UserType("Student")),
//                                     new ProposedSubject("AI", "makeasasd", Boolean.TRUE, "B"));
//        expectedVote.setId(existingVotes.get(0).getId());
//        Vote actualResult = controller.updateParticularVoteOfParticularUser(expectedVote);
//        assertThat("updateUserVotes() should update the votes of a user", actualResult,
//                   is(expectedVote));
//    }
//
//    @Test
//    public void testDeleteUserVotes()
//    {
//        String expectedResult = AcceptanceProtocol.VOTE_DELETION_SUCCESS;
//        String actualResult = controller.deleteAllVotesOfParticularUser();
//        assertThat("deleteUserVotes() should delete ALL votes of that user", actualResult,
//                   is(expectedResult));
//    }
//}
