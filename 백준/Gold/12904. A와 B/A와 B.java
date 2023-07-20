import java.io.*;
public class Main {
    public static String t, s;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();
        t = br.readLine();
        System.out.println(backtrackingT() ? "1" : "0");
        br.close();
    }
    public static boolean backtrackingT() {
        while(t.length() != s.length()) {
            if(t.charAt(t.length()-1) == 'A') {
                t = new StringBuilder(t).deleteCharAt(t.length()-1).toString();
            }
            else t = new StringBuilder(t).deleteCharAt(t.length()-1).reverse().toString();
        }
        return t.equals(s);
    }
}