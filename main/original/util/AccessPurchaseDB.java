package original.util;

import java.io.*;
import java.util.*;
import java.net.*;
import java.sql.*;

import original.classes.*;

public class AccessPurchaseDB{

	// Access to PostgreSQL(Port:5432) via JDBC
  public void execInsert(Product pdobj, Purchase pcobj, Reservation rvobj){

    Connection con = null;
    Statement stmt = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    final String url = "jdbc:postgresql://localhost:5432/testdb";
    final String user = "hervtea";
    final String password = "bond";
    final String sql = "insert into Purchase values (?,?,?,?,?,?)";

    // duplicate Product object
    private String productID = null;  // primary-key
    private String productName = null;
    private String description = null;
    int price = 0;

    try{
      // Load JDBC Driver
      Class.forName("org.postgresql.Driver");

      // connect to PostgreSQL
      con = DriverManager.getConnection(url, user, password);

      // commit-automation OFF
      con.setAutoCommit(false);

      // exec SQL
      stmt = con.createStatement();
      rs = stmt.executeQuery("select ticketid from purchase");

      // input is pdobj
      if(pdobj != null && pcobj == null && rvobj == null){
        try{
          String lastId = null;
          while(rs.next()){
            lastId = String.valueOf(Integer.parseInt(rs.getString("ticketid")) + 1);
          }
          ps = con.prepareStatement(sql);
          ps.setString(1, lastId);
          ps.setString(2, pdobj.productID);
          ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
          ps.setString(4, null);
          ps.setInt(5, 1);
          ps.setInt(6, pdobj.price * 1);
          ps.executeUpdate();
          con.commit();
        }
        catch(Exception e1){
          con.rollback();
          System.out.println("rollback");
          throw e1;
        }
      }

      // input is pcobj
      else if(pdobj == null && pcobj != null && rvobj == null){
        try{
          String lastId = null;
          while(rs.next()){
            lastId = String.valueOf(Integer.parseInt(rs.getString("ticketid")) + 1);
          }
          ps = con.prepareStatement(sql);
          ps.setString(1, lastId);
          ps.setString(2, pcobj.productID);
          ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
          ps.setString(4, pcobj.telNumber);
          ps.setInt(5, 1);
          ps.setInt(6, pcobj.price);
          ps.executeUpdate();
          con.commit();
        }
        catch(Exception e2){
          con.rollback();
          System.out.println("rollback");
          throw e2;
        }
      }

      // input is rvobj
      else if(pdobj == null && pcobj == null && rvobj != null){
        try{
          String lastId = null;
          while(rs.next()){
            lastId = String.valueOf(Integer.parseInt(rs.getString("ticketid")) + 1);
          }
          ps = con.prepareStatement(sql);
          ps.setString(1, lastId);
          ps.setString(2, rvobj.productID);
          ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
          ps.setString(4, rvobj.telNumber);
          ps.setInt(5, 1);
          ps.setInt(6, rvobj.price);
          ps.executeUpdate();
          con.commit();
        }
        catch(Exception e3){
          con.rollback();
          System.out.println("rollback");
          throw e3;
        }
      }

      // in case of other
      else{
        throws IllegalArgumentException("Only one argument must be null");
      }
    }
    catch(Exception e){
      e.printStackTrace();
    }

    try{
      if(con != null)  con.close();
      if(ps != null)  ps.close();
      if(rs != null)  rs.close();
    }
    catch(Exception e){
      e.printStackTrace();
    }
    finally{
      System.out.println("done");
    }
  }
}
