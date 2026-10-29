import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int[] counts = new int[10];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        // Please write your code here.

        int ans = 0;
        for (int current = x; current < y + 1; current++) {
            int temp = current;
            Arrays.fill(counts, 0);

            while (temp > 0) {
                counts[temp % 10]++;
                temp /= 10;
            }
            

            ArrayList<Integer> numbers = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                if(counts[i] > 0) numbers.add(i);
            }

            if(numbers.size() != 2) continue;
            if(counts[numbers.get(0)] == 1 || counts[numbers.get(1)] == 1) ans++;
        }
        System.out.println(ans);
    }

    
}