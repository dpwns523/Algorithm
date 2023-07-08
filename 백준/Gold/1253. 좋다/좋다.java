import java.util.*;
import java.io.*;
public class Main {
    /*
         오름차순 정렬을 하면 앞에 있는 숫자로 표현될 수 있는지 체크하면 된다
         st = 맨 처음
         ed = 가장 큰 원소 -> 음수가 포함되어있기 때문
     */
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(arr);
        int res = 0;
        for(int i=0; i<n; i++) {
            int st=0, ed=n-1;
            while(st<ed) {
                if(st == i) st++;
                else if(ed == i) ed--;
                else {
                    if (arr[st] + arr[ed] == arr[i]) {
                        res++;
                        break;
                    } else if (arr[st] + arr[ed] < arr[i]) st++;
                    else ed--;
                }
            }
        }
        System.out.println(res);
        br.close();
    }
}