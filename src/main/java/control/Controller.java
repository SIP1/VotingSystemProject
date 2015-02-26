package control;

public class Controller {
    
    public static void main(String[] args) {
        new Controller().authenticateUser("bobkoo", "12345");
    }
    
    public String authenticateUser(String userName, String password){
        return "Authentication successfull";
    }
    
}
