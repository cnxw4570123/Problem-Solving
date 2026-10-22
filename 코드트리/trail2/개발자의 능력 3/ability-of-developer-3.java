import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] ability = new int[6];
        int sum = 0;
        for (int i = 0; i < 6; i++) {
            ability[i] = sc.nextInt();
            sum += ability[i];
        }
        // Please write your code here.

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 6; i++) {
            for (int j = i + 1; j < 6; j++) {
                for (int k = j + 1; k < 6; k++) {
                    int teamA = ability[i] + ability[j] + ability[k];
                    int teamB = sum - teamA;
                    ans = Math.min(ans, Math.abs(teamA - teamB));
                }
            }
        }
        System.out.println(ans);
    }
}
