import java.io.*;
import java.util.*;
import java.net.*;
import java.sql.*;

import com.google.gson.*;

public class JavaClientUtil{
  public static void main(String[] args){

    // responce element
    int price = 0;
    String bookName = "", circleName = "";

    // SQL-connection element
    final String db_url = "jdbc:postgresql://localhost:5432/testdb";
    final String db_user = "hervtea";
    final String db_passwd = "bond";
    final String js_url = "http://localhost:3000/send/";

    Connection con = null;
    // PreparedStatement ps = null;

    // JSON element
    Gson gson = new Gson();

    int pos = 1, logLast;
    String json;

    try{
      // connect to PostgreSQL
      con = DriverManager.getConnection(db_url, db_user, db_passwd);

      // commit-automation OFF
      con.setAutoCommit(false);

      // prepare SQL
      final String sql = "insert into funbook values (?,?,?)";
      
      // for test: 2 min.
      for(int i = 0; i < 60 / 5; ++i){
        while(true){

          // get & translate JSON
          System.out.println("Access to " + (js_url + pos));
          json = new ConnectionUtil().callGet(js_url + pos);
          Doujinshi fb = gson.fromJson(json, Doujinshi.class);

          if(fb == null){
            break;
          }
          else if(fb.bookName == null || fb.circleName == null){
            break;
          }

          // exec SQL
          try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, fb.bookName);
            ps.setInt(2, fb.price);
            ps.setString(3, fb.circleName);

            ps.executeUpdate();
            con.commit();
          }
          catch(Exception e){
            con.rollback();
            System.out.println("rollback..");
            throw e;
          }
          finally{
            System.out.println("done..");
          }
          ++pos;
        }
        try{
          Thread.sleep(5000);
        }
        catch(InterruptedException e){
          e.printStackTrace();
        }
      }
    }
    catch(Exception e){
      e.printStackTrace();
    }
  }
}

