package org.classes;

import java.io.*;
import java.util.*;

public class Product{
  public String productID; // primary-key
  public String productName;
  public String description;
  public int price;

  // response from 'SELECT * from Product(master) where ..'
  public Product(String productID, String productName, String description, int price){
    this.productID = productID;
    this.productName = productName;
    this.description = description;
    this.price = price;
  }
}

