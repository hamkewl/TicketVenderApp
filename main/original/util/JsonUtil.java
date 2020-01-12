package original.util;

import java.io.*;
import java.util.*;
import java.net.*;
import java.sql.*;

import original.classes.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonUtil{

	// Normal purchase (pid: productID)
  public String convertFromJson(String js_url, int index, Object obj){

    // JSON related
    // Ex. js_url = "http://localhost:3000/purchase/";
    String json = null;
    Gson gson = new Gson();

    json = ConnectionUtil().callGet(js_url + index);
    if(obj instanceof Product){
      System.out.println("Product");
      Product response = gson.fromJson(json, Product.class);
      return response;
    }
    else if(obj instanceof Purchase){
      System.out.println("Purchase");
      Purchase response = gson.fromJson(json, Purchase.class);
      return response;
    }
    else if(obj instanceof Reservation){
      System.out.println("Reservation");
      Reservation response = gson.fromJson(json, Reservation.class);
      return response;
    }

    // return response;
  }

  public String convertToJson(String js_url, ArrayList lst){

    // JSON related
    // Ex. js_url = "http://localhost:3000/purchase/";
    String json = null;
    Gson gson = new Gson();

    try{
      for(int i = 0; i < lst.size(), ++i){
        json = gson.toJson(lst.get(i));
        result = new ConnectionUtil.callGet(js_url, json);
        System.out.println("sent..");
      }
    }
    catch(Exception e){
      System.out.println("Access error..");
      e.printStackTrace();
    }
  }
}

