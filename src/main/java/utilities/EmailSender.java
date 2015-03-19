package utilities;

import com.thoughtworks.xstream.XStream;
import java.util.ArrayList;
import java.util.Date;
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

public class EmailSender
{

    public static void main(String[] args)
    {
        new EmailSender().eMailSender();
    }
    
    public boolean emailSender(){
        return eMailSender();
    }

    private List<FinalClass> informationToSend()
    {
        ArrayList<FinalClass> finalClasses = new ArrayList<>();

        List<ProposedSubject> proposedSubjects = new ArrayList();
        List<User> users = new ArrayList();
        List<UserType> userTypes = new ArrayList<>();
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

        return finalClasses;
    }

    private boolean eMailSender()
    {

        XStream x = new XStream();
        String xml = x.toXML(informationToSend());

        final String senderEmailAccount = "group2sip@gmail.com";
        final String senderEMailPassword = "letmelogin";
        final String receiveEmailAccount = "boyko.surlev@gmail.com";
        final String subject_text = "Title";
        final String message_text = "Dear Mail Crawler,\n\n" + xml;
        try
        {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); // for gmail use smtp.gmail.com
            props.put("mail.smtp.auth", "true");
            props.put("mail.debug", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");

            Session mailSession = Session.getInstance(props, new javax.mail.Authenticator()
                                              {

                                                  @Override
                                                  protected PasswordAuthentication getPasswordAuthentication()
                                                  {
                                                      return new PasswordAuthentication(senderEmailAccount, senderEMailPassword);
                                                  }
            });

            mailSession.setDebug(true); // Enable the debug mode

            Message msg = new MimeMessage(mailSession);

            //--[ Set the FROM, TO, DATE and SUBJECT fields
            msg.setFrom(new InternetAddress(senderEmailAccount));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiveEmailAccount));
            msg.setSentDate(new Date());
            msg.setSubject(subject_text);
            //--[ Create the body of the mail
            msg.setText(message_text);

            //--[ Ask the Transport class to send our mail message
            Transport.send(msg);

        }
        catch (MessagingException E)
        {
            System.out.println("Oops something has gone pearshaped!");
            System.out.println(E);
            return false;
        }
        return true;
    }

}
