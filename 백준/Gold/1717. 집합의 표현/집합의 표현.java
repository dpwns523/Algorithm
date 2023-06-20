import java.util.*;
import java.io.*;

class Main {
    public static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        parent = new int[n+1];
        for(int i=1; i<=n; i++){
            parent[i] = i;
        }
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken()), a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            if(command == 0) union(a, b);
            else if(command == 1){
                if (find(a) == find(b)) System.out.println("YES");
                else System.out.println("NO");
            }
        }
    }
    public static void union(int n1, int n2){
        int a = find(n1);
        int b = find(n2);
        if(a > b) parent[a] = b;
        else parent[b] = a;
    }
    public static int find(int a) {
        if(parent[a] != a) return parent[a] = find(parent[a]);
        return a;
    }
}