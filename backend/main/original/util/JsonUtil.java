package original.util;

import java.io.*;
import java.util.*;
import java.net.*;
import java.sql.*;

import original.classes.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonUtil{

  // Ex. js_url = "http://localhost:3000/purchase/";
  public String getJson(String js_url, int index){
    Gson gson = new Gson();
    String json = new ConnectionUtil().callGet(js_url + index);

    return json;
  }

  // Product.class related
  // convert from JSON
  public Product convertProductObject(int index){
    String json = null;
    final String url = "http://localhost:3000/purchase/";
    Gson gson = new Gson();
    Product pd;

    json = getJson(url, index);
    pd = gson.fromJson(json, Product.class);
    return pd;
  }

  // Purchase.class related
  // convert from JSON
  public Purchase convertPurchaseObject(int index){
    Gson gson = new Gson();
    String json = null;
    final String url = "http://localhost:3000/check-req/";
    Purchase pc;

    json = getJson(url, index);
    pc = gson.fromJson(json, Purchase.class);
    return pc;
  }

  // Reservation.class related
  // convert from JSON
  public Reservation convertReservationObject(String opt, int index){
    Gson gson = new Gson();
    String json = "";
    final String url = "http://localhost:3000/";

    json = getJson(url + opt + "/", index);
    return (gson.fromJson(json, Reservation.class));
  }

  // convert to JSON
  public String pushJsonOfReservation(Reservation rvobj){
    Gson gson = new Gson();
    String json = null;
    final String url = "http://localhost:3000/check-res/";

    json = gson.toJson(rvobj);
    return (new ConnectionUtil().callPost(url, json));
  }
}

