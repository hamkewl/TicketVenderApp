import java.util.Calendar;
import java.text.SimpleDateFormat;
 
public class Main{
  public static void main(String[] args){
    
    // Calendarクラスのオブジェクトを生成する
    Calendar cl = Calendar.getInstance();
    
    // SimpleDateFormatクラスでフォーマットパターンを設定する
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    System.out.println(sdf.format(cl.getTime()));
  }
}

