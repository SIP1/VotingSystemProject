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
    
    public static boolean send(String email, String finalClasses){
       final String senderEmailAccount = "group2sip@gmail.com";
        final String senderEMailPassword = "letmelogin";
        final String receiverEmailAccount = email;
        final String subject_text = "List of final classes";
        final String message_text = "Dear Administrator,\n\n" + finalClasses;
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
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmailAccount));
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
