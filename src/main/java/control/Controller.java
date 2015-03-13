package control;

import JPA2.ProposedSubject;
import JPA2.User;
import JPA2.UserType;
import JPA2.Vote;
import interfaces.ControllerInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    
   // private ArrayList<User> teachers;
   // private ArrayList<User> students;
    private DbMock db;
    private List<User> users;

    public static void main(String[] args)
    {
        new Controller().base();
    }

    private void base()
    {
        List<User> topFiveUnsatissfiedStudents = getAllStudentsByUnsatisfactionRate();
        for (int i = 0; i < topFiveUnsatissfiedStudents.size(); i++)
        {
            System.out.println("i:" + i + ">>" + topFiveUnsatissfiedStudents.get(i).getName());
            for (int j = 0; j < topFiveUnsatissfiedStudents.get(i).getVotesByRound(1).size(); j++)
            {
                {
                    System.out.println("i" + i + " j" + j + " >>" + topFiveUnsatissfiedStudents.get(i).getVotesByRound(1).get(j).getPoints());
                }
            }
        }
    }

    public Controller()
    {
 //       teachers = new ArrayList<>();
//        students = new ArrayList<>();
        users = new ArrayList<>();
        db = DbMock.getInstance();
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
        users = getAllUsers();
        for (User x : users)
        {
            if (x.getUsername().equals(userName) && x.getPassword().equals(password))
            {
                loggedInUser = x;
                return AcceptanceProtocol.ACCOUNT_LOGIN_SUCCESS;
            }
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
    public List<ProposedSubject> getAllAliveProposedElectiveSubjects()
    {
        return db.getAliveProposedSubjects();
    }

    @Override
    public List<ProposedSubject> getAllProposedElectiveSubjects()
    {
        return db.getAllProposedSubjects();
    }

    @Override
    public ProposedSubject addProposedElectiveSubject(ProposedSubject pes)
    {
        db.addProposedSubject(pes);
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
        List<ProposedSubject> availableSubjects = getAllAliveProposedElectiveSubjects();
        for (ProposedSubject p : availableSubjects)
        {
            if (p.getName().equals(vote1) || p.getName().equals(vote2))
            {
                votedSubjects.add(new Vote(loggedInUser, p, roundNumber, 2));
            }
        }
        for (ProposedSubject p : availableSubjects)
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
    @Override
    public List<User> getUsersByUserType(UserType ut)
    {
        return db.getUsersByUserTpe(ut);
    }

    @Override
    public List<User> getAllUsers()
    {
        return db.getUsers();
    }

    @Override
    public void setSatisfactionForStudent(int[] a, int[] b, User student)
    {
        List<ProposedSubject> proposedSubjects = new ArrayList();
        proposedSubjects = db.getAliveProposedSubjects();
        List<Vote> currentVotes = student.getVotesByRound(1);
        List<ProposedSubject> pollA = new ArrayList<>();
        List<ProposedSubject> pollB = new ArrayList<>();
        for (int i = 0; i < a.length; i++)
        {
            pollA.add(proposedSubjects.get(a[i]));
        }
        for (int i = 0; i < b.length; i++)
        {
            pollB.add(proposedSubjects.get(b[i]));
        }
        int satisfactionA = 0;
        int satisfactionB = 0;
        boolean found;
        for (Vote vote : currentVotes)
        {
            found = false;
            for (ProposedSubject ps : pollA)
            {
                if (vote.getProposedSubject().equals(ps))
                {
                    found = true;
                    // vote is a first priority
                    if (vote.getPoints() == 2)
                    {
                        satisfactionA = 50;
                        break;
                    }
                    //vote is a second priority
                    else
                    {
                        if (satisfactionA < 50)
                        {
                            satisfactionA = 25;
                        }

                    }
                }
            }
            if (!found)
            {
                for (ProposedSubject ps : pollB)
                {
                    if (vote.getProposedSubject().equals(ps))
                    {
                        if (vote.getPoints() == 2)
                        {
                            satisfactionB = 50;
                            break;
                        }
                        //vote is a second priority
                        else
                        {
                            if (satisfactionB < 50)
                            {
                                satisfactionB = 25;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(satisfactionA + satisfactionB);
        student.setSatisfaction(satisfactionA + satisfactionB);

    }

    @Override
    public int getOverallSatisfaction(int[] a, int[] b)
    {
        users = db.getUsers();
        int totalSatisfaction = 0;
        List<User> allStudents = getUsersByUserType(db.getUserTypeByName("Student"));
        List<User> allUsersWhoVotedInParticularRound = getAllStudentsWhoHadVotedForParticularRound(allStudents);
        for (User student : allUsersWhoVotedInParticularRound)
        {
            setSatisfactionForStudent(a, b, student);
            totalSatisfaction += student.getSatisfaction();
        }
        return totalSatisfaction / allStudents.size();
    }

    private List<User> getAllStudentsWhoHadVotedForParticularRound(List<User> allStudents)
    {
        List<User> allStudentsWhoHadVotedForParticularRound = new ArrayList();
        for (User x : allStudents)
        {
            if (!x.getVotesByRound(1).isEmpty() && x.getVotesByRound(1).size() == 4)
            {
                allStudentsWhoHadVotedForParticularRound.add(x);
            }
        }
        return allStudentsWhoHadVotedForParticularRound;
    }

    @Override
    public List<User> getAllStudentsByUnsatisfactionRate()
    {
        users = db.getUsers();
        List<User> unsatisfied = new ArrayList();

        for (User x : users)
        {
            if ("Student".equals(x.getUserType().getName()))
            {
                unsatisfied.add(x);
            }
        }

        Collections.sort(unsatisfied, new Comparator<User>()
                 {

                     public int compare(User o1, User o2)
                     {
                         return (Integer) o1.getSatisfaction() - (Integer) o2.getSatisfaction();
                     }
        });

        for (User x : unsatisfied)
        {
            List<Vote> votesToBeOrganized = new ArrayList();
            votesToBeOrganized.addAll(x.getVotesByRound(1));
            Collections.sort(votesToBeOrganized, new Comparator<Vote>()
                     {

                         public int compare(Vote o1, Vote o2)
                         {
                             return (Integer) o2.getPoints() - (Integer) o1.getPoints();
                         }
            });
            x.setVotes(votesToBeOrganized);
        }

//        if(unsatisfied.size()<5){
//            return unsatisfied;
//        }
//        List<User> topFiveUnsatisfied = new ArrayList();
//        for (int i = 0; i < 5; i++)
//        {
//            topFiveUnsatisfied.add(unsatisfied.get(i));
//        }
        return unsatisfied;
    }

    @Override
    public List<User> getAllTeachers()
    {
        return db.getUsersByUserTpe(db.getUserTypeByName("Teacher"));
    }

    @Override
    public String addProposedSubject(ProposedSubject ps, int[] selectedIndices)
    {
        List<User> allTeachers = getAllTeachers();
        List<User> toAdd = new ArrayList<>();
        for (int i = 0; i < selectedIndices.length; i++)
        {
            toAdd.add(allTeachers.get(selectedIndices[i]));
        }
        ps.setUsers(toAdd);
        db.addProposedSubject(ps);
        return AcceptanceProtocol.NEW_PROPOSED_SUBJECT_SUCCESS;
    }

    @Override
    public String addSubjectsToPolls(int[] a, int[] b)
    {
        List<ProposedSubject> pollA = new ArrayList<>();
        List<ProposedSubject> pollB = new ArrayList<>();
        List<ProposedSubject> proposedSubjects = db.getAliveProposedSubjects();
        for (int i = 0; i < a.length; i++)
        {
            pollA.add(proposedSubjects.get(a[i]));
        }
        for (int i = 0; i < b.length; i++)
        {
            pollB.add(proposedSubjects.get(b[i]));
        }
        //db.fillPolls(pollA, pollB);
        return AcceptanceProtocol.SUBJECTS_ADDED_TO_POLLS_SUCCESS + "\n" + db.fillPolls(pollA, pollB);
    }

    @Override
    public String selectSubjectsForRound1(int[] selectedIndexes)
    {
        List<ProposedSubject> allSubjects = getAllProposedElectiveSubjects();
        for (int i = 0; i < selectedIndexes.length; i++)
        {
            allSubjects.get(selectedIndexes[i]).setIsAlive(true);
        }
        return AcceptanceProtocol.SUBJECTS_ADDED_TO_ROUND_1_SUCCESS;
    }

}
