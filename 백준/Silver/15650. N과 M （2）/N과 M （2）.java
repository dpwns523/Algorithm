import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static int n, m;
    public static int[] seq;
    public static int[] visited;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        seq = new int[m];
        visited = new int[n + 1];
        dfs(1, 0);

        System.out.println(sb);
    }

    public static void dfs(int start, int seqNum){
        if(seqNum == m){
            for(int s : seq){
                sb.append(s+" ");
            }
            sb.append("\n");
            return;
        }
        for(int i=start; i<=n; i++){
            if(visited[i] == 0){
                visited[i] = 1;
                seq[seqNum] = i;
                dfs(i+1, seqNum + 1);
                visited[i] = 0;
            }
        }
    }
}
