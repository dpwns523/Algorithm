import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static LinkedList<Integer>[] graph;
    static int[] colors;
    static int c = 1;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        for(int i=0; i<k; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph = new LinkedList[v+1];
            colors = new int[v+1];
            for(int j=1; j<=v; j++){
                graph[j] = new LinkedList<>();
            }
            for(int j=1; j<=e; j++){
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                graph[b].add(a);
            }
            boolean isBiGraph = true;
            for(int j=1; j<=v; j++) {
                if(!isBiGraph) break;
                if(colors[j] != 0) continue;

                if (checkBiGraph(j, c)) isBiGraph = true;
                else isBiGraph = false;
            }
            bw.write(isBiGraph ? "YES\n" : "NO\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static Boolean checkBiGraph(int start, int color) throws IOException {    // dfs
        colors[start] = color;
        for(int node: graph[start]){
            if(colors[node] == 0){
                if(!checkBiGraph(node, -color)) return false;
            }
            else if(colors[node] == color){
                return false;
            }
        }
        return true;
    }
}
