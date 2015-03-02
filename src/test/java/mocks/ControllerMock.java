package mocks;

import JPA2.ProposedSubject;
import JPA2.User;
import JPA2.UserType;
import JPA2.Vote;
import interfaces.ControllerInterface;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import utilities.AcceptanceProtocol;

public class ControllerMock implements ControllerInterface
{
    //getVote.clear won't work... neither for any other delete... TODO

    User user = new User();
    public ArrayList<ProposedSubject> subjects = new ArrayList();
    public ArrayList<User> users = new ArrayList();

    public static void main(String[] args)
    {
        new ControllerMock().testingBase();
    }

    public ControllerMock()
    {
        testingBase();
    }

    private void testingBase()
    {
        users.add(new User("bobkoo", "12345", "boyko", "email@email.mail", new UserType("Student")));
        users.add(new User("boyko", "67890a", "Bobanka", "bobanka@bulgaria.bg", new UserType("Student")));
        authenticateUser("bobkoo", "12345");
    }

    //Users
    @Override
    public String authenticateUser(String userName, String password)
    {
        for (User x : users)
        {
            if (x.getUsername().equals(userName) && x.getPassword().equals(password))
            {
                user = x;
                return AcceptanceProtocol.ACCOUNT_LOGIN_SUCCESS;
            }
        }
        return AcceptanceProtocol.ACCOUNT_LOGIN_ERROR;
    }

    @Override
    public String registerUser(User u)
    {
        for (User x : users)
        {
            if (x.getUsername().equals(u.getUsername()))
            {
                return AcceptanceProtocol.ACCOUNT_REGISTRATION_ERROR;
            }
        }
        users.add(u);
        return AcceptanceProtocol.ACCOUNT_REGISTRATION_SUCCESS;
    }

    @Override
    public String deleteParticularUser()
    {
        for (User x : users)
        {
            if (x.getUsername().equals(user.getUsername()))
            {
                users.remove(x);
                return AcceptanceProtocol.ACCOUNT_DELETION;
            }
        }
        return AcceptanceProtocol.ACCOUNT_DELETION_ERROR;
    }

    @Override
    public String updateParticularUser(String password, User newUserInfo)
    {
        if (user.getPassword().equals(password))
        {
            for (User x : users)
            {
                if (x.getUsername().equals(user.getUsername()))
                {
                    user.setPassword(newUserInfo.getPassword());
                    user.setEmail(newUserInfo.getEmail());
                    user.setUserType(newUserInfo.getUserType());
                    x = user;
                    return AcceptanceProtocol.ACCOUNT_UPDATE_SUCCESS;
                }
            }
        }
        return AcceptanceProtocol.ACCOUNT_UPDATE_ERROR;
    }

    @Override
    public User getUser()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //UserTypes
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

    //Subjects
    @Override
    public ArrayList<ProposedSubject> getAllAvailableProposedElectiveSubjects()
    {
        return subjects;
    }

    @Override
    public ProposedSubject addProposedElectiveSubject(ProposedSubject pes)
    {
        subjects.add(pes);
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

    //Votes
    @Override
    public String addVoteFromParticularUser(ArrayList<Vote> votes)
    {
        String checkingResult = isChoiceAccepted(votes);
        if (checkingResult.equals(AcceptanceProtocol.VOTE_REGISTRATION_SUCCESS))
        {
            user.setVotes(votes);

        }
        return checkingResult;
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
        return user.getVotes();
    }

    @Override
    public Vote updateParticularVoteOfParticularUser(Vote nv)
    {
        for (Vote v : user.getVotes())
        {
            if (Objects.equals(v.getId(), nv.getId()))
            {
                v = nv;
                break;
            }
        }
        return nv;
    }

    @Override
    public String deleteAllVotesOfParticularUser()
    {
        if (user.getVotes().size() > 0)
        {
            user.getVotes().clear();
            return AcceptanceProtocol.VOTE_DELETION_SUCCESS;
        }
        return AcceptanceProtocol.VOTE_DELETION_FAIL;
    }

/*
    Those nested classes were used while the database was not created
    we decided to comment them out and leave them as part of our project.
    */
//******************************************************************************    
//    private Integer pesIDCreator = 0;
//    public class ProposedElectiveSubject
//    {
//
//        private Integer id;
//        private String name, description, poolOptions;
//        private Boolean isAlive;
//
//        public ProposedElectiveSubject(String name, String description, Boolean isAlive)
//        {
//            this.id = pesIDCreator++;
//            this.name = name;
//            this.description = description;
//            this.isAlive = isAlive;
//            this.poolOptions = "";
//        }
//
//        public Integer getId()
//        {
//            return id;
//        }
//
//    }
//
//******************************************************************************
//    private Integer votesIDCreator = 0;
//    public class Vote
//    {
//
//        private Integer id, points, proposedElectiveSubjectsID, roundNo;
//        private String userNameStudent;
//
//        public Vote(String userNameStudent, Integer points, Integer proposedElectiveSubjectsID, Integer roundNo)
//        {
//            this.id = votesIDCreator++;
//            this.points = points;
//            this.proposedElectiveSubjectsID = proposedElectiveSubjectsID;
//            this.roundNo = roundNo;
//            this.userNameStudent = userNameStudent;
//        }
//
//        public void setId(Integer id)
//        {
//            this.id = id;
//        }
//
//        public Integer getId()
//        {
//            return id;
//        }
//
//        public Integer getRoundNo()
//        {
//            return roundNo;
//        }
//
//        public Integer getProposedElectiveSubjectsID()
//        {
//            return proposedElectiveSubjectsID;
//        }
//    }
//******************************************************************************
//    public class User
//    {
//
//        private Integer useTypeID;
//        private String userName, name, password, email;
//        private ArrayList<Vote> userVotes = new ArrayList();
//
//        public User(Integer useTypeID, String userName, String name, String password, String email)
//        {
//            this.useTypeID = useTypeID;
//            this.userName = userName;
//            this.name = name;
//            this.password = password;
//            this.email = email;
//        }
//
//        public String getUserName()
//        {
//            return userName;
//        }
//
//        public String getPassword()
//        {
//            return password;
//        }
//
//        public void addVote(Vote v)
//        {
//            userVotes.add(v);
//        }
//
//        public void addVotes(ArrayList<Vote> aLv)
//        {
//            userVotes.addAll(aLv);
//        }
//
//        public void setEmail(String email)
//        {
//            this.email = email;
//        }
//
//        public void setName(String name)
//        {
//            this.name = name;
//        }
//
//        public void setPassword(String password)
//        {
//            this.password = password;
//        }
//
//        public void setUseTypeID(Integer useTypeID)
//        {
//            this.useTypeID = useTypeID;
//        }
//
//        public void setUserName(String userName)
//        {
//            this.userName = userName;
//        }
//
//        public void setUserVotes(ArrayList<Vote> userVotes)
//        {
//            this.userVotes = userVotes;
//        }
//
//        public String getEmail()
//        {
//            return email;
//        }
//
//        public String getName()
//        {
//            return name;
//        }
//
//        public Integer getUseTypeID()
//        {
//            return useTypeID;
//        }
//
//        public ArrayList<Vote> getUserVotes()
//        {
//            return userVotes;
//        }
//    }
//******************************************************************************
}
