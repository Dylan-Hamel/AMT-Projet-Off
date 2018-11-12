package Utils;

import javax.ejb.Local;

@Local
public interface SendEmailInterface {

    void sendEmail (String userAdresseEmail, String title, String msgBody);

}
