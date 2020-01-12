package original.util;

import java.io.*;
import java.util.*;
import java.net.*;
import java.sql.*;

import original.classes.*;

public class AccessProductMasterDB{

	// Access to PostgreSQL(Port:5432) via JDBC
  public Product execSelect(String pid){  // pid: productID

    // DB related
    final String db_url = "jdbc:postgresql://localhost:5432/testdb";
    final String user = "hervtea";
    final String password = "bond";
    final String sql = "select * from Product where (Productid = ?)";
    
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    // duplicate Product object
    String productID = null;  // primary-key
    String productName = null;
    String description = null;
    int price = 0;

    try{

      // Load JDBC Driver
      Class.forName("org.postgresql.Driver");

      // connect to PostgreSQL
      con = DriverManager.getConnection(db_url, user, password);

      // commit-automation OFF
      con.setAutoCommit(false);

      // exec SQL
      ps = con.prepareStatement(sql);
      ps.setString(1, pid);
      rs = ps.executeQuery();

      // primary-key Queryなので多分rs内のデータは1つしかないと思われるが一応
      while(rs.next()){

        // System.out.print(rs.getString("productID"));
        productID = rs.getString("productID");

        // System.out.print("/ " + rs.getString("productName"));
        productName = rs.getString("productName");

        // System.out.print("/ " + rs.getString("description"));
        description = rs.getString("description");

        // System.out.println("/ " + rs.getString("price"));
        price = rs.getInt("price");
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

    return (new Product(productID, productName, description, price));
  }
}

