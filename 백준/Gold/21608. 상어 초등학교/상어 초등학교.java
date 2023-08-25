
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;
import java.awt.Point;

public class Main {
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	private static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int sz = n * n;
		map = new int[n][n];

		HashSet<Integer>[] hash = new HashSet[sz + 1];
		for (int i = 1; i <= sz; i++) {
			hash[i] = new HashSet<Integer>();
		}

		for (int i = 0; i < sz; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int student = Integer.parseInt(st.nextToken());

			for (int j = 0; j < 4; j++) {
				hash[student].add(Integer.parseInt(st.nextToken()));
			}

			Point lo = findLocation(student, n, hash);
			map[lo.y][lo.x]=student;
		}

		
		System.out.println(calScore(hash,n));
		
	}

	private static int calScore(HashSet<Integer>[] hash,int n) {
		int totalScore=0;
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				int count=0;
				for(int k=0;k<4;k++) {
					int cmpX = j + dx[k];
					int cmpY = i + dy[k];
					if (cmpX < 0 || cmpX >= n || cmpY < 0 || cmpY >= n)
						continue;

					if (hash[map[i][j]].contains(map[cmpY][cmpX])) {
						count++;
					}
				}
				
				if(count==0) {
					totalScore+=0;
				}
				else if(count==1) {
					totalScore+=1;
				}
				else if(count==2) {
					totalScore+=10;
				}
				else if(count==3) {
					totalScore+=100;
				}
				else if(count==4) {
					totalScore+=1000;
				}
			}
		}
		return totalScore;
		
	}

	private static Point findLocation(int student, int n, HashSet<Integer>[] h) {
		int maxCount = -1;
		int optimalX = -1;
		int optimalY = -1;
		Queue<Point> q = new ArrayDeque<Point>();
		// 2번 조건 - 1조건을 만족하는 칸이 여러개일때 큐에 넣어두고 다시 탐색하기 위해 사용

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(map[i][j]!=0)//탐색하는칸에 이미 다른애가 앉아있을경우
					continue;
				// i,j는 현재 위치
				int count = 0;// 주변에 인접한 좋아하는 친구들 수 카운팅
				for (int k = 0; k < 4; k++) {
					int cmpX = j + dx[k];
					int cmpY = i + dy[k];
					if (cmpX < 0 || cmpX >= n || cmpY < 0 || cmpY >= n)
						continue;

					if (h[student].contains(map[cmpY][cmpX])) {
						count++;
					}
				}
				if (maxCount == count) {
					q.add(new Point(j, i));
				}

				if (maxCount < count) {
					q.clear();
					q.add(new Point(j, i));

					optimalX = j;
					optimalY = i;
					maxCount = count;
				}
			}
		}

		if (q.size() != 1) {
			int maxEmptyCount = -1;
			while (!q.isEmpty()) {
				Point cur = q.poll();
				int count = 0;

				for (int i = 0; i < 4; i++) {
					int cmpX = cur.x + dx[i];
					int cmpY = cur.y + dy[i];

					if (cmpX < 0 || cmpX >= n || cmpY < 0 || cmpY >= n)
						continue;

					if (map[cmpY][cmpX] == 0) {
						count++;
					}

				}
				if (count > maxEmptyCount) {
					maxEmptyCount = count;
					optimalX = cur.x;
					optimalY = cur.y;
				}
			}
		}

		return new Point(optimalX, optimalY);
	}

}
