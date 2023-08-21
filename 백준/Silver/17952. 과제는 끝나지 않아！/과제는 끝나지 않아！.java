import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException{	// 메인 함수
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	// 입력
        int N = Integer.parseInt(br.readLine());	// N분 입력
        int time = 0, score = 0;	// 일하는 시간, 업무 평가
        Deque<int[]> queue = new ArrayDeque<>();	// 하던 업무 저장
        while(N > time) {	// 업무 종료까지 반복
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();	// 업무 받기
            int[] job;	// 업무 정보 배열
            if(input[0] == 0) { // 새로운 업무 없음
                if(!queue.isEmpty()) {
                    job = queue.pollFirst();    // 하던 업무를 읽어옴
                    if (--job[2] == 0) score += job[1];    // 업무를 마칠 수 있다면 업무 점수 추가
                    else queue.addFirst(job);    // 업무를 마치지 못한다면 업무 리스트에 추가
                }
            }
            else {
                if(--input[2] == 0) score += input[1];	// 업무를 바로 마칠 수 있다면 업무 점수 추가
                else queue.addFirst(input);	// 업무를 바로 마치지 못한다면 업무 리스트에 추가
            }
            time++;	// 시간 증가
        }
        System.out.println(score);	// 최종 평가 점수 출력
        br.close();	// 입력 닫기
    }
}
