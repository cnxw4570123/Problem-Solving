import java.util.Scanner;
import java.util.Arrays;
public class Main {
    static int[] abilities = new int[6];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int total = 0;
        for (int i = 0; i < 6; i++) {
            abilities[i] = sc.nextInt();
            total += abilities[i];
        }
        // Please write your code here.

        int min = Integer.MAX_VALUE;
        int[] groups = new int[3];
        for (int i = 0; i < 5; i++) {
            for (int j = i + 1; j < 6; j++) {
                for (int k = 0; k < 5; k++) {
                    for (int l = k + 1; l < 6; l++) {
                        if(i == k || i == l || j == k || j == l) {
                            continue;
                        }
                        groups[0] = abilities[i] + abilities[j];
                        groups[1] = abilities[k] + abilities[l];
                        groups[2] = total - groups[0] - groups[1];

                        Arrays.sort(groups);

                        min = Math.min(min, groups[2] - groups[0]);
                    }
                }
            }
        }
        System.out.println(min);
    }
}