/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

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
    public void before(){
        controller = new Controller();
    }
    
    @Test
    public void testAuthenticateUser() {
        String expected = "Authentication successfull";
        String userName = "Bobkoo";
        String password = " 12345";
        String result = controller.authenticateUser(userName, password);
         assertThat(result, is(expected));
        //fail("The test case is a prototype.");
    }
    
}
