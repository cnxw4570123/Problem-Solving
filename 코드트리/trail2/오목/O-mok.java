import java.util.Scanner;

public class Main {
    static int MAX = 19;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] arr = new int[19][19];
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        // Please write your code here.

        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                if(arr[i][j] == 0){
                    continue;
                }
                
                // ↘
                if(i < MAX - 4 && j < MAX - 4) {
                    if(
                        arr[i][j] == arr[i + 1][j + 1] &&
                        arr[i + 1][j + 1] == arr[i + 2][j + 2] &&
                        arr[i + 2][j + 2] == arr[i + 3][j + 3] &&
                        arr[i + 3][j + 3] == arr[i + 4][j + 4]  
                    ) {
                        System.out.println(arr[i][j]);
                        System.out.printf("%d %d", i + 2 + 1, j + 2 + 1);
                        return;
                    }
                }

                // ↙
                if(i < MAX - 4 && j > 3) {
                    if(
                        arr[i][j] == arr[i + 1][j - 1] &&
                        arr[i + 1][j - 1] == arr[i + 2][j - 2] &&
                        arr[i + 2][j - 2] == arr[i + 3][j - 3] &&
                        arr[i + 3][j - 3] == arr[i + 4][j - 4]
                    ) {
                        System.out.println(arr[i][j]);
                        System.out.printf("%d %d", i + 2 + 1, j - 2 + 1);
                        return;
                    }
                }

                // ↓
                if(i < MAX - 4) {
                    if(
                        arr[i][j] == arr[i + 1][j] &&
                        arr[i + 1][j] == arr[i + 2][j] &&
                        arr[i + 2][j] == arr[i + 3][j] &&
                        arr[i + 3][j] == arr[i + 4][j]
                    ) {
                        System.out.println(arr[i][j]);
                        System.out.printf("%d %d", i + 2 + 1, j + 1);
                        return;
                    }
                }

                // →
                if(j < MAX - 4) {
                    if(
                        arr[i][j] == arr[i][j + 1] &&
                        arr[i][j + 1] == arr[i][j + 2] &&
                        arr[i][j + 2] == arr[i][j + 3] &&
                        arr[i][j + 3] == arr[i][j + 4]
                    ) {
                        System.out.println(arr[i][j]);
                        System.out.printf("%d %d", i + 1, j + 2 + 1);
                        return;
                    }
                }
            }
        }
        System.out.println(0);
    }
    

}
/*
19 * 19 
*/