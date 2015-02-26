package control;

import java.util.ArrayList;
import utilities.AcceptanceProtocol;

public class Controller {

//    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JA6QQDBPU");
//    EntityManager em = emf.createEntityManager();
    User user;
    public ArrayList subjects = new ArrayList();

    public static void main(String[] args) {
        new Controller().testingBase();
    }

    public Controller() {
        testingBase();
    }

    private void testingBase() {
        authenticate("bobkoo", "12345");
        user = new User(1, "bobkoo", "boyko", "12345", "user@email");
    }

    public String authenticate(String userName, String password) {
        return AcceptanceProtocol.LOGIN_SUCCESS;
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
        if (votes.size() != 4) {
            return AcceptanceProtocol.ERROR_AMMOUNT;
        }
        ArrayList<Number> pesIDs = new ArrayList();
        for (int i = 0; i < votes.size(); i++) {
            if (!pesIDs.contains(votes.get(i).getProposedElectiveSubjectsID())) {
                pesIDs.add(i);
            } else {
                return AcceptanceProtocol.ERROR_REPETITION;
            }
        }
        return AcceptanceProtocol.VOTE_SUCCESS;
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

        public void addVote(Vote v) {
            userVotes.add(v);
        }

        public void addVotes(ArrayList<Vote> aLv) {
            userVotes.addAll(aLv);
        }
    }
}
