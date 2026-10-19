import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        // Please write your code here.

        int max = -1;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if(doesCarryHappens(arr[i], arr[j], arr[k])){
                        continue;
                    }
                    max = Math.max(max, arr[i] + arr[j] + arr[k]);
                }
            }
        }
        System.out.println(max);
    }

    static boolean doesCarryHappens(int x, int y, int z) {
        while (x != 0 || y != 0 || z != 0) {
            int modX = x % 10, modY = y % 10, modZ = z % 10;
            if(modX + modY + modZ >= 10){
                return true;
            }

            x /= 10;
            y /= 10;
            z /= 10;
        }
        
        return false;
    }
}