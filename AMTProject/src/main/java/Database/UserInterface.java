package Database;

import Model.User;
import java.util.List;
import javax.ejb.Local;

@Local
public interface UserInterface {

    /*
     *
     */
    public boolean findIfUserExist(String email, String password);

    /*
     *
     */
    public boolean checkIfUserExist(String  email);

    /*
     *
     */
    public boolean insertUser (String firstname, String lastname, String email, String password, String address,
                               String zip, String country );

    /*
     *
     */
    public List<String> getAllUsersEmailAddress ();

    /*
     *
     */
    public boolean updateUserPassword(String email, String password);

    /*
     *
     */
    public User getUserWithID(String email);

    /*
     *
     */
    public boolean update(String firstname, String lastname, String email, String password, String address, String zip, String country) ;
    /*
     *
     */
    public List<User> getAllUsersEmailAndStatus () ;

    /*
     *
     */
    public boolean checkIfUserHaveResetedPassword (String email) ;

    /*
     * This function is used when a user has set his new password after a reset
     */
    public boolean setUserResetTo0 (String email) ;

    /*
     * This function is used when a user "Forgot Username / Password?" feature
     * Set reset to 1, so that user will change his password
     */
    public boolean setUserResetTo1 (String email) ;


    /*
     *
     */
    public boolean disableUser (String email);

    /*
     *
     */

    public boolean enableUser (String email);

    /*
     *
     */
    public boolean deletUser (String email);


}
