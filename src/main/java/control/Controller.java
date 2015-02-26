package control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import utilities.AcceptanceProtocol;

public class Controller {

    User user;
    public ArrayList<ProposedElectiveSubject> subjects = new ArrayList();
    public ArrayList<User> users = new ArrayList();

    public static void main(String[] args) {
        new Controller().testingBase();
    }

    public Controller() {
        testingBase();
    }

    private void testingBase() {
        users.add(new User(1, "bobkoo", "boyko", "12345", "user@email"));
        authenticate("bobkoo", "12345");

    }

    public String authenticate(String userName, String password) {
        for (User x : users) {
            if (x.getUserName().equals(userName) && x.getPassword().equals(password)) {
                user = x;
                return AcceptanceProtocol.LOGIN_SUCCESS;
            }
        }
        return AcceptanceProtocol.LOGIN_FAILED;
    }

    public String register(User u) {
        for(User x : users) {
            if(x.getUserName().equals(u.userName)){
                return AcceptanceProtocol.REGISTRATION_FAILED;
            }
        }
        users.add(u);
        return AcceptanceProtocol.REGISTRATION_SUCCESS;
    }

    public String deleteAccount(String userName){
        for(User x : users) {
            if(x.getUserName().equals(userName)){
                users.remove(x);
                return AcceptanceProtocol.ACCOUNT_DELETION;
            }
        }
        return AcceptanceProtocol.ACCOUNT_DELETION_FAIL;
    }
    
    public ArrayList getAllAvailableSubjects() {
        return subjects;
    }

    public ProposedElectiveSubject addSubject(ProposedElectiveSubject pes) {
        subjects.add(pes);
        return pes;
    }

    public String vote(ArrayList<Vote> votes) {
        String checkingResult = isChoiceAccepted(votes);
        if (checkingResult.equals(AcceptanceProtocol.VOTE_SUCCESS)) {
            user.addVotes(votes);
        }
        return checkingResult;
    }

    private String isChoiceAccepted(ArrayList<Vote> votes) {
        Set<Integer> values = new HashSet<Integer>(Arrays.asList(4, 2));
        int votesSize = votes.size();
        if (!values.contains(votesSize)) {
            return AcceptanceProtocol.ERROR_AMMOUNT;
        }
        List<Integer> pesIDs = new ArrayList();
        List<Integer> roundNos = new ArrayList();
        for (int i = 0; i < votesSize; i++) {
            pesIDs.add(votes.get(i).getProposedElectiveSubjectsID());
            roundNos.add(votes.get(i).getRoundNo());
        }
        Set<Integer> setPesIDs = new HashSet<Integer>(pesIDs);
        Set<Integer> setRoundNos = new HashSet<Integer>(roundNos);
        if (setPesIDs.size() < pesIDs.size()) {
            return AcceptanceProtocol.ERROR_REPETITION;
            /* There are duplicates */
        }
        if (1 != setRoundNos.size()) {
            return AcceptanceProtocol.ERROR_ROUNDS;
            /* There are votes for more than 1 round */
        }
        return AcceptanceProtocol.VOTE_SUCCESS;
    }

    public ArrayList<Vote> getUserVotes() {
        return user.userVotes;
    }

    public Vote updateUserVote(Vote nv) {
        for (Vote v : user.userVotes) {
            if (v.id == nv.id) {
                v = nv;
                break;
            }
        }
        return nv;
    }

    public String deleteUserVotes() {
        user.userVotes.clear();
        return AcceptanceProtocol.VOTE_DELETION;
    }

    //Nested class representing the ProposedSubject table (until it's created)
    private Integer pesIDCreator = 0;

    public class ProposedElectiveSubject {

        private Integer id;
        private String name, description, poolOptions;
        private Boolean isAlive;

        public ProposedElectiveSubject(String name, String description, Boolean isAlive) {
            this.id = pesIDCreator++;
            this.name = name;
            this.description = description;
            this.isAlive = isAlive;
            this.poolOptions = "";
        }

        public Integer getId() {
            return id;
        }

    }

    private Integer votesIDCreator = 0;

    class Vote {

        private Integer id, points, proposedElectiveSubjectsID, roundNo;
        private String userNameStudent;

        public Vote(String userNameStudent, Integer points, Integer proposedElectiveSubjectsID, Integer roundNo) {
            this.id = votesIDCreator++;
            this.points = points;
            this.proposedElectiveSubjectsID = proposedElectiveSubjectsID;
            this.roundNo = roundNo;
            this.userNameStudent = userNameStudent;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getId() {
            return id;
        }

        public Integer getRoundNo() {
            return roundNo;
        }

        public Integer getProposedElectiveSubjectsID() {
            return proposedElectiveSubjectsID;
        }
    }

    class User {

        private Integer useTypeID;
        private String userName, name, password, email;
        private ArrayList<Vote> userVotes = new ArrayList();

        public User(Integer useTypeID, String userName, String name, String password, String email) {
            this.useTypeID = useTypeID;
            this.userName = userName;
            this.name = name;
            this.password = password;
            this.email = email;
        }

        public String getUserName() {
            return userName;
        }

        public String getPassword() {
            return password;
        }

        public void addVote(Vote v) {
            userVotes.add(v);
        }

        public void addVotes(ArrayList<Vote> aLv) {
            userVotes.addAll(aLv);
        }
    }
}
