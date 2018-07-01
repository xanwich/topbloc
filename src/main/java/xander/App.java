package xander;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.*;
import org.json.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
// import org.apache.http.entity.StringEntity.UrlEncodedFormEntity;
import org.apache.http.impl.client.HttpClientBuilder;

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
  // public static final String postURL = "http://34.239.125.159:5000/challenge";

    public static void main(String[] args) {
      System.out.println("help");

      Workbook wb1;
      Workbook wb2;
      

      try {
        wb1 = WorkbookFactory.create(new File(doc1));
        wb2 = WorkbookFactory.create(new File(doc2));
      }  catch(Exception ioe) {
        ioe.printStackTrace();
        System.exit(1);
        return;
      }
      Workbook books[] = {wb1, wb2};

      Dataset ds1 = new Dataset(rows-1);
      Dataset ds2 = new Dataset(rows-1);
      Dataset sets[] = {ds1, ds2};

      for (int i = 0; i < 2; i++) {
        Workbook wb = books[i];
        Dataset ds = sets[i];
        Sheet sheet = wb.getSheetAt(0);
        // account for header row
        for (int r = 1; r < rows; r ++) {
          Row row = sheet.getRow(r);
          ds.numSet1[r-1] = (int) row.getCell(0).getNumericCellValue();
          ds.numSet2[r-1] = (int) row.getCell(1).getNumericCellValue();
          ds.wordSet[r-1] = row.getCell(2).toString();
          // System.out.println(ds.wordSet[r-1]);
        }
      }

      int numberSetOne[] = new int[rows-1];
      int numberSetTwo[] = new int[rows-1];
      String wordSetOne[] = new String[rows-1];

      for (int i = 0; i < (rows-1); i++) {
        numberSetOne[i] = ds1.numSet1[i] * ds2.numSet1[i];
        numberSetTwo[i] = ds1.numSet2[i] / ds2.numSet2[i];
        wordSetOne[i] = ds1.wordSet[i] + " " + ds2.wordSet[i];
      }

      String json = new JSONObject()
        .put("id", "xander@xanderb.com")
        .put("numberSetOne", numberSetOne)
        .put("numberSetTwo", numberSetTwo)
        .put("wordSetOne", wordSetOne)
        .toString();
      System.out.println(json);

      try {
        String postURL = "http://ptsv2.com/t/gztfh-1530466570/post";
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(postURL);
        post.setHeader("Content-type", "application/json");
        post.setHeader("Accept", "application/json");
        post.setEntity(new StringEntity(json));
        HttpResponse response = httpClient.execute(post);
        System.out.println(response.toString());
      } catch(Exception ioe) {
        ioe.printStackTrace();
        System.exit(1);
      }

    }
}
