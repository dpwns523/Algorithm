import java.io.*;

class Main{
    public static final int maxNum = 10000;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int[] visited = new int[maxNum+1];
        for(int i=1; i<=maxNum; i++){
            if(visited[i] == 1) continue;
            int curr = i;
            while(curr<=maxNum){
                curr = nextNum(curr);
                if(curr>maxNum || visited[curr] == 1) break;
                visited[curr] = 1;    // curr=1 -> 다음 curr인 2는 d(1)이며 1이 생성자가 됨
            }
        }
        for(int i=1; i<=maxNum; i++){
            if(visited[i] == 0) sb.append(i+"\n");
        }
        System.out.print(sb);
    }
    public static int nextNum(int num) {
        int result = num;
        while(num > 0) {
            result += num % 10;
            num /= 10;
        }
        return result;
    }
}