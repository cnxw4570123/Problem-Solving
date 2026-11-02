import java.util.Scanner;
public class Main {
    static int MAX = 1000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] h = new int[n];
        for (int i = 0; i < n; i++) {
            h[i] = sc.nextInt();
        }
        // Please write your code here.
        int ans = 0;
        for (int i = 0; i < MAX + 1; i++) {
            int previous = h[0];
            int res = previous > i ? 1 : 0;
            
            for (int j = 1; j < n; j++) {
                if(previous <= i && h[j] > i) {
                    res++;
                }
                previous = h[j];
            }

            ans = Math.max(ans, res);
        }
        System.out.println(ans);
    }
    /*
    3 2 2 1 2
    3 1
    if previous > 높이 && current > 높이 then continue
    if previous <= 높이 && current > 높이 then res++
    

    마지막 구간.
    prev
    */
}