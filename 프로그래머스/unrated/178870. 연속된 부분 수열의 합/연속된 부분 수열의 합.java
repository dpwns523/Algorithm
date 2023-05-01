import java.util.*;
class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        int curr=sequence[0], idx=1;
        Deque<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while(!queue.isEmpty()){
            if(curr > k){
                curr -= sequence[queue.poll()];
            }
            else if(curr == k){
                if(Arrays.stream(answer).allMatch(a->a==0) || answer[1]-answer[0] > queue.peekLast() - queue.peekFirst()){
                    answer[0] = queue.peekFirst();
                    answer[1] = queue.peekLast();
                }
                curr -= sequence[queue.poll()];
            }
            else if(curr < k){
                if(idx == sequence.length) break;
                curr += sequence[idx];
                queue.offer(idx);
                idx++;
            }
        }
        return answer; 
    }
}