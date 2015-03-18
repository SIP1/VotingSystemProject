/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import com.thoughtworks.xstream.XStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import models.FinalClass;
import models.ProposedSubject;
import models.User;
import models.UserType;

public class EmailSender {

    public static void main(String[] args) {

        ArrayList<ProposedSubject> proposedSubjects = new ArrayList();
        ArrayList<User> users = new ArrayList();
        ArrayList<UserType> userTypes = new ArrayList<>();
        ArrayList<FinalClass> finalClasses = new ArrayList<>();
        List<User> teachers = new ArrayList<>();
        List<User> students = new ArrayList<>();

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

        //add teachers to the proposed subject
        teachers.add(users.get(1));
        teachers.add(users.get(4));
        proposedSubjects.get(0).setUsers(teachers);

        //add teachers to the proposed subject
        students.add(users.get(0));
        students.add(users.get(3));

        //create final classes
        FinalClass finalClass = new FinalClass(proposedSubjects.get(0));
        finalClass.setName("This is a test class");
        finalClass.setStudents(students);
        finalClass.setTeacher(teachers);
        finalClasses.add(finalClass);

        XStream x = new XStream();
        String xml = x.toXML(finalClasses);

        final String from_address = "group2sip@gmail.com";
        final String to_address = "cristina.madalina.dragan@gmail.com";
        final String subject = "Testing";
        final String message_text = "Dear Mail Crawler,\n\n" + xml;

        final String username = "group2sip@gmail.com";
        final String password = "wejustwanttosendanemail";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from_address));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to_address));
            message.setSubject(subject);
            message.setText(message_text);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
