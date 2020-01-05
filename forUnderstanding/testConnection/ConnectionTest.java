import java.io.*;
import java.util.*;
import java.net.*;
import static java.lang.System.*;
import com.google.gson.*;

public class ConnectionTest{
  public static void main(String[] args) throws Exception{
    int n;
    String url = "http://localhost:3000/receive";
    String json;
    String result;
    Scanner sc = new Scanner(System.in);
    ArrayList<String> elementArray = new ArrayList<String>();
    Gson gson = new Gson();

    // input
    int price;
    String name, circle;

    sc.useDelimiter("\n");
    out.println("Funbook -");
    out.print("name: ");    name = (sc.nextLine()).replace(" ", "+");
    out.print("price: ");   price = Integer.parseInt((sc.nextLine()).replace("\n",     " "));
    out.print("circle: ");  circle = (sc.nextLine()).replace(" ", "+");
    Doujinshi doujinshi = new Doujinshi(name, price, circle);
    
    // convert JSON
    json = gson.toJson(doujinshi);
    System.out.println(json);

    try{
      result = new ConnectionUtil().callPost(url, json);
      out.println(result);
    }
    catch(Exception e){
      out.println("Access Error..");
    }
  }
}

