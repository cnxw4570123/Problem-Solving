import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n + 1];

        for (int i = 1; i < n + 1; i++){
            arr[i] = arr[i - 1] + sc.nextInt();
        }
            
        // Please write your code here.

        int max = arr[1];
        for (int i = 1; i <= n - k + 1; i++) {
            max = Math.max(max, arr[i + k - 1] - arr[i - 1]);
        }
        System.out.println(max);
    }
}

/*
0 9 10 12 16 23 24
n = 6, k = 2

i = 1 => arr[1 + 2 - 1] - arr[1 - 1]
i = n - k => arr[n - 1] - arr[n - k - 1]

*/