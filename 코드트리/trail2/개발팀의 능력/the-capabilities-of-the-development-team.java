import java.util.Scanner;
import java.util.Arrays;

public class Main {
    static int[] abilities = new int[5];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int total = 0;
        for (int i = 0; i < 5; i++) {
            abilities[i] = sc.nextInt();
            total += abilities[i];
        }


        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 5; j++) {
                for (int k = 0; k < 4; k++) {
                    for (int l = k + 1; l < 5; l++) {
                        if(i == k || i == l || j == k || j == l) {
                            continue;
                        }

                        int teamA = abilities[i] + abilities[j];
                        int teamB = abilities[k] + abilities[l];
                        int teamC = total - teamA - teamB;
                        if(teamA == teamB || teamB == teamC || teamC == teamA) {
                            continue;
                        }
                        min = Math.min(min, calc(teamA, teamB, teamC));
                        
                    }
                }
            }
        }

        System.out.println(min != Integer.MAX_VALUE ? min : -1);
    }

    static int calc(int a, int b, int c) {
        int[] res = {a, b, c};
        Arrays.sort(res);

        return res[2] - res[0];
    }
}