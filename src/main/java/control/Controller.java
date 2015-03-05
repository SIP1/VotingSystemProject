package control;

import JPA2.ProposedSubject;
import JPA2.User;
import JPA2.UserType;
import JPA2.Vote;
import interfaces.ControllerInterface;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import utilities.AcceptanceProtocol;

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
        loggedInUser = null;
//        should be loggedInUser = em.find(Class<T> entityClass, Object primaryKey, Map<String,Object> properties),
//        where we can add the received password as an extra property, so it will return the object only if it matches.
        if (userName.equals("1") && password.equals("1"))
        {
            loggedInUser = new User("1", "1", "Peter Lorensen", "pelo", new UserType("teacher"));
        }
        if (loggedInUser != null)
        {
            return AcceptanceProtocol.ACCOUNT_LOGIN_SUCCESS;
        }
        return AcceptanceProtocol.ACCOUNT_LOGIN_ERROR;
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
    public User updateParticularUser(String password, User newUserInfo)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getUser()
    {
        return loggedInUser;
    }

    @Override
    public String addUserType(String name)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserType updateParticularUserType(Integer id, UserType newUserType)
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
        proposedSubjects = new ArrayList<ProposedSubject>();
        proposedSubjects.add(new ProposedSubject("Android", "none", Boolean.TRUE, "none"));
        proposedSubjects.get(proposedSubjects.size() - 1).setId(101);
        proposedSubjects.add(new ProposedSubject("C#", "none", Boolean.TRUE, "none"));
        proposedSubjects.get(proposedSubjects.size() - 1).setId(102);
        proposedSubjects.add(new ProposedSubject("Arduino", "none", Boolean.TRUE, "none"));
        proposedSubjects.get(proposedSubjects.size() - 1).setId(103);
        proposedSubjects.add(new ProposedSubject("AI", "none", Boolean.TRUE, "none"));
        proposedSubjects.get(proposedSubjects.size() - 1).setId(104);
        proposedSubjects.add(new ProposedSubject("Game Design", "none", Boolean.TRUE, "none"));
        proposedSubjects.get(proposedSubjects.size() - 1).setId(105);

        return proposedSubjects;
    }

    @Override
    public ProposedSubject addProposedElectiveSubject(ProposedSubject pes)
    {
        proposedSubjects.add(pes);
        return pes;
    }

    @Override
    public ProposedSubject updateParticularElectiveSubject(Integer id, ProposedSubject newProposedSubject)
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
        ArrayList<Vote> votedSubjects = new ArrayList<>();
        for (ProposedSubject p : proposedSubjects)
        {
            if (p.getName().equals(vote1) || p.getName().equals(vote2))
            {
                votedSubjects.add(new Vote(loggedInUser, p, roundNumber, 2));
            }
        }
        for (ProposedSubject p : proposedSubjects)
        {
            if (p.getName().equals(vote3) || p.getName().equals(vote4))
            {
                votedSubjects.add(new Vote(loggedInUser, p, roundNumber, 1));
            }
        }
        String checkup = isChoiceAccepted(votedSubjects);
        if (checkup.equals(AcceptanceProtocol.VOTE_REGISTRATION_SUCCESS))
        {
            loggedInUser.setVotes(votedSubjects);
        }
        return checkup;
    }

    private String isChoiceAccepted(ArrayList<Vote> votes)
    {
        int votesSize = votes.size();
        if (votesSize != 4)
        {
            return AcceptanceProtocol.VOTE_REGISTRATION_ERROR_AMMOUNT;
        }
        List<Integer> pesIDs = new ArrayList();
        List<Integer> roundNos = new ArrayList();
        for (int i = 0; i < votesSize; i++)
        {
            pesIDs.add(votes.get(i).getProposedSubject().getId());
            roundNos.add(votes.get(i).getRoundNumber());
        }
        Set<Integer> setPesIDs = new HashSet<Integer>(pesIDs);
        Set<Integer> setRoundNos = new HashSet<Integer>(roundNos);
        if (setPesIDs.size() < pesIDs.size())
        {
            return AcceptanceProtocol.VOTE_REGISTRATION_ERROR_REPETITION;
        }
        if (1 != setRoundNos.size())
        {
            return AcceptanceProtocol.VOTE_REGISTRATION_ERROR_ROUNDS;

        }
        return AcceptanceProtocol.VOTE_REGISTRATION_SUCCESS;
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
