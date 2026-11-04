import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        // Please write your code here.
        ArrayList<Integer> numbers = new ArrayList<>();

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            arr[i] *= 2;

            for (int j = 0; j < n; j++) {
                numbers.clear();
                for (int k = 0; k < n; k++) {
                   if(j == k) continue;
                   numbers.add(arr[k]);
                }
                int score = 0;
                for (int k = 0; k < numbers.size() - 1; k++) {
                    score += Math.abs(numbers.get(k) - numbers.get(k + 1));
                }
                ans = Math.min(ans, score);
            }
            arr[i] /= 2;
        }
        System.out.println(ans);
    }
}

