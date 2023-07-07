import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int limitCount = queue1.length * 3;     // 최악의 경우 전체를 옮기고 (n*2) 다시 옮겨서 원상태로 돌리는 경우는 나올 수 없다
        int answer = 0;
        long sum = Arrays.stream(queue1).sum() + Arrays.stream(queue2).sum();
        if(sum % 2 != 0) return -1;
        CustomQ q1 = new CustomQ(queue1);
        CustomQ q2 = new CustomQ(queue2);

        while(!q1.checkSum(q2)){
            if(q1.sum > q2.sum) q1.popAndInsert(q2);
            else q2.popAndInsert(q1);
            answer +=1;
            if(answer > limitCount) return -1;
        }
        return answer;
    }
    static class CustomQ{
        Queue<Integer> queue;
        long sum;

        public CustomQ(int[] arr){
            this.sum =0;
            this.queue = new ArrayDeque<>();
            for(int i=0; i<arr.length; i++){
                this.queue.add(arr[i]);
                this.sum += arr[i];
            }
        }
        public void popAndInsert(CustomQ q){
            int p = this.queue.poll();
            q.queue.add(p);

            this.sum -= p;
            q.sum += p;
        }
        public boolean checkSum(CustomQ q){
            if(this.sum == q.sum) return true;
            return false;
        }
    }
}