import java.util.*;
import java.io.*;
public class Main {
    static class Candidate {
        int like, day, num;
        public Candidate(int like, int day, int num) {
            this.like = like;
            this.day = day;
            this.num = num;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Candidate> student = new ArrayList<>();
        for(int i=1; i<=m; i++) {
            int s_num = Integer.parseInt(st.nextToken());
            int idx = -1;
            for(int j=0; j<student.size(); j++) {
                if(student.get(j).num == s_num) {
                    idx = j;
                    break;
                }
            }
            if(idx == -1) {    // 사진 등재
                if(student.size() == n) {    // 사진 삭제 후 등재
                    Collections.sort(student, (s1, s2) -> s1.like==s2.like ? s1.day - s2.day : s1.like - s2.like);
                    student.remove(0);
                }
                student.add(new Candidate(1, i, s_num));

            }
            else {
                student.get(idx).like++;
            }
        }
        Collections.sort(student, (s1, s2) -> s1.num - s2.num);
        for(Candidate s : student) {
            System.out.print(s.num +" ");
        }
        br.close();
    }
}