import java.util.*;
import java.util.stream.Collectors;
class Solution {
    class Customer{
        int visitedTime;
        int outTime;
        
        public Customer(String visitedTime, String outTime){
            this.visitedTime = parseTime(visitedTime);
            this.outTime = parseTime(outTime);
        }
        
        private int parseTime(String time){
            int[] parse = Arrays.stream(time.split(":")).mapToInt(Integer::parseInt).toArray();
            return 60 * parse[0] + parse[1];
        }
    }
    public int solution(String[][] book_time) {
        int n = book_time.length;
        
        Customer[] customers = new Customer[book_time.length];
        for(int i=0; i<n; i++) {
            customers[i] = new Customer(book_time[i][0], book_time[i][1]);
        }
        Arrays.sort(customers, (c1, c2) -> c1.visitedTime - c2.visitedTime);
        
        Queue<Integer> endQueue = new PriorityQueue<>();
        int res = 1;
        endQueue.offer(customers[0].outTime + 10);
        
        for(int i=1; i<n; i++) {
            if(endQueue.peek() <= customers[i].visitedTime) endQueue.poll();
            else res++;
            endQueue.offer(customers[i].outTime + 10);
        }
        
        return res;
    }
}