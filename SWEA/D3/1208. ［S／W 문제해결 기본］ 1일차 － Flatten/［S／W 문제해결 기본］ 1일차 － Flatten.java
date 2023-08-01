import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    public final static int COL = 100;
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int test_case = 1; test_case <= 10; test_case++)
        {
            int n = Integer.parseInt(br.readLine());
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for(int i=0; i<n; i++) {
                Arrays.sort(arr);
                if(Arrays.stream(arr).allMatch(a -> a==arr[0])) break;
                arr[0]++;
                arr[COL-1]--;

            }
            Arrays.sort(arr);
            System.out.println("#"+test_case+" "+(arr[COL-1] - arr[0]));
        }
    }
}