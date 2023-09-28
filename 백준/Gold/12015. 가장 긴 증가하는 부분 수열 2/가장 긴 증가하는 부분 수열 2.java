import java.util.*;
import java.io.*;

public class Main {
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] lis = new int[arr.length];
        lis[0] = arr[0];
        int lisIdx = 1;
        for (int i = 1; i < n; i++) {

            if(lis[lisIdx - 1] < arr[i]) {  // 마지막 증가 수열의 값보다 크다면 그냥 넣으면 된다
                lis[lisIdx++] = arr[i];
            }
            else {  // 현재 값으로 대체했을 때 더 큰 경우가 발생할 수 있으므로 대체할 위치를 찾는다
                int replaceIdx = binarySearch(lis, lisIdx, arr[i]);
                lis[replaceIdx] = arr[i];
            }
        }
        System.out.println(lisIdx);
        br.close();
    }

    public static int binarySearch(int[] arr, int ed, int key) {
        int st = 0, idx = ed;

        while (st <= ed) {
            int mid = (st + ed) / 2;
            if(arr[mid] >= key) {
                idx = mid;
                ed = mid - 1;
            }
            else st = mid + 1;
        }
        return idx;
    }

}