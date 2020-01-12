package original.util;

import java.io.*;
import java.util.*;
import java.net.*;
import java.sql.*;

import original.classes.*;

public class AccessReservationDB{

	// Access to PostgreSQL(Port:5432) via JDBC
  public Reservation execSelect(String reserveID, String telNumber){

    // DB related
    final String db_url = "jdbc:postgresql://localhost:5432/testdb";
    final String user = "hervtea";
    final String password = "bond";
    final String sql = "select * from Reserve where (" +
                       "reserveid = ? and telnumber = ?)"; 
    
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    // duplicate Product object
    private String reserveID = null;  // primary-key
    private String productID = null;
    private Timestamp date = null;
    private String telNumber = null;  // confirm-key
    private int quantity = 1;
    private int sumPrice = 1;

    try{
      this.reserveid = reserveid;
      this.telNumber = telNumber;

      // Load JDBC Driver
      Class.forName("org.postgresql.Driver");

      // connect to PostgreSQL
      con = DriverManager.getConnection(db_url, user, password);

      // commit-automation OFF
      con.setAutoCommit(false);

      // exec SQL
      ps = con.prepareStatement(sql);
      ps.setString(1, reserveID);
      ps.setString(2, telNumber);
      rs = ps.executeQuery();

      // primary-key Queryなので多分rs内のデータは1つしかないと思われるが一応
      while(rs.next()){
        this.productID = rs.getString("productid");
        this.date = rs.getTimestamp("date");
        this.quantity = rs.getInt("quantity");
        this.sumPrice = rs.getInt("price");
      }
    }
    catch(Exception e){
      e.printStackTrace();
    }
    finally{
      try{
        if(con != null)  con.close();
        if(ps != null)  ps.close();
        if(rs != null)  rs.close();
      }
      catch(Exception e){
        e.printStackTrace();
      }
    }

    return (new Reservation(this.reserveID, this.productID, this.date,
                          this.telNumber, this.quantity, this.sumPrice));
  }

  public void execDelete(String reserveID){

    // DB related
    final String db_url = "jdbc:postgresql://localhost:5432/testdb";
    final String user = "hervtea";
    final String password = "bond";
    final String sql = "delete from Reserve where (reserveid = ?)"; 
    
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try{

      // Load JDBC Driver
      Class.forName("org.postgresql.Driver");

      // connect to PostgreSQL
      con = DriverManager.getConnection(db_url, user, password);

      // commit-automation OFF
      con.setAutoCommit(false);

      // exec SQL
      ps = con.prepareStatement(sql);
      ps.setString(1, reserveID);
      rs = ps.executeUpdate();
      con.commit();
    }
    catch(Exception e){
      con.rollback();
      System.out.println("rollback");
      throw e;
    }
    finally{
      try{
        if(con != null)  con.close();
        if(ps != null)  ps.close();
        if(rs != null)  rs.close();
      }
      catch(Exception e){
        e.printStackTrace();
      }
    }
  }
}
