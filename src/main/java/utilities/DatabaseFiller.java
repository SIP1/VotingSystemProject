package utilities;

import models.ProposedSubject;
import models.User;
import models.UserType;
import models.Vote;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DatabaseFiller {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory( "SIP_PU" );
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tr;

    public static void main( String[] args ) {
        new DatabaseFiller().base();

    }

    private void base() {
        UserType ut1 = new UserType( "Student" );
        UserType ut2 = new UserType( "Teacher" );
        UserType ut3 = new UserType( "GOD" );

        ProposedSubject ps1 = new ProposedSubject( "C#", "Java Like", Boolean.TRUE, "non" );
        ProposedSubject ps2 = new ProposedSubject( "C++", "complex", Boolean.TRUE, "non" );
        ProposedSubject ps3 = new ProposedSubject( "Game Design", "World Of Warcraft", Boolean.TRUE, "non" );
        ProposedSubject ps4 = new ProposedSubject( "AI", "Make it think", Boolean.TRUE, "non" );

        User user1 = new User( "bobkoo", "12345", "boyko", "mail@mail.com", new UserType( "GOD" ) );
        User user2 = new User( "smara", "67890", "smara", "mail@mail.com", new UserType( "Student" ) );
        User user3 = new User( "peterto", "abcde", "peter", "mail@mail.com", new UserType( "Teacher" ) );
        User user4 = new User( "mada1994", "qwerty", "mada", "mail@mail.com", new UserType( "Student" ) );

        user1.setUserType( ut3 );
        user2.setUserType( ut1 );
        user3.setUserType( ut2 );
        user4.setUserType( ut1 );

        List<User> usersList = new ArrayList();
        List<ProposedSubject> subjectsList = new ArrayList();

        subjectsList.add( ps3 );
        subjectsList.add( ps1 );
        usersList.add( user1 );
        user1.setProposedSubjects( subjectsList );
        ps1.setUsers( usersList );
        ps3.setUsers( usersList );

        List<User> usersList2 = new ArrayList();
        List<ProposedSubject> subjectsList2 = new ArrayList();

        subjectsList2.add( ps2 );
        usersList2.add( user2 );
        user2.setProposedSubjects( subjectsList2 );
        ps2.setUsers( usersList2 );
        subjectsList2.add( ps4 );
        usersList2.add( user3 );
        user3.setProposedSubjects( subjectsList2 );
        ps4.setUsers( usersList2 );

        Vote v1 = new Vote( user1, ps4, 1, 1 );
        Vote v2 = new Vote( user1, ps3, 1, 2 );
        Vote v3 = new Vote( user1, ps2, 1, 1 );
        Vote v4 = new Vote( user1, ps1, 1, 2 );

        initializeTransactions();
        tr.begin();
        em.persist( ut1 );
        em.persist( ut2 );
        em.persist( ut3 );
        em.persist( ps1 );
        em.persist( ps2 );
        em.persist( ps3 );
        em.persist( ps4 );
        em.persist( user1 );
        em.persist( user2 );
        em.persist( user3 );
        em.persist( user4 );
        em.persist( v1 );
        em.persist( v2 );
        em.persist( v3 );
        em.persist( v4 );
        tr.commit();
        closeTransactions();
    }

    private void initializeTransactions() {
        tr = em.getTransaction();
    }

    private void closeTransactions() {
        em.close();
    }

}
