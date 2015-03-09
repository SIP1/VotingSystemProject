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

    public DbMock() {
        proposedSubjects = new ArrayList();
        users = new ArrayList();
        userTypes = new ArrayList();
        votes = new ArrayList();
        populate();
    }
    
    private static DbMock instance = null;

    public static DbMock getInstance()
    {
        
        if (instance == null)
        {
            instance = new DbMock();
        }
        return instance;
    }

    public List<ProposedSubject> getProposedSubjects() {
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
    
    public User getUserByUsername(String username){
        for(User user : users)
        {
            if(user.getUsername().equals(username) )
            {
                return user;
            }
        }
        return null;
    }
    
    public UserType getUserTypeByName(String type)
    {
        for(UserType ut : userTypes)
        {
            if(ut.getName().endsWith(type))
            {
                return ut;
            }
        }
        return null;
    }
    
    public ProposedSubject getProposedSubjectById(int id)
    {
        for(ProposedSubject ps : proposedSubjects)
        {
            if(ps.getId() == id)
            {
                return ps;
            }
        }
        return null;
    }
    
    public List<User> getUsersByUserTpe(UserType ut) {
        switch (ut.getName()) {
            case "Teacher":
                List<User> teachers = new ArrayList<>();
                for(User user : users)
                {
                    if(user.getUserType().getName().equals("Teacher"))
                    {
                        teachers.add(user);
                    }
                }
                return teachers;
            case "Student":
                List<User> students = new ArrayList<>();
                for(User user : users)
                {
                    if(user.getUserType().getName().equals("Student"))
                    {
                        students.add(user);
                    }
                }
                return students;
            default:
                return null;
        }
    }
    
    private void populate()
    {
        //populate usertypes
        userTypes.add(new UserType("Teacher"));
        userTypes.add(new UserType("Student"));
        userTypes.add(new UserType("Head"));
        
        //populate teachers
        users.add(new User("pelo", "1", "Peter Lorensen", "pelo@cphbusiness.dk", getUserTypeByName("Teacher")));
        users.add(new User("lam", "2", "Lars Mortensen", "lam@cphbusiness.dk", getUserTypeByName("Teacher")));
        users.add(new User("tor", "3", "Torben", "tor@cphbusiness.dk", getUserTypeByName("Teacher")));
        
        //populate student
        users.add(new User("boyko", "boyko", "Boyko Surlev", "boyko@gmail.com", getUserTypeByName("Student")));
        users.add(new User("smara", "smara", "Smaranda Dungeanu", "smara@gmail.com", getUserTypeByName("Student")));
        users.add(new User("mada", "mada", "Madalina Dragan", "mada@gmail.com", getUserTypeByName("Student")));
        users.add(new User("cristi", "cristi", "Cristi Nita", "cristi@gmail.com", getUserTypeByName("Student")));
        
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
        votes.add(new Vote(getUserByUsername("boyko"), getProposedSubjectById(101), 1, 2));
        votes.add(new Vote(getUserByUsername("boyko"), getProposedSubjectById(102), 1, 2));
        votes.add(new Vote(getUserByUsername("boyko"), getProposedSubjectById(103), 1, 1));
        votes.add(new Vote(getUserByUsername("boyko"), getProposedSubjectById(104), 1, 1));
    }
}
