class Solution {
    public String solution(String number, int k) {
        int idx = 0;
        StringBuilder sb = new StringBuilder(number);
        while(k>0 && idx < number.length() - 1) {
            if(sb.charAt(idx) < sb.charAt(idx+1)) {
                sb.deleteCharAt(idx);
                k--;
                idx = idx==0 ? idx : --idx;
            }
            else idx++;
        }
        number = sb.toString();
        if(k>0) {
            number = number.substring(0, number.length() - k);
        }
        return number;
    }
}