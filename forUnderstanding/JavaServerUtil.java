import java.io.*;
import java.util.*;
import java.net.*;
import java.sql.*;

import com.google.gson.*;

public class JavaServerUtil{
  public static void main(String[] args){

    // responce element
    int price = 0;
    String bookName = "", circleName = "";

    // SQL-connection element
    String db_url = "jdbc:postgresql://localhost:5432/testdb";
    String db_user = "hervtea";
    String db_passwd = "bond";
    String js_url = "http://localhost:3000/receive";

    Connection con = null;
    Statement stmt = null;
    ResultSet rset = null;

    // JSON element
    Gson gson = new Gson();
    String json, result;
    ArrayList<Doujinshi> fblst = new ArrayList<Doujinshi>();

    try{
      // connect to PostgreSQL
      con = DriverManager.getConnection(db_url, db_user, db_passwd);

      // commit-automation OFF
      con.setAutoCommit(false);

      // exec SQL
      stmt = con.createStatement();
      String sql = "select * from funbook";
      rset = stmt.executeQuery(sql);
      while(rset.next()){
        bookName = rset.getString("bookname");
        price = rset.getInt("price");
        circleName = rset.getString("circlename");
        fblst.add(new Doujinshi(bookName, price, circleName));
      }
    }
    catch(Exception e){
      e.printStackTrace();
    }
    finally{
      try{
        if(rset != null)  rset.close();
        if(stmt != null)  stmt.close();
        if(con != null) con.close();
      }
      catch(Exception e){
        e.printStackTrace();
      }
    }

    // convert to JSON and send
    try{
      for(int i = 0; i < fblst.size(); ++i){
        json = gson.toJson(fblst.get(i));
        result = new ConnectionUtil().callPost(js_url, json);
        System.out.println("Accomplished!!");
      }
    }
    catch(Exception e){
      System.out.println("Access Error..");
      e.printStackTrace();
    }
  }
}

