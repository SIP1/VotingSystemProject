package control;

import control.Controller.ProposedElectiveSubject;
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
    public void testAuthenticateUser() {
        String expectedResult = "Authentication successfull";
        String userName = "Bobkoo";
        String password = " 12345";
        String actualResult = controller.authenticate(userName, password);
        assertThat(expectedResult, is(actualResult));
    }

    @Test
    public void testAddSubject() {
        assertEquals((Integer) 0, controller.addSubject(controller.new ProposedElectiveSubject("AI", "make it think", Boolean.TRUE)).getId());
    }

    @Test
    public void testGetAllAvailableSubjects() {
        ArrayList<ProposedElectiveSubject> testSubjects = new ArrayList();
        testSubjects.add(controller.new ProposedElectiveSubject("AI", "make it think", Boolean.TRUE));
        testSubjects.add(controller.new ProposedElectiveSubject("C#", "java like", Boolean.TRUE));
        testSubjects.add(controller.new ProposedElectiveSubject("C++", "complicated", Boolean.FALSE));
        testSubjects.add(controller.new ProposedElectiveSubject("Game Design", "WOW", Boolean.TRUE));
        controller.addSubject(testSubjects.get(0));
        controller.addSubject(testSubjects.get(1));
        controller.addSubject(testSubjects.get(2));
        controller.addSubject(testSubjects.get(3));

        assertEquals(testSubjects.size(), controller.getAllAvailableSubjects().size());
    }

    @Test
    public void testVote() {
        String expected = AcceptanceProtocol.VOTE_SUCCESS;
        ArrayList<Vote> testVotes = new ArrayList();
        testVotes.add(controller.new Vote("bobkoo", 1, 4, 1));
        testVotes.add(controller.new Vote("bobkoo", 2, 3, 1));
        testVotes.add(controller.new Vote("bobkoo", 1, 2, 1));
        testVotes.add(controller.new Vote("bobkoo", 2, 5, 1));
        assertEquals(expected, controller.vote(testVotes));
    }

}
