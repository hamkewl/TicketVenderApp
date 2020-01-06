import java.io.*;
import java.util.*;
import java.net.*;
import java.sql.*;

public class AccessTestDB{

	// Access to PostgreSQL(Port:5432) via JDBC
  public static void main(String[] args) {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rset = null;

    String url = "jdbc:postgresql://localhost:5432/testdb";
    String user = "hervtea";
    String password = "bond";

    try{
      // connect to PostgreSQL
      conn = DriverManager.getConnection(url, user, password);

      // commit-automation OFF
      conn.setAutoCommit(false);

      // exec SQL
      stmt = conn.createStatement();
      String sql = "select * from funbook where (circlename = 'Midori*Creates')";
      rset = stmt.executeQuery(sql);

      while(rset.next()){
        System.out.print(rset.getString("bookname"));
        System.out.print("/ " + rset.getString("price"));
        System.out.println("/ " + rset.getString("circlename"));
      }
    }
    catch(Exception e){
      e.printStackTrace();
    }
    finally{
      try{
        if(rset != null)  rset.close();
        if(stmt != null)  stmt.close();
        if(conn != null)  conn.close();
      }
      catch(SQLException e){
        e.printStackTrace();
      }
    }
  }
}

