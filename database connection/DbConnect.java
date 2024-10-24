package db;
import java.sql.*;
import javax.swing.JOptionPane;
public class DbConnect {
    public static Connection c;
    public static Statement s;
    static{
        try{
            c=DriverManager.getConnection("jdbc:mysql://localhost:3306/mm1"+"?useSSL=false", "root", "trisha2005");
            s=c.createStatement();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
