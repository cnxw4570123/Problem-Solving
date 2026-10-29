import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int[] counts = new int[10];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        // Please write your code here.

        int ans = 0;
        for (int current = x; current < y + 1; current++) {
            int temp = current;
            Arrays.fill(counts, 0);

            int digits = 0;
            while (temp > 0) {
                counts[temp % 10]++;
                temp /= 10;
                digits++;
            }
            
            
            boolean interesting = false;
            for (int i = 0; i < 10; i++) {
                if(counts[i] == digits - 1) interesting = true;
            }
            if (interesting) ans++;
        }
        System.out.println(ans);
    }

    
}