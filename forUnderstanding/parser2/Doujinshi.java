import java.io.*;
import java.util.*;

public class Doujinshi{

  // if 'Not found' then (null, 0, null)
  public String bookName;
  public int price;
  public String circleName;

  public Doujinshi(String bookName, int price, String circleName){
    this.bookName = bookName;
    this.price = price;
    this.circleName = circleName;
  }
}

