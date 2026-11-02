import java.util.Arrays;
import java.util.Scanner;
public class Main {
    static int MAX = 1_001;
    static char[] characters = new char[MAX];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();

        char[] c = new char[T];
        int[] x = new int[T];

        for (int i = 0; i < T; i++) {
            c[i] = sc.next().charAt(0);
            x[i] = sc.nextInt();
        
            characters[x[i]] = c[i];
        }
        // Please write your code here.

        int ans = 0;
        for (int i = a; i < b + 1; i++) {
            int d1 = Integer.MAX_VALUE, d2 = Integer.MAX_VALUE;
            for (int j = 0; j < MAX; j++) {
                if(characters[j] == 'S') {
                    d1 = Math.min(d1, Math.abs(i - j));
                    continue;
                }

                if(characters[j] == 'N') {
                    d2 = Math.min(d2, Math.abs(i - j));
                    continue;
                }
            }

            if ( d1 <= d2) {
                ans++;
            }
        }
        System.out.println(ans);

    }

// c_i = S or N, x_i에 위치.
// 현 위치 = k, k에서 가장 가까운 S 까지의 거리 = d_1, 가장 가까운 N까지의 거리 d_2
// d_1 <= d_2 => 
}
