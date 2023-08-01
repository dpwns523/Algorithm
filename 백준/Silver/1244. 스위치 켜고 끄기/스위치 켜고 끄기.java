import java.io.*;
import java.util.*;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int students = Integer.parseInt(br.readLine());
        for(int t=0; t<students; t++) {
            st = new StringTokenizer(br.readLine());
            int g = Integer.parseInt(st.nextToken()), num = Integer.parseInt(st.nextToken());

            if (g == 1) {
                for (int i = 1; num * i <= n; i++) {
                    arr[num * i] = 1 - arr[num * i];
                }
            } else {
                arr[num] = 1 - arr[num];
                int left = num - 1, right = num + 1;
                while (left >= 1 && right <= n) {
                    if (arr[left] == arr[right]) {
                        arr[left] = 1 - arr[left];
                        arr[right] = arr[left];
                    } else break;
                    left--;
                    right++;
                }
            }
        }
        for(int i=1;i<=n; i++) {
            System.out.print(arr[i]+" ");
            if(i % 20 == 0) System.out.println();
        }

        br.close();
    }
}