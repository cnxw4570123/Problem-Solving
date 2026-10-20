import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        // Please write your code here.

        int ans = n;
        for (int i = 0; i < n; i++) {
            for (int j = 2; j < n - i + 1; j++) {
                int sum = 0;
                for (int k = 0; k < j; k++) {
                    sum += arr[i + k];
                }
                
                if (sum % j != 0) continue;
                int avg = sum / j; 
               
                for (int k = 0; k < j; k++) {
                    if(arr[i + k] == avg) {
                        ans++;
                        break;
                    }
                }
            }
        }

        System.out.println(ans);
    }
}