package control;

import control.Controller.ProposedElectiveSubjects;
import java.util.ArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ControllerTest {

    private Controller controller;

    public ControllerTest() {
    }

    @Before
    public void before() {
        controller = new Controller();
        System.out.println("before each");
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
    public void testGetAllAvailableSubjects() {
        ArrayList subjects = new ArrayList();
        subjects.add(controller.new ProposedElectiveSubjects("AI", "make it think", Boolean.TRUE));
        subjects.add(controller.new ProposedElectiveSubjects("C#", "java like", Boolean.TRUE));
        subjects.add(controller.new ProposedElectiveSubjects("C++", "complicated", Boolean.FALSE));
        subjects.add(controller.new ProposedElectiveSubjects("Game Design", "WOW", Boolean.TRUE));
        assertEquals(subjects.size(), controller.getAllAvailableSubjects().size());
    }

    @Test
    public void testAddSubjectToDB() {
        assertEquals((Integer) 4, controller.addSubject(controller
        .new ProposedElectiveSubjects("AI", "make it think", Boolean.TRUE)).getId());
    }
    
    @Test
    public void testVote(){
        ArrayList<ProposedElectiveSubjects> alPES = new ArrayList();
        alPES.add(controller.new ProposedElectiveSubjects("AI", "make it think", Boolean.TRUE));
        alPES.add(controller.new ProposedElectiveSubjects("C#", "java like", Boolean.TRUE));
        alPES.add(controller.new ProposedElectiveSubjects("C++", "complicated", Boolean.FALSE));
        alPES.add(controller.new ProposedElectiveSubjects("Game Design", "WOW", Boolean.TRUE));
    }

}
