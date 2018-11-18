package Utils;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.sql.DataSource;


@Stateless
public class SendEmail implements SendEmailInterface {

    @Resource(lookup = "java:/smtp-server")
    private Session session;

    // private static String smtp = "smtp.heig-vd.ch";
    // private static String send = "amt-labo-2018-@heig-vd.ch";

    public boolean sendEmail (String userAdresseEmail, String title, String msgBody) {

        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            // message.setFrom(new InternetAddress(send));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userAdresseEmail));

            // Set Subject: header field
            message.setSubject(title);

            // Now set the actual message
            message.setText(msgBody);

            message.setSentDate(new Date());

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return false;
        }
        return true;
    }
}
