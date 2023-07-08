import java.util.*;
import java.io.*;
public class Main {
    /*
         0에 가까운 합을 만들자, 같은 용액으로도 가능하다
         1. 정렬 -> st = 0, ed = n-1
         2. |arr[ed] + arr[st]| < min -> update
     */
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(arr);
        int[] answer = new int[2];
        int min = Integer.MAX_VALUE, st = 0, ed = n-1;
        while(st < ed && ed < n) {
            if(min > Math.abs(arr[ed] + arr[st])) {
                min = Math.abs(arr[ed] + arr[st]);
                answer[0] = arr[st];
                answer[1] = arr[ed];
            }
            if(arr[ed] + arr[st] > 0) ed--;
            else st++;
        }

        Arrays.stream(answer).forEach(a -> System.out.print(a+" "));
        br.close();
    }
}