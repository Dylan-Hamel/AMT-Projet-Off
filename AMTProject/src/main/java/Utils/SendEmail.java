package Utils;

import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;


public class SendEmail {

    // private static String smtp = "smtp.heig-vd.ch";
    private static String send = "amt-labo-2018-@heig-vd.ch";

    public SendEmail (String userAdresseEmail, String title, String msgBody) {

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", "smtp.heig-vd.ch");
        properties.put("mail.smtp.port", 25);

        // Get the default Session object.
        javax.mail.Session session = javax.mail.Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(send));

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
        }

    }
    
}
