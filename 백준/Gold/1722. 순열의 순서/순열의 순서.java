import java.util.*;
import java.io.*;
public class Main {
    /*
        n!이 최대 20 -> 완탐불가
        정렬된 수열
            -> 맨 앞자리를 결정하면 그 뒤는 (n-1)!의 가지수가 남는 테크닉 활용
        소문제
        1. k번째 순열
        2. 주어진 순열이 몇 번째 순열인지?
     */
    public static int[][] permutation;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] factorial = makeFactorial(n);
        long[] command = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        int[] visited = new int[n+1];    // 같은 수가 나오면 안됨
        if(command[0] == 2) {    // 몇 번째 순열인지 찾기
            long res = 1;
            for(int i=1; i<=n; i++) {
                for(int j=1; j<command[i]; j++) {    // command[i]가 2이면 현재 위치에 1이 나왔던 수열 개수를 더해야함
                    if(visited[j] == 0) {
                        res += factorial[n-i];    // n자리 수 중 i자리 수 만큼(2, x, x, x) 빼고 그 뒤 자리 수 가능한 경우의 수 더하기
                    }
                }
                visited[(int)command[i]] = 1;
            }
            System.out.println(res);
        }
        else {    // 몇 번째 순열?
            long k = command[1];
            int[] arr = new int[n+1];
            for(int i=1; i<=n; i++) {    // 첫번째 자리부터 시작
                for(int j=1; j<=n; j++) {    // 가능한 수
                    if(visited[j] == 1) continue;    // 이미 결정된 자리의 숫자
                    if(factorial[n-i] < k) {    // 현재 위치를 제외한 뒤의 위치 (n-i)!을 몇번할 수 있느냐에 따라 맨 앞자리가 정해짐
                        // 1번 뺏더니 다음번에 (n-i)!가 불가능하다면 i번째 위치가 j로 고정되는 것
                        k -= factorial[n-i];
                    }
                    else {
                        arr[i] = j;
                        visited[j] = 1;
                        break;
                    }
                }
            }
            for(int i=1; i<=n; i++){
                System.out.print(arr[i]+" ");
            }
        }
        br.close();
    }
    public static long[] makeFactorial(int n) {
        long[] f = new long[n+1];
        f[0] = 1;
        for(int i=1; i<=n; i++) {
            f[i] = f[i-1]*i;
        }
        return f;
    }

}