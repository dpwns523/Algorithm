class Solution {
    public long solution(int k, int d) {
        long answer = 1;
        for(int i=0; i<=d; i+=k){
            int maxB = (int)Math.sqrt(Math.pow(d, 2) - Math.pow(i, 2));
            if(i == 0) answer += (maxB / k)*2;
            else answer += maxB / k;            
        }
        return answer;
    }
}