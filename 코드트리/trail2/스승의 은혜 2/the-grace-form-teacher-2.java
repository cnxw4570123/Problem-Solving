import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int b = sc.nextInt();
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = sc.nextInt();
        }
        // Please write your code here.

        Arrays.sort(p);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int start = p[i] / 2;
            if(start > b) continue;
            
            int count = 1;
            for (int j = 0; j < n; j++) {
                if(i == j) continue;
                if(start + p[j] > b) break;
                start += p[j];
                count++;                
            }
            ans = Math.max(ans, count);
        }
        System.out.println(ans);
    }
}