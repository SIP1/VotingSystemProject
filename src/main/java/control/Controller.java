package control;

import JPA2.ProposedSubject;
import JPA2.User;
import JPA2.UserType;
import JPA2.Vote;
import interfaces.ControllerInterface;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Controller implements ControllerInterface
{

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SIP_PU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tr;
    private User loggedInUser;
    private List<ProposedSubject> proposedSubjects = new ArrayList<>();

    public static void main(String[] args)
    {

    }

    private static Controller instance = null;

    public static Controller getInstance()
    {
        if (instance == null)
        {
            instance = new Controller();
        }
        return instance;
    }

    private void initializeTransactions()
    {
        tr = em.getTransaction();
    }

    @Override
    public String authenticateUser(String userName, String password)
    {
        // this is where we will initialize loggedInUser
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String registerUser(User u)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String deleteParticularUser()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String updateParticularUser(String password, User newUserInfo)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getUser()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String addUserType(String name)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String updateParticularUserType(Integer id)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String deleteParticularUserType(Integer id)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserType> getAllUserTypes()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ProposedSubject> getAllAvailableProposedElectiveSubjects()
    {
        return proposedSubjects;
    }

    @Override
    public ProposedSubject addProposedElectiveSubject(ProposedSubject pes)
    {
        proposedSubjects.add(pes);
        return pes;
    }

    @Override
    public String updateParticularElectiveSubject(Integer id)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String deleteParticularElectiveSubject(Integer id)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String addVoteFromParticularUser(String vote1, String vote2, String vote3, String vote4, int roundNumber)
    {
        List<Vote> votedSubjects = new ArrayList<>();
        for (ProposedSubject p : proposedSubjects) {
            if (p.getName().equals(vote1) || p.getName().equals(vote2)) {
                votedSubjects.add(new Vote(loggedInUser, p, roundNumber, 2));
            }
        }
        for (ProposedSubject p : proposedSubjects) {
            if (p.getName().equals(vote3) || p.getName().equals(vote4)) {
                votedSubjects.add(new Vote(loggedInUser, p, roundNumber, 1));
            }
        }
        loggedInUser.setVotes(votedSubjects);
        return "to be completed";
    }

    @Override
    public List<Vote> getAllVotesOfParticularUser()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String deleteAllVotesOfParticularUser()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vote updateParticularVoteOfParticularUser(Vote nv)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //getUserByUsername

}
