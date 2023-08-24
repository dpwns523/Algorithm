
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int needOutCount;
	private static int n;
	private static int[][] arr;
	private static int maxScore = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		arr = new int[n + 1][10];
		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			for (int j = 1; j <= 9; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 입력 완료

		// arr[i][j]는 j번 타자가 i회에 칠 수 있는 타구

		needOutCount = n * 3;

		int[] seq = new int[10];
		boolean[] visited = new boolean[10];
		seq[4] = 1;
		visited[1] = true;

		makeSequence(1, seq, visited);
		System.out.println(maxScore);

	}

	private static void makeSequence(int cur, int[] seq, boolean[] visited) {
		if (cur == 4) {
			makeSequence(cur + 1, seq, visited);
			return;
		}
		if (cur == 10) {
			maxScore = Math.max(maxScore, gameStart(seq));
			return;
		}

		for (int i = 1; i < 10; i++) {
			if (!visited[i]) {
				seq[cur] = i;
				visited[i] = true;
				makeSequence(cur + 1, seq, visited);
				visited[i] = false;
				seq[cur] = -1;
			}
		}

	}

	private static int gameStart(int[] seq) {

		int curBetting = 1;
		int curInning = 1;
		int score = 0;
//		if (seq[2] == 5 && seq[3] == 6 && seq[5] == 7) {
//			System.out.println("d");
//		}
		int size = n;
		while (size-- > 0) {
			int flag = 0;
			int outCount = 0;

			while (outCount != 3) {
				int cur = arr[curInning][seq[curBetting]];
				curBetting++;
				if (curBetting == 10) {
					curBetting = 1;
				}

				if (cur == 0) {// 현재타자 아웃당햇을때
					outCount++;
					continue;
				} else {
					flag = flag << 1;
					flag++;
					if (flag >= 8) {
						score++;
						flag -= 8;
					}

					for (int i = 1; i < cur; i++) {
						flag = flag << 1;
						if (flag >= 8) {
							score++;
							flag -= 8;
						}
					}
				}

			}
			curInning++;
		}
		return score;
	}
}
