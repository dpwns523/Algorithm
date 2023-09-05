import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int[] arr = new int[1_000_000];
        for (int test_case = 0; test_case < T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr, 0, n);
            int left = 0, right = n -1, cnt = 0, diff = Integer.MAX_VALUE;
            while(left < right) {
                int sum = arr[left] + arr[right];
                int next = Math.abs(sum - k);

                if(next < diff) {   // 더 작은 차이이면 조합의 수를 다시 센다
                    diff = next;
                    cnt = 1;
                }
                else if(next == diff) cnt++;

                if(sum == k) {    // left, right 중 하나만 움직여도 어차피 k보다 크거나 작은 부분으로 이동하게 됨
                    left++;
                    right--;
                }
                else {
                    left = sum < k ? left + 1 : left;
                    right = sum > k ? right - 1 : right;
                }
            }
            sb.append(cnt).append("\n");
        }
        System.out.print(sb);
        br.close();
    }
}