import java.util.*;
class Solution {
    class Info {
        int start, end, takeTime, type;
        public Info(int start, int end, int type) {
            this.start = start;
            this.end = end;
            this.type = type;
        }
    }
    public int[] seq;
    public int[][] waiting;
    // public PriorityQueue<Info> init = new PriorityQueue<>((i1, i2) -> i1.start-i2.start);
    public Map<Integer, PriorityQueue<Info>> map = new HashMap<>();
    public int wait = Integer.MAX_VALUE;
    public int solution(int k, int n, int[][] reqs) {
        int answer = 0;
        waiting = new int[k+1][n-k+2];
        seq = new int[k];
        for(int[] req : reqs) {
            map.computeIfAbsent(req[2], key -> new PriorityQueue<>((i1, i2) -> i1.start-i2.start)).add(new Info(req[0], req[1], req[2]));
            // init.offer(new Info(req[0], req[1], req[2]-1));
        }
        for(int i=1; i<=k; i++) {
            for(int j=1; j<=n-k+1; j++) {
                consulting(i, j);
                if(waiting[i][j] == 0) break;
            }
        }
        Arrays.fill(seq, 1);
        dfs(0, k, k, n);
        return wait;
    }
    public void consulting(int type, int cnt) {
        if(!map.containsKey(type)) return;
        PriorityQueue<Info> person = new PriorityQueue<>(map.get(type));
        PriorityQueue<Integer> consultant = new PriorityQueue<>();
        int wait = 0, num = cnt;
        while(!person.isEmpty()) {
            Info next = person.poll();
            if(cnt == 0) {  // 대기 발생
                if(!consultant.isEmpty() && consultant.peek() > next.start) {   // 기다려야 한다
                    wait += (consultant.peek() - next.start);
                    consultant.add(consultant.poll() + next.end);
                }
                else{   // 앞에 끝난 인원이 있다 -> 상담 가능
                    while(!consultant.isEmpty()) {
                        if(consultant.peek() > next.start) break;
                        consultant.poll();
                        cnt++;
                    }
                    cnt--;
                    consultant.add(next.start + next.end);
                }
            }
            else {
                consultant.add(next.start + next.end);
                cnt--;
            }
        }
        waiting[type][num] = wait;
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