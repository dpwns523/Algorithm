import java.io.*;
import java.util.*;

public class Main {

    public static int n, m;
    public static int[] arr;
    public static int[] seq;
    public static int[] visited;
    public static Set<String> result = new LinkedHashSet<>();
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                                .toArray();
        Arrays.sort(arr);

        seq = new int[m];
        visited = new int[n];
        dfs(0);
        for(String s : result){
            sb.append(s);
        }
        System.out.print(sb);
    }

    public static void dfs(int seqNum){
        if(seqNum == m){
            for(int s : seq){
                sb.append(s+" ");
            }
            sb.append("\n");
            result.add(sb.toString());
            sb.setLength(0);
            return;
        }
        for(int i=0; i<n; i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                seq[seqNum] = arr[i];
                dfs(seqNum + 1);
                visited[i] = 0;
            }
        }
    }
}
