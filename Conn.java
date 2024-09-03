package electricity.billing.system;

import java.sql.*;

public class Conn {

    //connection interface which is inside sql package
    Connection c;
    Statement s;
    Conn() {
        try {
            c = DriverManager.getConnection("jdbc:mysql:///electricitybillingsystem", "root", "12345678");
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}