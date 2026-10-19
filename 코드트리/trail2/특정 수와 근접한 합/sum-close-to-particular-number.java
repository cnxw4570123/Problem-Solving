import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int[] arr = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            sum += arr[i];
        }
        // Please write your code here.

        int min = Integer.MAX_VALUE;
        

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = sum - arr[i] - arr[j];
                min = Math.min(min, Math.abs(temp - s));
            }
        }

        System.out.println(min);
    }
}