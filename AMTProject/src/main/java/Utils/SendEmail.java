package Utils;

import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.*;


public class SendEmail {

    public SendEmail (String userAdresseEmail, String password) {

        // Sender's email ID needs to be mentioned
        String from = "amt-labo-2018-@heig-vd.ch";

        // Assuming you are sending email from localhost
        String host = "smtp.heig-vd.ch";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("smtp.heig-vd.ch", host);

        // Get the default Session object.
        javax.mail.Session session = javax.mail.Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userAdresseEmail));

            // Set Subject: header field
            message.setSubject("[AMT-Project-2018] - New Password");

            // Now set the actual message
            message.setText("New Password : " + password);

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
    
}
