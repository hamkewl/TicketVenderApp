package original.classes;

import java.io.*;
import java.util.*;
import java.sql.Timestamp;

public class Reservation{
  public String reserveID;  // primary-key
  public String productID;
  public String date; // convert from SimpleDateFormat()
  public String telNumber;  // NOT primary-key but instead of confirm-key
  public int quantity;
  public int sumPrice;

  public Reservation(){
  }

  // response 'SELECT'
  public Reservation(String reserveID, String productID, String date,
    String telNumber, int quantity, int sumPrice){
    this.reserveID = reserveID;
    this.productID = productID;
    this.date = date;
    this.telNumber = telNumber;
    this.quantity = quantity;
    this.sumPrice = sumPrice;
  }

  // send 'SELECT' to DB
  public Reservation(String reserveID, String telNumber){
    this.reserveID = reserveID;
    this.telNumber = telNumber;
  }
}

