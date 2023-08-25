import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.awt.Point;

public class Main {
	static ArrayList<Shark> sharks;
	static int[] dx = { 0, 0, 0, 1, -1 };
	static int[] dy = { 0, -1, 1, 0, 0 };
	private static int R;
	private static int C;
	static int king=0;
	private static int[][] current;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		current = new int[R+1][C+1];
		sharks = new ArrayList<Shark>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			// 좌표

			int s = Integer.parseInt(st.nextToken());
			// 속력

			int d = Integer.parseInt(st.nextToken());
			// 이동방향

			int z = Integer.parseInt(st.nextToken());
			// 크기
			sharks.add(new Shark(new Point(c, r), s, d, z));
		}
		// 입력종료
		for (int i = 1; i <= C; i++) {
			Collections.sort(sharks);//
			CatchShark(i);// 낚시왕 상어잡는데 성공

			moveShark();

			fight();
		}

		System.out.println(king);
	}//end of main
	


	private static void fight() {
		ArrayList<Integer> toDelete=new ArrayList<>();
		for(int i=1;i<=R;i++) {
			Arrays.fill(current[i], -1);
		}
		for(int i=0;i<sharks.size();i++) {
			Shark cur=sharks.get(i);
			int y=cur.position.y;
			int x=cur.position.x;
			
			if(current[y][x]!=-1) {
				if(sharks.get(current[y][x]).size>cur.size) {
					//먼저 와있는 놈이 더 클때
					toDelete.add(i);
				}
				else {//새로 비교하는 놈이 더 클때
					toDelete.add(current[y][x]);
					current[y][x]=i;
				}
			}
			else {
				current[y][x]=i;
			}
		}
		
		Collections.sort(toDelete);
		for(int i=toDelete.size()-1;i>=0;i--) {
			sharks.remove((int)toDelete.get(i));
		}
	}

//d가 1인 경우는 위, 2인 경우는 아래, 3인 경우는 오른쪽, 4인 경우는 왼쪽을 의미
	private static void moveShark() {
		ArrayList<Shark> toAdd=new ArrayList<>();

		while(!sharks.isEmpty()){
			Shark cur = sharks.get(0);
			sharks.remove(0);

			int cmpX = cur.position.x;
			int cmpY = cur.position.y;
			int direc = cur.direction;
			int sp;
			if(dx[direc]==0) {//상하 운동이면
				sp=cur.speed%(2*R-2);
			}else {
				sp=cur.speed%(2*C-2);
			}
			
			for (int j = 0; j < sp; j++) {
				
				if(direc==1&&cmpY==1) {
					direc=2;
				}else if(direc==2&&cmpY==R) {
					direc=1;
				}else if(direc==3&&cmpX==C) {
					direc=4;
				}else if(direc==4&&cmpX==1) {
					direc=3;
				}
				
				cmpX += dx[direc];
				cmpY += dy[direc];

			} // 이동 종료
			toAdd.add(new Shark(new Point(cmpX, cmpY), cur.speed, direc, cur.size));

		}
		sharks=toAdd;

	}

	private static void CatchShark(int curC) {
		int sz = sharks.size();

		for (int i = 0; i < sz; i++) {
			Shark cur = sharks.get(i);

			if (cur.position.x == curC) {// 제일 땅에 가깝고 같은 열에 있을 경우 제거하고 바로리턴
				sharks.remove(i);
				king+=cur.size;
				return;
			}
			if (cur.position.x > curC) {// 상어가 현재 낚시왕보다 오른쪽에 있는것을 탐색하기 시작할경우
				return;// 정렬되어있기때문에 바로 넘겨줌
			}
		}
		// 현재열에 살아있는 상어가 존재하지않을경우 그냥 둠
	}

	static class Shark implements Comparable<Shark> {
		Point position;
		int speed;
		int direction;
		int size;

		public Shark(Point position, int speed, int direction, int size) {
			this.position = position;
			this.speed = speed;
			this.direction = direction;
			this.size = size;
		}

		@Override
		public int compareTo(Shark o) {

			if (this.position.x == o.position.x) {
				return this.position.y - o.position.y;
			} else
				return this.position.x - o.position.x;
		}
	}
}
