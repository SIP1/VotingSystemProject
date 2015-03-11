package control;

import JPA2.ProposedSubject;
import JPA2.User;
import JPA2.UserType;
import JPA2.Vote;
import java.util.ArrayList;
import java.util.List;

public class DbMock {

    private List<ProposedSubject> proposedSubjects;
    private List<User> users;
    private List<UserType> userTypes;
    private List<Vote> votes;
    private List<ProposedSubject> pollA, pollB;

    public DbMock() {
        proposedSubjects = new ArrayList();
        users = new ArrayList();
        userTypes = new ArrayList();
        votes = new ArrayList();
        populate();
    }

    private static DbMock instance = null;

    public static DbMock getInstance() {

        if (instance == null) {
            instance = new DbMock();
        }
        return instance;
    }

    public List<ProposedSubject> getAliveProposedSubjects() {
        List<ProposedSubject> aliveSubject = new ArrayList<ProposedSubject>();
        for (ProposedSubject p : proposedSubjects) {
            if (p.isIsAlive()) {
                aliveSubject.add(p);
            }
        }
        return aliveSubject;
    }
    
      public List<ProposedSubject> getAllProposedSubjects() {
        return proposedSubjects;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<UserType> getUserTypes() {
        return userTypes;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public UserType getUserTypeByName(String type) {
        for (UserType ut : userTypes) {
            if (ut.getName().endsWith(type)) {
                return ut;
            }
        }
        return null;
    }

    public ProposedSubject getProposedSubjectById(int id) {
        for (ProposedSubject ps : proposedSubjects) {
            if (ps.getId() == id) {
                return ps;
            }
        }
        return null;
    }

    public List<User> getUsersByUserTpe(UserType ut) {
        switch (ut.getName()) {
            case "Teacher":
                List<User> teachers = new ArrayList<>();
                for (User user : users) {
                    if (user.getUserType().getName().equals("Teacher")) {
                        teachers.add(user);
                    }
                }
                return teachers;
            case "Student":
                List<User> students = new ArrayList<>();
                for (User user : users) {
                    if (user.getUserType().getName().equals("Student")) {
                        students.add(user);
                    }
                }
                return students;
            default:
                return null;
        }
    }

    public String fillPolls(List<ProposedSubject> a, List<ProposedSubject> b) {
        for (ProposedSubject ps : proposedSubjects) {
            boolean found = false;
            for (ProposedSubject psa : a) {
                if (ps.equals(psa)) {
                    ps.setPoolOptions("A");
                    found = true;
                }
            }
            for (ProposedSubject psb : b) {
                if (ps.equals(psb)) {
                    ps.setPoolOptions("B");
                    found = true;
                }
            }
            if (found == false) {
                ps.setPoolOptions(null);
            }
        }
        return "Size of poll A: " + a.size() + ", size of poll B: " + b.size();
    }

    public void addProposedSubject(ProposedSubject ps) {
        proposedSubjects.add(ps);
    }

    private void populate() {
        //populate usertypes
        userTypes.add(new UserType("Teacher"));
        userTypes.add(new UserType("Student"));
        userTypes.add(new UserType("Head"));

        //populate teachers
        users.add(new User("pelo", "1", "Peter Lorensen", "pelo@cphbusiness.dk", getUserTypeByName("Teacher")));
        users.add(new User("lam", "2", "Lars Mortensen", "lam@cphbusiness.dk", getUserTypeByName("Teacher")));
        users.add(new User("tor", "3", "Torben", "tor@cphbusiness.dk", getUserTypeByName("Teacher")));

        //populate student
        User boyko = new User("boyko", "boyko", "Boyko Surlev", "boyko@gmail.com", getUserTypeByName("Student"));
        User smara = new User("smara", "smara", "Smaranda Dungeanu", "smara@gmail.com", getUserTypeByName("Student"));
        User mada = new User("mada", "mada", "Madalina Dragan", "mada@gmail.com", getUserTypeByName("Student"));
        User cristi = new User("cristi", "cristi", "Cristi Nita", "cristi@gmail.com", getUserTypeByName("Student"));
        User peter = new User("peter", "peter", "Peter", "peter@mail.peter", getUserTypeByName("Student"));
        User marek = new User("marek", "marek", "Marek", "marek@mail.peter", getUserTypeByName("Student"));
        boyko.setSatisfaction(50);
        smara.setSatisfaction(25);
        cristi.setSatisfaction(50);
        mada.setSatisfaction(0);
        peter.setSatisfaction(75);
        marek.setSatisfaction(100);
        users.add(boyko);
        users.add(smara);
        users.add(mada);
        users.add(cristi);
        users.add(peter);
        users.add(marek);

        //make head of program
        users.add(new User("caroline", "caroline", "Caroline", "caroline@gmail.com", getUserTypeByName("Head")));

        //populate subjects
        List<User> teachersForProposedSubjects = new ArrayList<>();
        proposedSubjects.add(new ProposedSubject("Android", "none", Boolean.TRUE, "A"));
        proposedSubjects.get(proposedSubjects.size() - 1).setId(101);
        teachersForProposedSubjects.add(getUserByUsername("pelo"));
        teachersForProposedSubjects.add(getUserByUsername("lam"));
        proposedSubjects.get(proposedSubjects.size() - 1).setUsers(teachersForProposedSubjects);
        proposedSubjects.add(new ProposedSubject("C#", "none", Boolean.TRUE, "B"));
        proposedSubjects.get(proposedSubjects.size() - 1).setId(102);
        proposedSubjects.add(new ProposedSubject("Arduino", "none", Boolean.TRUE, "B"));
        proposedSubjects.get(proposedSubjects.size() - 1).setId(103);
        proposedSubjects.add(new ProposedSubject("AI", "none", Boolean.TRUE, "A"));
        proposedSubjects.get(proposedSubjects.size() - 1).setId(104);
        teachersForProposedSubjects.clear();
        teachersForProposedSubjects.add(getUserByUsername("tor"));
        proposedSubjects.get(proposedSubjects.size() - 1).setUsers(teachersForProposedSubjects);
        proposedSubjects.add(new ProposedSubject("Game Design", "none", Boolean.TRUE, "A"));
        proposedSubjects.get(proposedSubjects.size() - 1).setId(105);

        //populate votes
        List<Vote> boykoVotes = new ArrayList();
        boykoVotes.add(new Vote(getUserByUsername("boyko"), getProposedSubjectById(101), 1, 2));
        boykoVotes.add(new Vote(getUserByUsername("boyko"), getProposedSubjectById(102), 1, 2));
        boykoVotes.add(new Vote(getUserByUsername("boyko"), getProposedSubjectById(103), 1, 1));
        boykoVotes.add(new Vote(getUserByUsername("boyko"), getProposedSubjectById(104), 1, 1));
        votes.addAll(boykoVotes);
        getUserByUsername("boyko").setVotes(boykoVotes);

        List<Vote> smaraVotes = new ArrayList();
        smaraVotes.add(new Vote(getUserByUsername("smara"), getProposedSubjectById(101), 1, 2));
        smaraVotes.add(new Vote(getUserByUsername("smara"), getProposedSubjectById(102), 1, 1));
        smaraVotes.add(new Vote(getUserByUsername("smara"), getProposedSubjectById(103), 1, 1));
        smaraVotes.add(new Vote(getUserByUsername("smara"), getProposedSubjectById(105), 1, 2));
        votes.addAll(smaraVotes);
        getUserByUsername("smara").setVotes(smaraVotes);

        List<Vote> madaVotes = new ArrayList();
        madaVotes.add(new Vote(getUserByUsername("mada"), getProposedSubjectById(101), 1, 1));
        madaVotes.add(new Vote(getUserByUsername("mada"), getProposedSubjectById(104), 1, 2));
        madaVotes.add(new Vote(getUserByUsername("mada"), getProposedSubjectById(103), 1, 1));
        madaVotes.add(new Vote(getUserByUsername("mada"), getProposedSubjectById(105), 1, 2));
        votes.addAll(madaVotes);
        getUserByUsername("mada").setVotes(madaVotes);

        List<Vote> cristiVotes = new ArrayList();
        cristiVotes.add(new Vote(getUserByUsername("cristi"), getProposedSubjectById(101), 1, 1));
        cristiVotes.add(new Vote(getUserByUsername("cristi"), getProposedSubjectById(102), 1, 2));
        cristiVotes.add(new Vote(getUserByUsername("cristi"), getProposedSubjectById(104), 1, 2));
        cristiVotes.add(new Vote(getUserByUsername("cristi"), getProposedSubjectById(105), 1, 1));
        votes.addAll(cristiVotes);
        getUserByUsername("cristi").setVotes(cristiVotes);

        List<Vote> peterVotes = new ArrayList();
        peterVotes.add(new Vote(getUserByUsername("peter"), getProposedSubjectById(104), 1, 2));
        peterVotes.add(new Vote(getUserByUsername("peter"), getProposedSubjectById(102), 1, 1));
        peterVotes.add(new Vote(getUserByUsername("peter"), getProposedSubjectById(103), 1, 1));
        peterVotes.add(new Vote(getUserByUsername("peter"), getProposedSubjectById(105), 1, 2));
        votes.addAll(peterVotes);
        getUserByUsername("peter").setVotes(peterVotes);

        List<Vote> marekVotes = new ArrayList();
        marekVotes.add(new Vote(getUserByUsername("marek"), getProposedSubjectById(101), 1, 2));
        marekVotes.add(new Vote(getUserByUsername("marek"), getProposedSubjectById(102), 1, 1));
        marekVotes.add(new Vote(getUserByUsername("marek"), getProposedSubjectById(103), 1, 2));
        marekVotes.add(new Vote(getUserByUsername("marek"), getProposedSubjectById(104), 1, 1));
        votes.addAll(marekVotes);
        getUserByUsername("marek").setVotes(marekVotes);
    }
}
