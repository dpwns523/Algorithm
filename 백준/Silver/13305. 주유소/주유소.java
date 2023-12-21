import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dist = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] costs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        long cost = costs[0], res = 0;
        for (int i = 1; i < n; i++) {
            res += cost * dist[i-1];
            if(costs[i] < cost) cost = costs[i];
        }
        System.out.println(res);
        br.close();

    }

}