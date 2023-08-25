
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Node>[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int V=Integer.parseInt(st.nextToken());
		int E=Integer.parseInt(st.nextToken());
		
		int K=Integer.parseInt(br.readLine());//시작점
		
		int[] dist=new int[V+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		arr=new ArrayList[V+1];
		for(int i=1;i<=V;i++) {
			arr[i]=new ArrayList<>();
		}
		
		for(int i=0;i<E;i++) {
			st=new StringTokenizer(br.readLine());
			int u=Integer.parseInt(st.nextToken());
			int v=Integer.parseInt(st.nextToken());
			int w=Integer.parseInt(st.nextToken());
		
			arr[u].add(new Node(v,w));
		}
		dijk(K,dist);
		StringBuilder sb=new StringBuilder();
		for(int i=1;i<=V;i++) {
			if(dist[i]==Integer.MAX_VALUE) {
				sb.append("INF").append('\n');
			}else {
				sb.append(dist[i]).append('\n');
			}
		}
		System.out.println(sb.toString());
		
		
		
	}//end of main
	private static void dijk(int k,int[] dist) {
		PriorityQueue<Node> pq=new PriorityQueue<>();
		dist[k]=0;
		pq.add(new Node(k,0));
		
		while(!pq.isEmpty()) {
			Node cur=pq.poll();
			int sz=arr[cur.nodeNum].size();
			
			for(int i=0;i<sz;i++) {
				Node cmp=arr[cur.nodeNum].get(i);
				
				if(dist[cmp.nodeNum]>dist[cur.nodeNum]+cmp.cost) {
					dist[cmp.nodeNum]=dist[cur.nodeNum]+cmp.cost;
					pq.add(new Node(cmp.nodeNum,dist[cmp.nodeNum]));
				}
			}
		}
		
	}
	static class Node implements Comparable<Node>{
		int nodeNum;
		int cost;
		
		public Node(int nodeNum, int cost) {
			this.nodeNum = nodeNum;
			this.cost=cost;
		}

		@Override
		public int compareTo(Node o) {
			
			return this.cost-o.cost;
		}
		
	}
}
