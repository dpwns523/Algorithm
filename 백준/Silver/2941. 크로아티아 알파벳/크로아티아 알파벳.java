import java.io.*;

class Main{
    public static final String[] croatia = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int answer = 0;
        for(String spl : croatia) {
            while(s.contains(spl)){
                s = s.replaceFirst(spl, "#");   // masking
                answer++;
            }
        }
        s = s.replaceAll("#", "");
        br.close();
        System.out.println(answer + s.length() +"");
    }
}