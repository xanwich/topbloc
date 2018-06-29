package xander;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.*;

/**
 * Hello world!
 *
 */
public class App 
{

  public static final String doc1 = "./Data1.xlsx";
  public static final String doc2 = "./Data2.xlsx";
  

    public static void main(String[] args)
    {
      System.out.println("help");
      try {
        Workbook wb1 = WorkbookFactory.create(new File(doc1));
        Workbook wb2 = WorkbookFactory.create(new File(doc2));
        System.out.println("workbook1 has " + wb1.getNumberOfSheets() + " Sheets");
      } catch(Exception ioe) {
        ioe.printStackTrace();
      }
    }
}
