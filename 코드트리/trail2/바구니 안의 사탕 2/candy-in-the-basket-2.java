import java.util.Scanner;
public class Main {
    static int MAX = 100;
    static int[] candies = new int[MAX + 1];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int candy = sc.nextInt();
            int position = sc.nextInt();
            candies[position] += candy;
        }
        // Please write your code here.

        int max = 0;
        for (int i = 0; i < MAX + 1; i++) {
            int sum = 0;
            for (int j = Math.max(i - k, 0); j < Math.min(MAX, i + k) + 1; j++) {
                sum += candies[j];
            }

            max = Math.max(max, sum);
        }
        System.out.println(max);
    }
}