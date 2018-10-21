package Database;


import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Stateless
public class ProjectDAO {

    private final static String TABLE_NAME = "messages";

    @Resource(lookup = "jdbc/amtProject")
    private DataSource database;



}