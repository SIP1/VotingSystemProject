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
import utilities.AcceptanceProtocol;

public class ControllerMock implements ControllerInterface
{

    public User user = new User();
    public ArrayList<ProposedSubject> proposedSubjects = new ArrayList();
    public ArrayList<User> users = new ArrayList();
    public ArrayList<UserType> userTypes = new ArrayList();
    public ArrayList<Vote> votes = new ArrayList();
    public ArrayList<FinalClass> finalClasses = new ArrayList();
    public List<User> teachers = new ArrayList();
    public List<User> students = new ArrayList();
    private int roundNumber;

    public ControllerMock()
    {
        userTypes.add(new UserType("Student"));
        userTypes.add(new UserType("Teacher"));
        userTypes.add(new UserType("Administrator"));

        users.add(new User("TestUser", "12345", "boyko", "email@email.mail", userTypes.get(0)));
        users.add(new User("TestUser2", "test", "Testing user 2", "test@test.com", userTypes.get(1)));
        users.add(new User("TestUser3", "test", "Admin", "admin@test.com", userTypes.get(2)));
        users.add(new User("TestUser4", "test", "Lala", "skat@test.com", userTypes.get(0)));
        users.add(new User("TestUser5", "test", "Teacher", "test@test.com", userTypes.get(1)));

        proposedSubjects.add(new ProposedSubject("Test subject 1", "It was only just a test", true, "B"));
        proposedSubjects.add(new ProposedSubject("Test subject 2", "It was only just a test", true, "A"));
        proposedSubjects.add(new ProposedSubject("Test subject 3", "It was only just a test", true, "A"));
        proposedSubjects.add(new ProposedSubject("Test subject 4", "It was only just a test", true, "B"));
        proposedSubjects.add(new ProposedSubject("Test subject DEAD", "It was only just a dead test", false, "B"));

        teachers.add(users.get(1));
        teachers.add(users.get(4));
        proposedSubjects.get(0).setUsers(teachers);

        students.add(users.get(0));
        students.add(users.get(3));

        user = users.get(0);

        user.setSatisfaction(0);
        users.get(3).setSatisfaction(25);

        votes.add(new Vote(user, proposedSubjects.get(0), 1, 2));
        votes.add(new Vote(user, proposedSubjects.get(1), 1, 2));
        votes.add(new Vote(user, proposedSubjects.get(2), 1, 1));
        votes.add(new Vote(user, proposedSubjects.get(3), 1, 1));

        user.setVotes(votes);

        FinalClass finalClass = new FinalClass(proposedSubjects.get(0));
        finalClass.setName("This is a test class");
        finalClass.setStudents(students);
        finalClass.setTeachers(teachers);
        finalClasses.add(finalClass);

        roundNumber = 0;
    }

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
    public User getUser()
    {
        return user;
    }

    @Override
    public ProposedSubject addProposedElectiveSubject(ProposedSubject pes)
    {
        if (pes != null)
        {
            proposedSubjects.add(pes);
        }
        return pes;
    }

    @Override
    public String addVoteFromParticularUser(String vote1, String vote2, String vote3, String vote4)
    {

        if (vote1 != null && !vote1.equals("") && vote2 != null && !vote2.equals("") && vote3 != null && !vote3.equals("") && vote4 != null && !vote4.equals(""))
        {
            votes.add(new Vote(user, new ProposedSubject(vote1, "TEST", true, ""), roundNumber, 2));
            votes.add(new Vote(user, new ProposedSubject(vote2, "TEST", true, ""), roundNumber, 2));
            votes.add(new Vote(user, new ProposedSubject(vote3, "TEST", true, ""), roundNumber, 1));
            votes.add(new Vote(user, new ProposedSubject(vote4, "TEST", true, ""), roundNumber, 1));

            return AcceptanceProtocol.VOTE_REGISTRATION_SUCCESS;
        }
        return AcceptanceProtocol.VOTE_REGISTRATION_ERROR_AMMOUNT;
    }

    @Override
    public List<User> getUsersByUserType(UserType ut)
    {
        List<User> filteredUsers = new ArrayList();
        for (User u : users)
        {
            if (u.getUserType() == ut)
            {
                filteredUsers.add(u);
            }
        }
        return filteredUsers;
    }

    @Override
    public List<User> getAllUsers()
    {
        return users;
    }

    @Override
    public void setSatisfactionForStudent(int[] a, int[] b, User student)
    {
        if (a.length > 0)
        {
            student.setSatisfaction(50);
        }
        else
        {
            student.setSatisfaction(0);
        }
        if (b.length > 0)
        {
            student.setSatisfaction(student.getSatisfaction() + 25);
        }
    }

    @Override
    public int getOverallSatisfaction(int[] a, int[] b)
    {
        int overall = 0;
        if (a.length > 0)
        {
            overall = 25;
        }
        if (b.length > 0)
        {
            overall = overall + 25;
        }
        return overall;
    }

    @Override
    public List<User> getAllStudentsByUnsatisfactionRate()
    {
        List<User> unsatisfied = new ArrayList();
        for (User u : users)
        {
            if (u.getUserType() == userTypes.get(0))
            {
                unsatisfied.add(u);
            }
        }

        Collections.sort(unsatisfied, new Comparator<User>()
        {

            @Override
            public int compare(User o1, User o2)
            {
                return o1.getSatisfaction() - o2.getSatisfaction();
            }
        });
        return unsatisfied;
    }

    @Override
    public List<User> getAllTeachers()
    {
        List<User> teachers = new ArrayList();
        for (User user : users)
        {
            if (user.getUserType() == userTypes.get(1))
            {
                teachers.add(user);
            }
        }
        return teachers;
    }

    @Override
    public String addProposedSubject(ProposedSubject ps, int[] selectedIndices)
    {
        if (ps == null)
        {
            return AcceptanceProtocol.NEW_PROPOSED_SUBJECT_FAILURE_SUBJECT;
        }
        if (selectedIndices.length == 0)
        {
            return AcceptanceProtocol.NEW_PROPOSED_SUBJECT_FAILURE_TEACHER;
        }
        List<User> teachers = getAllTeachers();
        List<User> subjectTeachers = new ArrayList();
        for (int i = 0; i < selectedIndices.length; i++)
        {
            subjectTeachers.add(teachers.get(selectedIndices[i]));
        }
        ps.setUsers(subjectTeachers);
        return AcceptanceProtocol.NEW_PROPOSED_SUBJECT_SUCCESS;
    }

    @Override
    public String addSubjectsToPools(int[] a, int[] b)
    {
        if (a.length < 2 || b.length < 2)
        {
            return AcceptanceProtocol.SUBJECTS_ADDED_TO_POOLS_FAILURE;
        }
        else
        {
            return AcceptanceProtocol.SUBJECTS_ADDED_TO_POOLS_SUCCESS;
        }
    }

    @Override
    public List<ProposedSubject> getAllAliveProposedElectiveSubjects()
    {
        List<ProposedSubject> aliveSubjects = new ArrayList();
        for (ProposedSubject proposedSubject : proposedSubjects)
        {
            if (proposedSubject.isItAlive())
            {
                aliveSubjects.add(proposedSubject);
            }
        }
        return aliveSubjects;
    }

    @Override
    public List<ProposedSubject> getAllProposedElectiveSubjects()
    {
        return proposedSubjects;
    }

    @Override
    public String selectSubjectsForRound1(int[] selectedIndexes)
    {
        if (selectedIndexes.length < 4)
        {
            return AcceptanceProtocol.SUBJECTS_ADDED_TO_ROUND_1_FAILURE;
        }
        else
        {
            return AcceptanceProtocol.SUBJECTS_ADDED_TO_ROUND_1_SUCCESS;
        }
    }

    @Override
    public List<User> getAllStudents()
    {
        List<User> students = new ArrayList();
        for (User user : users)
        {
            if (user.getUserType().equals(userTypes.get(0)))
            {
                students.add(user);
            }
        }
        return students;
    }

    @Override
    public List<ProposedSubject> getSubjectsByPool(String pool)
    {
        List<ProposedSubject> poolSubjects = new ArrayList();
        for (ProposedSubject proposedSubject : proposedSubjects)
        {
            if (proposedSubject.getPoolOptions().equals(pool))
            {
                poolSubjects.add(proposedSubject);
            }
        }
        return poolSubjects;
    }

    @Override
    public FinalClass addNewClass(List<User> students, ProposedSubject subject)
    {
        if (subject == null)
        {
            return null;
        }
        if (students.isEmpty())
        {
            return null;
        }

        FinalClass fc = new FinalClass(subject);
        fc.setStudents(students);
        return fc;
    }

    @Override
    public List<FinalClass> getAllClasses()
    {
        return finalClasses;
    }

    @Override
    public List<User> getStudentsForClass(FinalClass c)
    {
        return c.getStudents();
    }

    @Override
    public String sendMail()
    {
        XStream xmlParser = new XStream();
        String xml = xmlParser.toXML(finalClasses);
        return xml;
    }

    @Override
    public int getRoundNumber()
    {
        return roundNumber;
    }

    @Override
    public int incrementRoundNumber()
    {
        roundNumber++;
        return roundNumber;
    }

    @Override
    public FinalClass editStudentsInClass(List<User> students, int classIndex)
    {
        if (classIndex > finalClasses.size())
        {
            return null;
        }
        if (students == null)
        {

            return null;
        }
        if (!students.isEmpty())
        {
            return null;
        }
        finalClasses.get(classIndex).setStudents(students);
        return finalClasses.get(classIndex);
    }

    @Override
    public FinalClass editTeachersInClass(List<User> teachers, int classIndex)
    {
        if (classIndex > finalClasses.size())
        {
            return null;
        }
        if (teachers == null)
        {

            return null;
        }
        if (!teachers.isEmpty())
        {
            return null;
        }
        finalClasses.get(classIndex).setTeachers(teachers);
        return finalClasses.get(classIndex);
    }
}
