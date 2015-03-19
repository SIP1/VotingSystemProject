package mocks;

import models.FinalClass;
import models.ProposedSubject;
import models.User;
import models.UserType;
import models.Vote;
import com.thoughtworks.xstream.XStream;
import edu.emory.mathcs.backport.java.util.Collections;
import interfaces.ControllerInterface;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import utilities.AcceptanceProtocol;

public class ControllerMock implements ControllerInterface {

    public User user = new User();
    public ArrayList<ProposedSubject> proposedSubjects = new ArrayList();
    public ArrayList<User> users = new ArrayList();
    public ArrayList<UserType> userTypes = new ArrayList<>();
    public ArrayList<Vote> votes = new ArrayList<>();
    public ArrayList<FinalClass> finalClasses = new ArrayList<>();
    public List<User> teachers = new ArrayList<>();
    public List<User> students = new ArrayList<>();

    public ControllerMock() {
        //create user types
        userTypes.add(new UserType("Student"));
        userTypes.add(new UserType("Teacher"));
        userTypes.add(new UserType("Administrator"));

        //create users
        users.add(new User("TestUser", "12345", "boyko", "email@email.mail", userTypes.get(0)));
        users.add(new User("TestUser2", "test", "Testing user 2", "test@test.com", userTypes.get(1)));
        users.add(new User("TestUser3", "test", "Admin", "admin@test.com", userTypes.get(2)));
        users.add(new User("TestUser4", "test", "Lala", "skat@test.com", userTypes.get(0)));
        users.add(new User("TestUser5", "test", "Teacher", "test@test.com", userTypes.get(1)));

        //create proposed subjects
        proposedSubjects.add(new ProposedSubject("Test subject 1", "It was only just a test", true, "B"));
        proposedSubjects.add(new ProposedSubject("Test subject 2", "It was only just a test", true, "A"));
        proposedSubjects.add(new ProposedSubject("Test subject 3", "It was only just a test", true, "A"));
        proposedSubjects.add(new ProposedSubject("Test subject 4", "It was only just a test", true, "B"));
        proposedSubjects.add(new ProposedSubject("Test subject DEAD", "It was only just a dead test", false, "B"));

        //add teachers to the proposed subject
        teachers.add(users.get(1));
        teachers.add(users.get(4));
        proposedSubjects.get(0).setUsers(teachers);

        //add teachers to the proposed subject
        students.add(users.get(0));
        students.add(users.get(3));

        //assing the current user which will be the student
        user = users.get(0);

        //satisfaction
        user.setSatisfaction(0);
        users.get(3).setSatisfaction(25);

        //create votes
        votes.add(new Vote(user, proposedSubjects.get(0), 1, 2));
        votes.add(new Vote(user, proposedSubjects.get(1), 1, 2));
        votes.add(new Vote(user, proposedSubjects.get(2), 1, 1));
        votes.add(new Vote(user, proposedSubjects.get(3), 1, 1));

        //set votes to user
        user.setVotes(votes);

        //create final classes
        FinalClass finalClass = new FinalClass(proposedSubjects.get(0));
        finalClass.setName("This is a test class");
        finalClass.setStudents(students);
        finalClass.setTeachers(teachers);
        finalClasses.add(finalClass);
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

    @Override
    public List<User> getUsersByUserType(UserType ut) {
        List<User> filteredUsers = new ArrayList<>();
        for (User u : users) {
            if (u.getUserType() == ut) {
                filteredUsers.add(u);
            }
        }

        return filteredUsers;
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public void setSatisfactionForStudent(int[] a, int[] b, User student) {
        if (a[0] == 1) {
            student.setSatisfaction(50);
        }
        if (b[0] == 2) {
            student.setSatisfaction(student.getSatisfaction() + 25);
        }
    }

    @Override
    public int getOverallSatisfaction(int[] a, int[] b) {
        int overall = 0;
        if (a[0] == 1) {
            overall = 25;
        }
        if (b[0] == 2) {
            overall = overall + 25;
        }
        return overall;
    }

    @Override
    public List<User> getAllStudentsByUnsatisfactionRate() {
        List<User> unsatisfied = new ArrayList<>();
        for (User u : users) {
            if (u.getUserType() == userTypes.get(0)) {
                unsatisfied.add(u);
            }
        }

        Collections.sort(unsatisfied, new Comparator<User>() {

            @Override
            public int compare(User o1, User o2) {
                return o1.getSatisfaction() - o2.getSatisfaction();
            }
        });
        return unsatisfied;
    }

    @Override
    public List<User> getAllTeachers() {
        List<User> teachers = new ArrayList<>();
        for (User user : users) {
            if (user.getUserType().getName().equals("Teacher")) {
                teachers.add(user);
            }
        }
        return teachers;
    }

    @Override
    public String addProposedSubject(ProposedSubject ps, int[] selectedIndices) {
        List<User> teachers = getAllTeachers();
        List<User> subjectTeachers = new ArrayList<>();
        for (int i = 0; i < selectedIndices.length; i++) {
            subjectTeachers.add(teachers.get(selectedIndices[i]));
        }
        ps.setUsers(subjectTeachers);
        return AcceptanceProtocol.NEW_PROPOSED_SUBJECT_SUCCESS;
    }

    @Override
    public String addSubjectsToPolls(int[] a, int[] b) {
        if (a[0] == 1 && b[0] == 2) {
            return AcceptanceProtocol.SUBJECTS_ADDED_TO_POLLS_SUCCESS;
        }
        return "";
    }

    @Override
    public List<ProposedSubject> getAllAliveProposedElectiveSubjects() {
        List<ProposedSubject> aliveSubjects = new ArrayList<>();
        for (ProposedSubject proposedSubject : proposedSubjects) {
            if (proposedSubject.isIsAlive()) {
                aliveSubjects.add(proposedSubject);
            }
        }
        return aliveSubjects;
    }

    @Override
    public List<ProposedSubject> getAllProposedElectiveSubjects() {
        return proposedSubjects;
    }

    @Override
    public String selectSubjectsForRound1(int[] selectedIndexes) {
        if (selectedIndexes[0] == 1) {
            return AcceptanceProtocol.SUBJECTS_ADDED_TO_ROUND_1_SUCCESS;
        }
        return "";
    }

    @Override
    public List<User> getAllStudents() {
        List<User> students = new ArrayList<>();
        for (User user : users) {
            if (user.getUserType().equals(userTypes.get(0))) {
                students.add(user);
            }
        }
        return students;
    }

    @Override
    public List<ProposedSubject> getSubjectsByPool(String pool) {
        List<ProposedSubject> poolSubjects = new ArrayList<>();
        for (ProposedSubject proposedSubject : proposedSubjects) {
            if (proposedSubject.getPoolOptions().equals(pool)) {
                poolSubjects.add(proposedSubject);
            }
        }
        return poolSubjects;
    }

    @Override
    public String addNewClass(List<User> students, ProposedSubject subject) {
        if (students.size() != 0 && subject != null) {
            return subject.getName() + "(" + students.size() + ")";
        }
        return AcceptanceProtocol.FINAL_CLASS_ADDING_ERROR;
    }

    @Override
    public List<FinalClass> getAllClasses() {
        return finalClasses;
    }

    @Override
    public List<User> getStudentsForClass(FinalClass c) {
        return c.getStudents();
    }

    public String createClassesXML() {
        XStream x = new XStream();
        String xml = x.toXML(proposedSubjects);
        return xml;
    }

    @Override
    public String sendMail()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
