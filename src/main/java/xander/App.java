package xander;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.*;

/**
 * Hello world!
 *
 */
public class App 
{

  public static final String doc1 = "./Data1.xlsx";
  public static final String doc2 = "./Data2.xlsx";
  public static final int cols = 3;
  public static final int rows = 5;
  

    public static void main(String[] args) {
      System.out.println("help");

      try {
        Workbook wb1 = WorkbookFactory.create(new File(doc1));
        Workbook wb2 = WorkbookFactory.create(new File(doc2));
        Workbook books[] = {wb1, wb2};

        Dataset ds1 = new Dataset(rows-1);
        Dataset ds2 = new Dataset(rows-1);
        Dataset sets[] = {ds1, ds2};

        for (int i = 0; i < 2; i++) {
          Workbook wb = books[i];
          Dataset ds = sets[i];
          Sheet sheet = wb.getSheetAt(0);
          // account for header
          for (int r = 1; r < rows; r ++) {
            Row row = sheet.getRow(r);
            ds.numSet1[r-1] = (int) row.getCell(0).getNumericCellValue();
            ds.numSet2[r-1] = (int) row.getCell(1).getNumericCellValue();
            ds.wordSet[r-1] = row.getCell(2).toString();
            // System.out.println(ds.wordSet[r-1]);
          }
        }

        int nums1[] = new int[rows-1];
        int nums2[] = new int[rows-1];
        String words[] = new String[rows-1];

        for (int i = 0; i < (rows-1); i++) {
          nums1[i] = ds1.numSet1[i] * ds2.numSet1[i];
          nums2[i] = ds1.numSet2[i] / ds2.numSet2[i];
          words[i] = ds1.wordSet[i] + " " + ds2.wordSet[i];
          System.out.println(nums1[i]);
          System.out.println(nums2[i]);
          System.out.println(words[i]);
        }

      } catch(Exception ioe) {
        ioe.printStackTrace();
      }
    }
}
