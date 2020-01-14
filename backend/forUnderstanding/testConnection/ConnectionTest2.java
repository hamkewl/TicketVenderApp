import java.io.*;
import java.util.*;
import java.net.*;
import static java.lang.System.*;
import com.google.gson.*;

public class ConnectionTest2{
  public static void main(String[] args) throws Exception{
    int n;
    String url = "http://localhost:3000/send";
    String json;
    String result;
    Scanner sc = new Scanner(System.in);
    ArrayList<String> elementArray = new ArrayList<String>();
    Gson gson = new Gson();

    // input
    String name;

    sc.useDelimiter("\n");
    out.println("Funbook (search) -");
    out.print("name: ");    name = sc.nextLine().replace(" ", "%20");
    // out.print("circle: ");  circle = sc.nextLine().replace(" ", "%20");
    
    // convert HTTP-Query
    url += ("?bookName=" + name);
    // System.out.println(json);

    try{
      result = new ConnectionUtil().callGet(url);
      out.println(result);
    }
    catch(Exception e){
      out.println("Access Error..");
    }
  }
}

