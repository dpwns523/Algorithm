// 멘토 n명 , 1~k 상담유형
// 각 멘토는 k개의 상담 유형 중 하나만 담당할 수 있음
// 동시에 참가자 한명과만 상담 가능 , 참가자가 요청한 시간만큼

// 원하는 유형 멘토가 모두 상담 - 기다린다
// 상담 요청 ~ 시작할때까지 시간
// 먼저 상담 요청한 참가자가 우선

// 기다린 시간의 합이 최소가 되도록 , 유형별 멘토인원 1명 이상

// 1 <= 상담 유형수 k <= 5
// k <= 멘토수 n <= 20
// 3 <= 상담 수 <= 300

import java.util.*;
import java.io.*;

class Solution {
    static ArrayList<ArrayList<int[]>> list = new ArrayList<>();
    static int[][] waiting;
    public int[] seq;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static int wait = Integer.MAX_VALUE;
    public int solution(int k, int n, int[][] reqs) {
        for (int i=0; i<k+1; i++){
            list.add(new ArrayList<int[]>());
        }

        int reqsLen = reqs.length;

        for (int i=0; i<reqsLen; i++){
            int type = reqs[i][2];
            list.get(type).add(new int[] {reqs[i][0] , reqs[i][1]});
        }

        // 1 ~ k 까지 1명일때 2명일때 ... 대기시간 구하기
        // 배열은 상담원 명수만큼
        waiting = new int[k+1][n+1]; // 유형, 상담원 명수에 따라 
        for (int i=0; i< k+1; i++){
            waiting[i][0] = Integer.MAX_VALUE; // 0명일 때는 최대값으로 초기화
        }

        int[] whenzeros = new int[k+1];
        for (int type=1; type<= k; type++){
            for (int num=1; num <=n; num++){
                // 상담 타입, 상담원 명수 
                // 명수에 따라 priorityqueue 설정
                pq.clear();
                // for (int i=0; i<num; i++){
                //     pq.add(0);
                // }
                waiting[type][num] = getWaitingTime(type, num);
                if (waiting[type][num] == 0){ // 상담원 늘리는 게 의미 없을 때
                    // whenzeros[type] = num; // 언제 0인지 기록
                    break;
                }
                // whenzeros[type] = num;
            }
        }

        // for (int i=1; i<=k; i++){
        //     System.out.println(Arrays.toString(waiting[i]));
        // }

//         // System.out.println(Arrays.toString(whenzeros));
//         int good = 0;
//         for(int type=1; type<=k; type++){
//             good += whenzeros[type];
//         }

//         // 최적일 때 상담원 수가 원래보다 더 많으면 - 줄여야 함
//         // System.out.println("good: " + good);
//         if(good > n){
//             int htm = good - n; // htm명만큼 줄여야 한다. 
//             // whenzeros -1 , -2 , -3 .. htm 만큼 대기시간 제일 적은걸로 선택
//             // whenzeros 갱신 , answer + waitingtime , 

//             for (int i=1; i<= htm; i++){
//                 int minwait = Integer.MAX_VALUE;
//                 int mintype = -1;

//                 for (int type=1; type<=k; type++){
//                     int when = whenzeros[type] - 1;
//                     if (waiting[type][when] < minwait){
//                         minwait = waiting[type][when];
//                         mintype = type;
//                     }
//                 }
//                 // System.out.println("minType: " + mintype + ", minWait: " + minwait);
//                 whenzeros[mintype] -= 1;
//                 // answer += minwait;
//                 //System.out.println(Arrays.toString(whenzeros)+" "+answer);
//             }

//         }
//         int answer = 0;
//         for(int type=1; type<=k; type++) {
//             answer += waiting[type][whenzeros[type]];
//         }
        // System.out.println(Arrays.deepToString(waiting));
        seq = new int[k];
        Arrays.fill(seq, 1);
        dfs(0, k, k, n);
        return wait;
    }

    private static int getWaitingTime(int type, int num){
        int waitingtime = 0;

        ArrayList<int[]> participants = list.get(type);
        //System.out.println(participants);
        

        for ( int[] participant : participants ){
            
            // int pqtime = pq.poll();
            int start = participant[0];
            int duringtime = participant[1];
            while(!pq.isEmpty() && pq.peek() <= start) {
                    pq.poll();
                    num++;
            }
            
            if (num == 0) { // 아직 상담이 안끝났을 때
                waitingtime += ( pq.peek() - start );
                pq.add(pq.poll() + duringtime);
            } else { // 비어있는 타임이 있으면
                num--;
                pq.add(start + duringtime);
            }
        }

        return waitingtime;
    }
    
    public void dfs(int idx, int sum, int k, int n) {
        if(idx == k) {
            if(sum < n) return; // 상담사가 n명이어야 함
            int curr = 0;
            for(int i=1; i<=k; i++) {
                curr += waiting[i][seq[i-1]];
            }
            wait = Math.min(wait, curr);
            return;
        }
        
        for(int i=0; i<=n-k; i++) {
            if(sum + i > n) continue;
            seq[idx] += i;
            dfs(idx+1, sum+i, k, n);
            seq[idx] -= i;
        }
    }
}