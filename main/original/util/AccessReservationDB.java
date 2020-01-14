package original.util;

import java.io.*;
import java.util.*;
import java.net.*;
import java.sql.*;
import java.text.SimpleDateFormat;

import original.classes.*;

public class AccessReservationDB{

	// Access to PostgreSQL(Port:5432) via JDBC
  public Reservation execSelect(Reservation rvobj){

    // DB related
    final String db_url = "jdbc:postgresql://localhost:5432/testdb";
    final String user = "hervtea";
    final String password = "bond";
    String sql = "select * from Reservation where ("
                  + "reserveid = ? and telnumber = ?)"; 
    
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    // duplicate Product object
    String reserveID = null;  // primary-key
    String productID = null;
    Timestamp date = null;
    String telNumber = null;  // confirm-key
    int quantity = 1;
    int sumPrice = 1;

    try{
      reserveID = rvobj.reserveID;
      telNumber = rvobj.telNumber;
      
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

      while(rs.next()){
        productID = rs.getString("productid");
        date = rs.getTimestamp("date");
        quantity = rs.getInt("quantity");
        sumPrice = rs.getInt("sumprice");
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

    String datestr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(date);
    System.out.println(datestr);

    // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    // String datestr = sdf.format(date);
    return (new Reservation(reserveID, productID, datestr, 
                                telNumber, quantity, sumPrice));
  }

  public void execDelete(String reserveID){

    // DB related
    final String db_url = "jdbc:postgresql://localhost:5432/testdb";
    final String user = "hervtea";
    final String password = "bond";
    final String sql = "delete from Reservation where (reserveid = ?)"; 
    
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
      ps.executeUpdate();
      con.commit();
    }
    catch(Exception e){
      try{
        con.rollback();
        System.out.println("rollback");
        e.printStackTrace();
      }
      catch(Exception e4){
        e4.printStackTrace();
      }
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
