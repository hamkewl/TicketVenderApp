import java.io.*;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ParseJson{
  public static void main(String[] args){

    FileReader fr = null;
    try{
      fr = new FileReader("./testfb.json");
      Gson gson = new Gson();

      // parse element
      if(true){
        Doujinshi response = gson.fromJson(fr, Doujinshi.class);
        System.out.print(response.bookName + ", ");
        System.out.print(response.price + ", ");
        System.out.println(response.circleName);
      }

      /*
      // parse list
      if(true){
        List<Data> fruitList = gson.fromJson(fr, new TypeToken<List>(){}.getType());
        System.out.println(fruitList.size());
        for(int i = 0; i < fruitList.size(); ++i){
          Data fruit = fruitList.get(i);
          System.out.print(fruit.getName() + ", ");
          System.out.println(fruit.getColor());
        }
      }
      */
    }
    catch(Exception e){
      e.printStackTrace();
    }
    finally{
      try{
        if(fr != null){
          fr.close();
        }
      }
      catch(Exception e){
        e.printStackTrace();
      }
    }
  }
}

