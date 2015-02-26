/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import control.Controller.ProposedElectiveSubjects;
import java.util.ArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author root
 */
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
        String actualResult = controller.authenticateUser(userName, password);
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
    
}
