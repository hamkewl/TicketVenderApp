import java.io.*;
import java.util.*;
import java.net.*;
import java.sql.*;

import static java.lang.System.*;

public class InsertReservationDB{
  public static void main(String[] args){

    final String url = "jdbc:postgresql://localhost:5432/testdb";
    final String user = "hervtea";
    final String passwd = "bond";
    final String sql = "insert into reservation values (?,?,?,?,?,?)";

    String reserveID, productID, telNumber;
    int sumPrice;

    Scanner sc = new Scanner(System.in);
    out.println("## INSERT to Reservation Data Base PROGRAM ##");
    out.print("reserveID (must checking Purchase DB): ");
    reserveID = sc.next();
    out.print("productID: ");
    productID = sc.next();
    out.print("telNumber: ");
    telNumber = sc.next();
    out.print("sumPrice: ");
    sumPrice = sc.nextInt();

    try{
      Connection con = DriverManager.getConnection(url, user, passwd);
      con.setAutoCommit(false);
      
      try{
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, reserveID);
        ps.setString(2, productID);
        ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
        ps.setString(4, telNumber);
        ps.setInt(5, 1);
        ps.setInt(6, sumPrice);

        ps.executeUpdate();
        con.commit();
      }
      catch(Exception e){
        con.rollback();
        System.out.println("rollback");
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

