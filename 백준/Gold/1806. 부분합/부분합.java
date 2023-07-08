import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), s = Integer.parseInt(st.nextToken());

        int[] arr = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        
        int ans = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        int sum =0;
        while (start <= n && end <= n) {
            if (sum >= s) {
                ans = Math.min(ans, end - start); // 현재 end는 다음 요소를 가리키고 있기 때문에 end-start+1이 아님
                sum -= arr[start++];
            } else {
                sum += arr[end++];
            }
        }
        if(ans == Integer.MAX_VALUE) bw.write("0");
        else bw.write(ans + "");
        
        
        bw.flush();
        bw.close();
        br.close();
    }
}