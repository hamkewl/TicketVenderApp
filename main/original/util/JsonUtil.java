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
    final String url = "http://localhost:3000/purchase/";

    String json = this.getJson(url, index);
    return (gson.fromJson(json, Product.class));
  }

  // convert to JSON
  public void pushJsonOfProduct(Product pdobj){
    Gson gson = new Gson();
    String json = gson.toJson(pdobj);
  }

}

