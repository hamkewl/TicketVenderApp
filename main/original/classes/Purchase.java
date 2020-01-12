package original.classes;

import java.io.*;
import java.util.*;
import java.sql.Timestamp;

public class Purchase{
  String ticketID;  // primary-key
  String productID;
  String date;  // convert from SimpleDataFormat
  String telNumber;
  int quantity;
  int sumPrice;

  public Purchase(){
  }

  // when push to DB というかこれしか機能がない
  public Purchase(String ticketID, String productID, String date,
      String telNumber, int quantity, int sumPrice){

    this.ticketID = ticketID;
    this.productID = productID;
    this.date = date;
    this.telNumber = telNumber;
    this.quantity = quantity;
    this.sumPrice = sumPrice;
  }
}

