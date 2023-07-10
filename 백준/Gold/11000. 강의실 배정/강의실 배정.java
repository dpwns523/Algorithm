import java.util.*;
import java.io.*;
public class Main {
    /*
         강의실 배정을 하려면 시작 시간 순서로 정렬을 한다.
         우선순위 큐를 이용하여 가장 빨리 끝나는 시간과 다음 강의 시작 시간을 비교하면 강의실을 새로 or 넘겨받기 가능
     */
    static class Lecture {
        int start, end;
        public Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] room = new int[n];
        Lecture[] lectures = new Lecture[n];
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            lectures[i] = new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(lectures, (l1, l2) -> l1.start-l2.start);
        int res = 1;
        Queue<Lecture> queue = new PriorityQueue<>((l1, l2) -> l1.end - l2.end);
        queue.offer(lectures[0]);
        for(int i=1; i<n; i++) {
            if(queue.peek().end > lectures[i].start) {
                queue.offer(lectures[i]);
                res++;
            }
            else if(queue.peek().end <= lectures[i].start) {
                queue.poll();
                queue.offer(lectures[i]);
            }
        }
        System.out.println(res);
        br.close();
    }
}