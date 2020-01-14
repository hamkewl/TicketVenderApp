package original.util;

import java.io.*;
import java.util.*;
import java.net.*;
import java.sql.*;

import original.classes.*;

public class ReservedTicketingServer extends Thread{
  public void run(){
    int pos = 1;
    Reservation rv_print;

    while(true){
      while(true){
        System.out.println("[ReservationTicketingServer/] pos: " + pos);
        if((rv_print = new JsonUtil().convertReservationObject("delete-req", pos)) != null){
          new AccessPurchaseDB().execInsert(null, null, rv_print);
          new AccessReservationDB().execDelete(rv_print.reserveID);
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
