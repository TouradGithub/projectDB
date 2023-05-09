import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static  String DB_URL = "jdbc:mysql://localhost:3306/PROJECTDB";
    static final String USER = "root";
    static final String PASS = "12345678@";
    public Statement stmt = null;
    public Connection conn = null;

    public static void setDbUrl(String dbUrl) {
        DB_URL = dbUrl;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    public static String getDbUrl() {
        return DB_URL;
    }

    public Connection getConn() {
        return conn;
    }

    public Statement getStmt() {
        return stmt;
    }



    public  ConnectionDB() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);

        System.out.println("Connecting to database...");
       this.conn = DriverManager.getConnection(this.DB_URL,this.USER,this.PASS);
    }




}
