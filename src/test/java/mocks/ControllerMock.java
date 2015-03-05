package mocks;

import JPA2.ProposedSubject;
import JPA2.User;
import JPA2.UserType;
import JPA2.Vote;
import interfaces.ControllerInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import utilities.AcceptanceProtocol;

public class ControllerMock implements ControllerInterface {
    //getVote.clear won't work... neither for any other delete... TODO

    public User user = new User();
    public ArrayList<ProposedSubject> proposedSubjects = new ArrayList();
    public ArrayList<User> users = new ArrayList();
    public ArrayList<UserType> userTypes = new ArrayList<>();
    public ArrayList<Vote> votes = new ArrayList<>();

    public static void main(String[] args) {
        ControllerMock controllerMock = new ControllerMock();
    }
//
//    public ControllerMock() {
//        testingBase();
//    }

    public ControllerMock() {
        //create user types
        userTypes.add(new UserType("Student"));
        userTypes.add(new UserType("Teacher"));
        userTypes.add(new UserType("Administrator"));

        //create users
        users.add(new User("TestUser", "12345", "boyko", "email@email.mail", userTypes.get(0)));
        users.add(new User("TestUser2", "test", "Testing user 2", "test@test.com", userTypes.get(1)));
        users.add(new User("TestUser3", "test", "Admin", "admin@test.com", userTypes.get(2)));

        //create proposed subjects
        proposedSubjects.add(new ProposedSubject("Test subject 1", "It was only just a test", true, null));
        proposedSubjects.add(new ProposedSubject("Test subject 2", "It was only just a test", true, null));
        proposedSubjects.add(new ProposedSubject("Test subject 3", "It was only just a test", true, null));
        proposedSubjects.add(new ProposedSubject("Test subject 4", "It was only just a test", true, null));

        //assing the current user which will be the student
        user = users.get(0);

        //create votes
        votes.add(new Vote(user, proposedSubjects.get(0), 1, 2));
        votes.add(new Vote(user, proposedSubjects.get(1), 1, 2));
        votes.add(new Vote(user, proposedSubjects.get(2), 1, 1));
        votes.add(new Vote(user, proposedSubjects.get(3), 1, 1));

        //set votes to user
        user.setVotes(votes);
        // authenticateUser("bobkoo", "12345");
    }

    //Users
    @Override
    public String authenticateUser(String userName, String password) {
        for (User x : users) {
            if (x.getUsername().equals(userName) && x.getPassword().equals(password)) {
                user = x;
                return AcceptanceProtocol.ACCOUNT_LOGIN_SUCCESS;
            }
        }
        return AcceptanceProtocol.ACCOUNT_LOGIN_ERROR;
    }

    @Override
    public String registerUser(User u) {
        for (User x : users) {
            if (x.getUsername().equals(u.getUsername())) {
                return AcceptanceProtocol.ACCOUNT_REGISTRATION_ERROR;
            }
        }
        users.add(u);
        return AcceptanceProtocol.ACCOUNT_REGISTRATION_SUCCESS;
    }

    @Override
    public String deleteParticularUser() {
        for (User x : users) {
            if (x.getUsername().equals(user.getUsername())) {
                users.remove(x);
                return AcceptanceProtocol.ACCOUNT_DELETION;
            }
        }
        return AcceptanceProtocol.ACCOUNT_DELETION_ERROR;
    }

    @Override
    public User updateParticularUser(String password, User newUserInfo) {
        if (user.getPassword().equals(password)) {
            for (User oldUser : users) {
                if (oldUser.getUsername().equals(newUserInfo.getUsername())) {
                    oldUser = newUserInfo;
                    return oldUser;
                }
            }
        }
        return null;
    }

    @Override
    public User getUser() {
        return user;
    }

    //UserTypes
    @Override
    public String addUserType(String name) {
        userTypes.add(new UserType(name));
        return AcceptanceProtocol.USERTYPE_ADD_SUCCESS;

    }

    @Override
    public UserType updateParticularUserType(Integer id, UserType newUserType) {
        UserType oldUserType = userTypes.get(id);
        oldUserType = newUserType;
        return oldUserType;
    }

    @Override
    public String deleteParticularUserType(Integer id) {
        userTypes.remove(id);
        return AcceptanceProtocol.USERTYPE_DELETION_SUCCESS;
    }

    @Override
    public List<UserType> getAllUserTypes() {
        return userTypes;
    }

    //Subjects
    @Override
    public ArrayList<ProposedSubject> getAllAvailableProposedElectiveSubjects() {
        return proposedSubjects;
    }

    @Override
    public ProposedSubject addProposedElectiveSubject(ProposedSubject pes) {
        proposedSubjects.add(pes);
        return pes;
    }

    @Override
    public ProposedSubject updateParticularElectiveSubject(Integer id, ProposedSubject newProposedSubject) {
        ProposedSubject oldProposedSubject = proposedSubjects.get(id);
        oldProposedSubject = newProposedSubject;
        return oldProposedSubject;
    }

    @Override
    public String deleteParticularElectiveSubject(Integer id) {
        proposedSubjects.remove(id);
        return AcceptanceProtocol.SUBJECT_DELETION_SUCCESS;
    }

    //Votes
    @Override
    public String addVoteFromParticularUser(String vote1, String vote2, String vote3, String vote4, int roundNumber) {

        votes.add(new Vote(user, new ProposedSubject(vote1, "TEST", true, ""), roundNumber, 2));
        votes.add(new Vote(user, new ProposedSubject(vote2, "TEST", true, ""), roundNumber, 2));
        votes.add(new Vote(user, new ProposedSubject(vote3, "TEST", true, ""), roundNumber, 1));
        votes.add(new Vote(user, new ProposedSubject(vote4, "TEST", true, ""), roundNumber, 1));

        return AcceptanceProtocol.VOTE_REGISTRATION_SUCCESS;
    }

    @Override
    public List<Vote> getAllVotesOfParticularUser() {
        return user.getVotes();
    }

    @Override
    public Vote updateParticularVoteOfParticularUser(Vote nv) {
        for (Vote v : user.getVotes()) {
            if (Objects.equals(v.getId(), nv.getId())) {
                v = nv;
                break;
            }
        }
        return nv;
    }

    @Override
    public String deleteAllVotesOfParticularUser() {
        if (user.getVotes().size() > 0) {
            user.getVotes().clear();
            return AcceptanceProtocol.VOTE_DELETION_SUCCESS;
        }
        if (user.getVotes().isEmpty()) {
            return AcceptanceProtocol.VOTE_DELETION_NOTHING;
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
