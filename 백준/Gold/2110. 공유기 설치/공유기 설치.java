import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        int ans = 0, low = 0, high = 1000000000;    // 최소, 최대를 지정 arr엔 어떤 값이 들어갈 지 모르므로 주어진 범위 적용
        while(low <= high) {
            int mid = (low + high) / 2;
            if(countInternet(arr, mid, c)) {
                ans = mid;
                low = mid + 1;
            }
            else high = mid - 1;
        }
        System.out.println(ans);
        br.close();
    }
    public static boolean countInternet(int[] arr, int dis, int c) {
        int cnt=1, last = arr[0];
        for(int i=1; i<arr.length; i++) {
            if(arr[i] - last < dis) continue;
            last = arr[i];
            cnt++;
        }
        return cnt >= c;
    }
}