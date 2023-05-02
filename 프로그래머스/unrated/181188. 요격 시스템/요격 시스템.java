import java.util.*;
class Solution {
    /*
        다음 개구간의 시작점이 이전 요격 구간의 끝지점이랑 같거나 멀면 새로운 요격 미사일을 추가해야 된다
    */
    public int solution(int[][] targets) {     
        Arrays.sort(targets, (t1, t2) -> t1[1] == t2[1] ? t1[0] - t2[0] : t1[1] - t2[1]);
        int currEnd = targets[0][1];
        int answer = 1;
        for(int[] target : targets){
            if(target[0] >= currEnd){   // 시작지점이 현재 요격 위치 끝지점이랑 같거나 멀다
                currEnd = target[1];
                answer++;                
            }
        }
        return answer;
    }
}