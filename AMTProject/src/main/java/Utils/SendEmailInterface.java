package Utils;

import javax.ejb.Local;

@Local
public interface SendEmailInterface {

    boolean sendEmail (String userAdresseEmail, String title, String msgBody);

}
