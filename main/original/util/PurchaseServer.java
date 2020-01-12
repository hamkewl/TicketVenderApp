package original.util;

import java.io.*;
import java.util.*;
import java.net.*;
import java.sql.*;

import original.classes.*;

public class NormalPurchaseServer extends Thread{
  public void run(){
    int pos = 1;
    Product pd_search, pd;

    while(true){
      while(true){
        System.out.println("pos: " + pos);
        if((pd_search = new JsonUtil().convertProductObject(pos)) != null){
          pd = new AccessProductMasterDB().execSelect(pd_search.productID);
          new AccessPurchaseDB().execInsert(pd, null, null);
          ++pos;
        }
        else break;
      }
      try{
        Thread.sleep(6000);
      }
      catch(Exception e){
        e.printStackTrace();
      }
    }
  }
}
