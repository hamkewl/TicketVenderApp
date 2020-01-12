import java.io.*;
import java.util.*;

import original.classes.*;
import original.util.*;

public class TestAPM{
  public static void main(String[] args){
    Product pd = new AccessProductMasterDB().execSelect("200003");
    System.out.println(pd.price);
    new AccessPurchaseDB().execInsert(pd);
  }
}

