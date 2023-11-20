import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static char[] str, explosive;
    static Stack<data> stack = new Stack<>();
    public static void main(String[] args) throws IOException {
//        System.setIn(Files.newInputStream(Paths.get("src/input.txt")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str = br.readLine().toCharArray();
        explosive = br.readLine().toCharArray();
        for (int i = 0; i < str.length; i++) {
            int idx;
            if(stack.isEmpty()){
                idx = 0;
            } else{
                idx = stack.peek().idx + 1;
            }

            if(str[i] == explosive[idx]){
                stack.push(new data(idx, str[i]));
            } else if(str[i] == explosive[0]){
                stack.push(new data(0, str[i]));
            } else{
                stack.push(new data(-1, str[i]));
            }

            if(stack.peek().idx == explosive.length - 1){
                for (int j = 0; j < explosive.length; j++) {
                    stack.pop();
                }
            }
        }
        StringBuilder ans = new StringBuilder();
        while(!stack.isEmpty()){
            ans.append(stack.pop().character);
        }
        if(ans.length() == 0){
            ans.append("ALURF");
        }

        bw.write(ans.reverse().toString());

        bw.flush();
        bw.close();
        br.close();
    }
}

class data{
    int idx;
    char character;

    public data(int idx, char character) {
        this.idx = idx;
        this.character = character;
    }
}