/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import JPA2.ProposedSubject;
import JPA2.User;
import JPA2.UserType;
import JPA2.Vote;
import java.util.ArrayList;
import java.util.List;
import mocks.ControllerMock;
import static org.hamcrest.CoreMatchers.is;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
     * Test of registerUser method, of class Controller.
     */
    @Test
    public void testRegisterUser() {
        System.out.println("registerUser");
        String userName = "Hahaha";
        String name = "NoName";
        String password = "qwerty";
        String email = "bobanka@email.com";
        User user = new User(userName, name, email, password, controller.userTypes.get(0));
        String expectedResult = AcceptanceProtocol.ACCOUNT_REGISTRATION_SUCCESS;
        String actualResult = controller.registerUser(user);
        assertEquals(expectedResult, actualResult);
    }

    /**
     * Test of deleteParticularUser method, of class Controller.
     */
    @Test
    public void testDeleteParticularUser() {
        System.out.println("deleteParticularUser");
        String expected = AcceptanceProtocol.ACCOUNT_DELETION;
        String actualResult = controller.deleteParticularUser();
        assertEquals(expected, actualResult);
    }

    /**
     * Test of updateParticularUser method, of class Controller.
     */
    @Test
    public void testUpdateParticularUser() {
        System.out.println("updateParticularUser");
        String userName = "TestUser";
        String password = "12345";
        String newPassword = "54321";
        String name = "Changed";
        String email = "changed@email.com";
        String expectedResult = AcceptanceProtocol.ACCOUNT_UPDATE_SUCCESS;
        String actualResult = controller.updateParticularUser(password, new User(userName, newPassword, name, email, controller.userTypes.get(0)));
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
     * Test of addUserType method, of class Controller.
     */
    @Test
    public void testAddUserType() {
        System.out.println("addUserType");
        String name = "New Usertype";
        String expResult = AcceptanceProtocol.USERTYPE_ADD_SUCCESS;
        String result = controller.addUserType(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateParticularUserType method, of class Controller.
     */
    @Test()
    public void testUpdateParticularUserType() {
        System.out.println("updateParticularUserType FIX ME!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

//        Integer id = null;
//        Controller instance = new Controller();
//        String expResult = "";
//        String result = instance.updateParticularUserType(id);
//        assertEquals(expResult, result);
        fail("Interface needs to be changed");
    }

    /**
     * Test of deleteParticularUserType method, of class Controller.
     */
    @Test
    public void testDeleteParticularUserType() {
        System.out.println("deleteParticularUserType");
        String expResult = AcceptanceProtocol.USERTYPE_DELETION_SUCCESS;
        String result = controller.deleteParticularUserType(2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllUserTypes method, of class Controller.
     */
    @Test
    public void testGetAllUserTypes() {
        System.out.println("getAllUserTypes");
        List<UserType> result = controller.getAllUserTypes();
        int expectedResult = controller.userTypes.size();
        assertEquals(expectedResult, result.size());
    }

    /**
     * Test of getAllAvailableProposedElectiveSubjects method, of class
     * Controller.
     */
    @Test
    public void testGetAllAvailableProposedElectiveSubjects() {
        System.out.println("getAllAvailableProposedElectiveSubjects");
        ArrayList<ProposedSubject> actualResult = controller.getAllAvailableProposedElectiveSubjects();
        ArrayList<ProposedSubject> expectedResult = controller.proposedSubjects;
        assertEquals(expectedResult, actualResult);
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
     * Test of updateParticularElectiveSubject method, of class Controller.
     */
    @Test
    public void testUpdateParticularElectiveSubject() {
        System.out.println("updateParticularElectiveSubject FIX ME!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//      
//        String expResult = "";
//        String result = controller.updateParticularElectiveSubject(1);
//        assertEquals(expResult, result);
        fail("Interface needs to be changed");
    }

    /**
     * Test of deleteParticularElectiveSubject method, of class Controller.
     */
    @Test
    public void testDeleteParticularElectiveSubject() {
        System.out.println("deleteParticularElectiveSubject");
        String expected = AcceptanceProtocol.SUBJECT_DELETION_SUCCESS;
        assertEquals(expected, controller.deleteParticularElectiveSubject(1));
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
        int roundNumber = 1;
        String expected = AcceptanceProtocol.VOTE_REGISTRATION_SUCCESS;
        assertEquals(expected, controller.addVoteFromParticularUser(vote1, vote2, vote3, vote4, roundNumber));
    }

    /**
     * Test of getAllVotesOfParticularUser method, of class Controller.
     */
    @Test
    public void testGetAllVotesOfParticularUser() {
        System.out.println("getAllVotesOfParticularUser");

        List<Vote> votes = controller.getAllVotesOfParticularUser();
        int expResult = controller.user.getVotes().size();
        assertEquals(expResult, votes.size());
    }

    /**
     * Test of deleteAllVotesOfParticularUser method, of class Controller.
     */
    @Test
    public void testDeleteAllVotesOfParticularUser() {
        System.out.println("deleteAllVotesOfParticularUser");
        //First we have to add votes
        //   ArrayList<Vote> testVotes = votesGenerator();
//        controller.addVoteFromParticularUser(testVotes);
        //Then we try to delete them
        String expectedResult = AcceptanceProtocol.VOTE_DELETION_SUCCESS;
        String actualResult = controller.deleteAllVotesOfParticularUser();
        assertThat(actualResult, is(expectedResult));
    }

    /**
     * Test of updateParticularVoteOfParticularUser method, of class Controller.
     */
    @Test
    public void testUpdateParticularVoteOfParticularUser() {
        System.out.println("updateParticularVoteOfParticularUser");
        
        
        Vote expResult = controller.user.getVotes().get(0);
        expResult.setPoints(1);
        expResult.setProposedSubject(controller.proposedSubjects.get(3));
        expResult.setRoundNumber(3);
        
        Vote actualResult = controller.updateParticularVoteOfParticularUser(expResult);
        assertEquals(expResult,actualResult);
    }
}
