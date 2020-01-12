package original.util;

import java.io.*;
import java.util.*;
import java.net.*;
import java.sql.*;

import original.classes.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonUtil{

  // private method
  // Ex. js_url = "http://localhost:3000/purchase/";
  public String getJson(String js_url, int index){
    Gson gson = new Gson();
    String json = new ConnectionUtil().callGet(js_url + index);

    return json;
  }

  // Product.class related
  // convert from JSON
  public Product convertProductObject(int index){
    Gson gson = new Gson();
    String json = null;
    final String url = "http://localhost:3000/purchase/";

    json = this.getJson(url, index);
    return (gson.fromJson(json, Product.class));
  }


  // Purchase.class related
  // convert from JSON
  public Purchase convertPurchaseObject(int index){
    Gson gson = new Gson();
    String json = null;
    final String url = "http://localhost:3000/check-req/";

    json = this.getJson(url, index);
    return (gson.fromJson(json, Purchase.class));
  }

  /*
  // convert to JSON
  public String pushJsonOfPurchase(Purchase pcobj){
    Gson gson = new Gson();
    String json = null;
    final String url = "http://localhost:3000/check-res/";

    json = gson.toJson(pcobj);
    return (ConnectionUtil().callPost(url, json));
  }
  */


  // Reservation.class related
  // convert from JSON
  public Reservation convertReservationObject(String opt,int index){
    Gson gson = new Gson();
    String json = "";
    final String url = "http://localhost:3000/";

    json = this.getJson(url + opt + "/", index);
    return (gson.fromJson(json, Reservation.class));
  }

  // convert to JSON
  public String pushJsonOfReservation(Reservation rvobj){
    Gson gson = new Gson();
    String json = null;
    final String url = "http://localhost:3000/check-res/";

    json = gson.toJson(rvobj);
    return (ConnectionUtil().callPost(url, json));
  }

}
