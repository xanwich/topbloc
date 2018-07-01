package xander;

public class Dataset
{
  public int[] numSet1;
  public int[] numSet2;
  public String[] wordSet;

  public Dataset(int len) {
    numSet1 = new int[len];
    numSet2 = new int[len];
    wordSet = new String[len];
  }
}