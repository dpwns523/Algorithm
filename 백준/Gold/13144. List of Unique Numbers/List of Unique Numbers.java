import java.util.*;
import java.io.*;
public class Main {
    /*
         연속한 1개 이상의 수를 뽑자
         맨 앞을 시작으로 나왔던 수가 나오면 정지
         -> 연속한 수이므로 그 길이 만큼 경우의 수 발생
         1231 -> 123까지 진행 -> 1, 12, 123 가능 경우의 수 = 길이
     */
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] visited = new int[n+1];
        long res = 0;
        int ed = 0;
        for(int i=0; i<n; i++) {
            while(ed < n && visited[arr[ed]] == 0) {
                visited[arr[ed++]]++;
            }
            res += ed - i;
            visited[arr[i]]--;
        }
        System.out.println(res);
        br.close();
    }
}