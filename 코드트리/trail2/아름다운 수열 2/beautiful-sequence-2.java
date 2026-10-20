import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] A = new int[N];
        for (int i = 0; i < N; i++)
            A[i] = sc.nextInt();
        int[] B = new int[M];
        for (int i = 0; i < M; i++)
            B[i] = sc.nextInt();
        // Please write your code here.

        int ans = 0;
        boolean[] checked = new boolean[M];
        for (int i = 0; i < N - M + 1; i++) {
            Arrays.fill(checked, false);
            
            for (int j = 0; j < M; j++) {
                int target = A[i + j];
                for (int k = 0; k < M; k++) {
                    if(target == B[k] && !checked[k]){
                        checked[k] = true;
                        break;
                    }
                }
            }

            boolean satisfy = true;
            for (int k = 0; k < M; k++) {
                if(!checked[k]){
                    satisfy = false;
                    break;
                }
            }
            if (satisfy) ans++;
        }

        System.out.println(ans);
    }
}