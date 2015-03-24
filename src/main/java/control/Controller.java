package control;

import com.thoughtworks.xstream.XStream;
import models.FinalClass;
import models.ProposedSubject;
import models.User;
import models.UserType;
import models.Vote;
import interfaces.ControllerInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import utilities.AcceptanceProtocol;
import utilities.EmailSender;

public class Controller implements ControllerInterface {

    private User loggedInUser;

    private static Controller instance = null;
    private DbMock db;
    private int roundNumber;

    public Controller() {
        db = DbMock.getInstance();
        roundNumber = 0;
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    @Override
    public String authenticateUser(String userName, String password) {
        loggedInUser = db.getUserByUsername(userName);
        if (loggedInUser == null) {
            return AcceptanceProtocol.ACCOUNT_LOGIN_ERROR;
        } else {
            if (loggedInUser.getPassword().equals(password)) {
                return AcceptanceProtocol.ACCOUNT_LOGIN_SUCCESS;
            }
            return AcceptanceProtocol.ACCOUNT_LOGIN_ERROR;
        }
    }

    @Override
    public int getRoundNumber() {
        return roundNumber;
    }

    @Override
    public int incrementRoundNumber() {
        roundNumber++;
        return roundNumber;
    }

    @Override
    public User getUser() {
        return loggedInUser;
    }

    @Override
    public List<ProposedSubject> getAllAliveProposedElectiveSubjects() {
        return db.getAliveProposedSubjects();
    }

    @Override
    public List<ProposedSubject> getAllProposedElectiveSubjects() {
        return db.getAllProposedSubjects();
    }

    @Override
    public ProposedSubject addProposedElectiveSubject(ProposedSubject pes) {
        db.addProposedSubject(pes);
        return pes;
    }

    @Override
    public String addVoteFromParticularUser(String vote1, String vote2, String vote3, String vote4) {
        ArrayList<Vote> votedSubjects = new ArrayList<>();
        ProposedSubject currentSubject;
        currentSubject = db.getAliveProposedSubjectByName(vote1);
        if (currentSubject != null) {
            votedSubjects.add(new Vote(loggedInUser, currentSubject, roundNumber, 2));
        }
        currentSubject = db.getAliveProposedSubjectByName(vote2);
        if (currentSubject != null) {
            votedSubjects.add(new Vote(loggedInUser, currentSubject, roundNumber, 2));
        }
        currentSubject = db.getAliveProposedSubjectByName(vote3);
        if (currentSubject != null) {
            votedSubjects.add(new Vote(loggedInUser, currentSubject, roundNumber, 1));
        }
        currentSubject = db.getAliveProposedSubjectByName(vote4);
        if (currentSubject != null) {
            votedSubjects.add(new Vote(loggedInUser, currentSubject, roundNumber, 1));
        }

        String checkup = isChoiceAccepted(votedSubjects);
        if (checkup.equals(AcceptanceProtocol.VOTE_REGISTRATION_SUCCESS)) {
            loggedInUser.setVotes(votedSubjects);
        }
        return checkup;
    }

    private String isChoiceAccepted(ArrayList<Vote> votes) {
        int votesSize = votes.size();
        if (votesSize != 4) {
            return AcceptanceProtocol.VOTE_REGISTRATION_ERROR_AMMOUNT;
        }
        List<Integer> pesIDs = new ArrayList();
        List<Integer> roundNos = new ArrayList();
        for (int i = 0; i < votesSize; i++) {
            pesIDs.add(votes.get(i).getProposedSubject().getId());
            roundNos.add(votes.get(i).getRoundNumber());
        }
        Set<Integer> setPesIDs = new HashSet<Integer>(pesIDs);
        Set<Integer> setRoundNos = new HashSet<Integer>(roundNos);
        if (setPesIDs.size() < pesIDs.size()) {
            return AcceptanceProtocol.VOTE_REGISTRATION_ERROR_REPETITION;
        }
        if (1 != setRoundNos.size()) {
            return AcceptanceProtocol.VOTE_REGISTRATION_ERROR_ROUNDS;

        }
        return AcceptanceProtocol.VOTE_REGISTRATION_SUCCESS;
    }

    @Override
    public List<User> getUsersByUserType(UserType ut) {
        return db.getUsersByUserTpe(ut);
    }

    @Override
    public List<User> getAllUsers() {
        return db.getUsers();
    }

    @Override
    public void setSatisfactionForStudent(int[] a, int[] b, User student) {
        List<ProposedSubject> proposedSubjects = db.getAliveProposedSubjects();
        List<Vote> currentVotes = student.getVotesByRound(1);
        List<ProposedSubject> pollA = new ArrayList<>();
        List<ProposedSubject> pollB = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            pollA.add(proposedSubjects.get(a[i]));
        }
        for (int i = 0; i < b.length; i++) {
            pollB.add(proposedSubjects.get(b[i]));
        }
        int satisfactionA = 0;
        int satisfactionB = 0;
        boolean found;
        for (Vote vote : currentVotes) {
            found = false;
            for (ProposedSubject ps : pollA) {
                if (vote.getProposedSubject().equals(ps)) {
                    found = true;
                    if (vote.getPoints() == 2) {
                        satisfactionA = 50;
                        break;
                    } else {
                        if (satisfactionA < 50) {
                            satisfactionA = 25;
                        }

                    }
                }
            }
            if (!found) {
                for (ProposedSubject ps : pollB) {
                    if (vote.getProposedSubject().equals(ps)) {
                        if (vote.getPoints() == 2) {
                            satisfactionB = 50;
                            break;
                        } else {
                            if (satisfactionB < 50) {
                                satisfactionB = 25;
                            }
                        }
                    }
                }
            }
        }
        student.setSatisfaction(satisfactionA + satisfactionB);
    }

    @Override
    public int getOverallSatisfaction(int[] a, int[] b) {
        int totalSatisfaction = 0;
        List<User> allStudents = getUsersByUserType(db.getUserTypeByName("Student"));
        List<User> allUsersWhoVotedInParticularRound = getAllStudentsWhoHadVotedForParticularRound(allStudents);
        for (User student : allUsersWhoVotedInParticularRound) {
            setSatisfactionForStudent(a, b, student);
            totalSatisfaction += student.getSatisfaction();
        }
        return totalSatisfaction / allStudents.size();
    }

    private List<User> getAllStudentsWhoHadVotedForParticularRound(List<User> allStudents) {
        List<User> allStudentsWhoHadVotedForParticularRound = new ArrayList();
        for (User x : allStudents) {
            if (!x.getVotesByRound(1).isEmpty() && x.getVotesByRound(1).size() == 4) {
                allStudentsWhoHadVotedForParticularRound.add(x);
            }
        }
        return allStudentsWhoHadVotedForParticularRound;
    }

    @Override
    public List<User> getAllStudentsByUnsatisfactionRate() {
        List<User> unsatisfied = db.getUsersByUserTpe(db.getUserTypeByName("Student"));

        Collections.sort(unsatisfied, new Comparator<User>() {

            public int compare(User o1, User o2) {
                return (Integer) o1.getSatisfaction() - (Integer) o2.getSatisfaction();
            }
        });

        for (User x : unsatisfied) {
            List<Vote> votesToBeOrganized = new ArrayList();
            votesToBeOrganized.addAll(x.getVotesByRound(1));
            Collections.sort(votesToBeOrganized, new Comparator<Vote>() {

                public int compare(Vote o1, Vote o2) {
                    return (Integer) o2.getPoints() - (Integer) o1.getPoints();
                }
            });
            x.setVotes(votesToBeOrganized);
        }

        return unsatisfied;
    }

    @Override
    public List<User> getAllTeachers() {
        return db.getUsersByUserTpe(db.getUserTypeByName("Teacher"));
    }

    @Override
    public String addProposedSubject(ProposedSubject ps, int[] selectedIndices) {
        List<User> allTeachers = getAllTeachers();
        List<User> toAdd = new ArrayList<>();
        for (int i = 0; i < selectedIndices.length; i++) {
            toAdd.add(allTeachers.get(selectedIndices[i]));
        }
        ps.setUsers(toAdd);
        db.addProposedSubject(ps);
        return AcceptanceProtocol.NEW_PROPOSED_SUBJECT_SUCCESS;
    }

    @Override
    public String addSubjectsToPolls(int[] a, int[] b) {
        String errorMessage = "";
        if (a.length < 2 && b.length < 2) {
            errorMessage = "You must select at least 2 subjects in each Poll!";
        } else if (a.length < 2) {
            errorMessage = "You must select at least 2 subjects in Poll A!";
        } else if (b.length < 2) {
            errorMessage = "You must select at least 2 subjects in Poll B!";
        } else {
            List<ProposedSubject> pollA = new ArrayList<>();
            List<ProposedSubject> pollB = new ArrayList<>();
            List<ProposedSubject> proposedSubjects = db.getAliveProposedSubjects();
            for (int i = 0; i < a.length; i++) {
                pollA.add(proposedSubjects.get(a[i]));
            }
            for (int i = 0; i < b.length; i++) {
                pollB.add(proposedSubjects.get(b[i]));
            }
            errorMessage = AcceptanceProtocol.SUBJECTS_ADDED_TO_POLLS_SUCCESS + "\n" + db.fillPolls(pollA, pollB);
        }
        return errorMessage;
    }

    @Override
    public String selectSubjectsForRound1(int[] selectedIndexes) {
        if (selectedIndexes.length < 4) {
            return AcceptanceProtocol.SUBJECTS_ADDED_TO_ROUND_1_FAILURE;
        } else {
            List<ProposedSubject> allSubjects = db.getAllProposedSubjects();
            for (int i = 0; i < allSubjects.size(); i++) {
                boolean found = false;
                for (int j = 0; j < selectedIndexes.length; j++) {
                    if (i == selectedIndexes[j]) {
                        found = true;
                    }
                }
                allSubjects.get(i).setIsAlive(found);
            }
            return AcceptanceProtocol.SUBJECTS_ADDED_TO_ROUND_1_SUCCESS;
        }
    }

    @Override
    public List<User> getAllStudents() {
        return getUsersByUserType(db.getUserTypeByName("Student"));
    }

    @Override
    public List<ProposedSubject> getSubjectsByPool(String pool) {
        List<ProposedSubject> subj = new ArrayList();
        for (ProposedSubject ps : db.getAliveProposedSubjects()) {
            if (!ps.getPoolOptions().equals("")) {
                if (ps.getPoolOptions().equals(pool)) {
                    subj.add(ps);
                }
            }
        }
        return subj;
    }

    @Override
    public FinalClass addNewClass(List<User> students, ProposedSubject subject) {
        FinalClass c = new FinalClass(subject);
        c.setStudents(students);
        return db.addClass(c);
    }

    @Override
    public FinalClass editStudentsInClass(List<User> students, int classIndex) {
        return db.editStudentsInClass(students, classIndex);
    }

    @Override
    public FinalClass editTeachersInClass(List<User> teachers, int classIndex) {
        return db.editTeachersInClass(teachers, classIndex);
    }

    @Override
    public List<FinalClass> getAllClasses() {
        return db.getAllClasses();
    }

    @Override
    public List<User> getStudentsForClass(FinalClass c) {
        return c.getStudents();
    }

    @Override
    public String sendMail() {
        List<FinalClass> classes = db.getAllClasses();
        String errorMessage = "Error!";
        for (FinalClass fc : classes) {
            if (fc.getTeachers().isEmpty()) {
                errorMessage += "\n" + fc.getName() + " class has no teacher!";
            }
            if (fc.getStudents().isEmpty()) {
                errorMessage += "\n" + fc.getName() + " class has no students!";
            }
        }
        if (errorMessage.equals("Error!")) {
            XStream xmlParser = new XStream();
            String xmlClasses = xmlParser.toXML(classes);
            List<User> receivers = db.getUsersByUserTpe(new UserType("Head"));

            if (EmailSender.send(receivers.get(0).getEmail(), xmlClasses)) {
                return AcceptanceProtocol.EMAIL_SEND_SUCCESS + receivers.get(0).getEmail();
            }
            return AcceptanceProtocol.EMAIL_SEND_FAIL;
        } else {
            return errorMessage;
        }
    }
}
