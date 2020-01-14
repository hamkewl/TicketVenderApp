import java.io.*;
import java.util.*;
import java.net.*;
import java.sql.*;

public class AccessTestDB2{

	// Access to PostgreSQL(Port:5432) via JDBC
  public static void main(String[] args) {
    Connection con = null;
    Statement stmt = null;
    ResultSet rset = null;

    final String url = "jdbc:postgresql://localhost:5432/testdb";
    final String user = "hervtea";
    final String password = "bond";

    try{
      // connect to PostgreSQL
      con = DriverManager.getConnection(url, user, password);

      // commit-automation OFF
      con.setAutoCommit(false);

      // exec SQL
      final String sql = "insert into funbook values (?,?,?)";
      try(PreparedStatement ps = con.prepareStatement(sql)){
        ps.setString(1, "Yummy!!");
        ps.setInt(2, 500);
        ps.setString(3, "Nanairo Batake");

        ps.executeUpdate();
        con.commit();
      }
      catch(Exception e){
        con.rollback();
        System.out.println("rollback..");
        throw e;
      }
    }
    catch(Exception e){
      e.printStackTrace();
    }
    finally{
      System.out.println("done..");
    }
  }
}

