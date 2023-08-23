import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken()), d = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());

        int[] sushis = new int[n];
        for (int i = 0; i < n; i++) {
            sushis[i] = Integer.parseInt(br.readLine());
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(c, 1);
        int s = 0, ed = 0, max = 0;
        while(s < n && max != k+1) {
            if(ed >= n) map.put(sushis[ed - n], map.getOrDefault(sushis[ed++ - n], 0)+1);
            else map.put(sushis[ed], map.getOrDefault(sushis[ed++], 0)+1);
            if(ed >= k) {   // k개
                max = Math.max(max, map.keySet().size());
                int before = map.get(sushis[s]);
                if(before == 1) map.remove(sushis[s++]);
                else map.put(sushis[s++], before - 1);
            }
        }
        System.out.println(max);
        br.close();
    }

}