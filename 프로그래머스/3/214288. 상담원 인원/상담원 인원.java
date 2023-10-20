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
    public PriorityQueue<Info> init = new PriorityQueue<>((i1, i2) -> i1.start-i2.start);
    public int wait = Integer.MAX_VALUE;
    public int solution(int k, int n, int[][] reqs) {
        int answer = 0;
        seq = new int[k];
        for(int[] req : reqs) {
            init.offer(new Info(req[0], req[1], req[2]-1));
        }
        Arrays.fill(seq, 1);
        dfs(0, k, k, n);
        return wait;
    }
    public void dfs(int idx, int sum, int k, int n) {
        if(idx == k) {
            if(sum < n) return;
            consult();
            return;
        }
        
        for(int i=0; i<=n-k; i++) {
            if(sum + i > n) continue;
            seq[idx] += i;
            dfs(idx+1, sum+i, k, n);
            seq[idx] -= i;
        }
    }
    
    public void consult() {
        int[] use = Arrays.copyOf(seq, seq.length);
        int currWait = 0;
        PriorityQueue<Info> person = new PriorityQueue<>(init);
        Map<Integer, PriorityQueue<Integer>> consultant = new HashMap<>();
        
        while(!person.isEmpty() && currWait < wait) {
            Info next = person.poll();
            if(use[next.type] == 0) {   // 상담원이 부족
                PriorityQueue<Integer> p = consultant.get(next.type);
                if(p.peek() > next.start) {   // 기다려야 한다
                    //기다렸다가 먼저 작업할 사람을 찾아야하는데 일찍끝나는사람이 들어가는게 전체 대기시간을 줄일 수 있다.
                    currWait += (p.peek() - next.start);
                    p.add(p.poll() + next.end);
//                     Queue<Info> tmp = new ArrayDeque<>();
//                     tmp.add(next);
//                     int min = next.end;
                    
//                     while(!person.isEmpty() && person.peek().start <= p.peek()) {   // 상담 기다린 사람들
//                         Info i = person.poll();
//                         if(i.type == next.type) min = Math.min(min, i.end);
//                         tmp.add(i);
//                     }
                    
//                     while(!tmp.isEmpty()) {
//                         Info i = tmp.poll();
//                         if(i.type == next.type && min == i.end) {   // 상담 가능 사람 중 가장 짧게 끝나는 사람 먼저                            
//                             currWait += (p.peek() - i.start);
//                             p.add(p.poll() + i.end);
//                             break;
//                         }
//                         else person.offer(i);
//                     }
//                     while(!tmp.isEmpty()) {
//                         person.offer(tmp.poll());
//                     }
                }
                else{   // 앞에 끝난 인원이 있다 -> 상담 가능
                    while(!p.isEmpty()) {
                        if(p.peek() > next.start) break;
                        p.poll();
                        use[next.type]++;
                    }
                    use[next.type]--;
                    p.add(next.start + next.end);
                }
            }
            else {
                consultant.computeIfAbsent(next.type, k -> new PriorityQueue<>()).add(next.start + next.end);
                use[next.type]--;
            }
        }
        wait = Math.min(wait, currWait);
    }
}