
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;




/**
 *
 * @author Adm
 */
public class conectaDAO {
    
    public Connection connectDB() throws ClassNotFoundException{
        Connection conn = null;
        
        try {
            String url = "jdbc:mysql://localhost/uc11";
            String user = "root";
            String password = "123456";
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            
        } catch (SQLException | ClassNotFoundException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }
    
}
