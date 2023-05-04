import java.util.*;
class Solution {
    public static int[][] graph;
    public static int count = 100;
    public int solution(int n, int[][] wires) {
        graph = new int[n+1][n+1];
        for(int[] wire : wires){
            graph[wire[0]][wire[1]] = 1;
            graph[wire[1]][wire[0]] = 1;
        }
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                if(graph[i][j] == 1){
                    graph[i][j] = 0;
                    graph[j][i] = 0;
                    countTree(i, j);
                    graph[i][j] = 1;
                    graph[j][i] = 1;
                }
            }
        }
        return count;
    }
    public void countTree(int n1, int n2) {
        // 잘린 노드 n1, n2
        Queue<Integer> queue1 = new LinkedList<>();
        queue1.offer(n1);
        Queue<Integer> queue2 = new LinkedList<>();
        queue2.offer(n2);
        count = Math.min(count, Math.abs(countNode(queue1) - countNode(queue2)));
    }
    public int countNode(Queue<Integer> queue) {
        // 각 시작점에서 연결된 간선의 수
        int num = 0;
        int[][] visited = new int[graph.length][graph[0].length];
        while(!queue.isEmpty()){
            int node = queue.poll();
            for(int i=1; i<graph.length; i++){
                if(graph[node][i] == 1 && visited[node][i] == 0){
                    queue.offer(i);
                    visited[node][i] = 1;
                    visited[i][node] = 1;
                    num++;
                }
            }
        }
        return num;
    }
}