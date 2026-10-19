import java.util.Scanner;
public class Main {
    static int[] dy = {-1, -1, -1, 0, 1, 1, 1, 0}, dx = {-1, 0, 1, 1, 1, 0, -1, -1};
    static final String DIR = "↖↑↗→↘↓↙←";
    static final String LEE = "LEE";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.next();
        }
        // Please write your code here.
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char target = arr[i].charAt(j);
                if (target != 'L') continue;

                for (int k = 0; k < 8; k++) {
                    boolean matches = true;
                    for (int l = 1; l < 3; l++) {
                        int r = i + dy[k] * l, c = j + dx[k] * l;
                        if(r >= n || c >= m || r < 0 || c < 0 || arr[r].charAt(c) != LEE.charAt(l)) {
                            matches = false;
                            break;
                        }
                    }
                    if(matches) {
                        ans++;
                    }
                }
            }
        }
        System.out.println(ans);
    }
}