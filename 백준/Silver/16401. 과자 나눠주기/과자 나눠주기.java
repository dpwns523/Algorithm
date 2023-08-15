import java.io.*;
import java.util.*;

public class Main {
    public static int[] snacks;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken()), n = Integer.parseInt(st.nextToken());
        snacks = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            snacks[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(snacks);
        System.out.println(findSnackLen(m));
        br.close();
    }

    public static int findSnackLen(int m) {
        int res = 0;
        int st = 1, ed = snacks[snacks.length-1];
        while(st <= ed) {
            int mid = (st + ed) / 2;
            int cnt = 0, idx = binarySearch(mid);

            for (int i = idx; i < snacks.length; i++) {
                cnt += snacks[i] / mid;
            }

            if(cnt >= m) {
                res = mid;
                st = mid + 1;
            }
            else ed = mid - 1;
        }
        return res;
    }

    public static int binarySearch(int key) {
        int st = 0, ed = snacks.length, res = 0;
        while (st <= ed) {
            int mid = (st + ed) / 2;
            if(key <= snacks[mid]) {
                res = mid;
                ed = mid - 1;
            }
            else st = mid + 1;
        }
        return res;
    }

}
