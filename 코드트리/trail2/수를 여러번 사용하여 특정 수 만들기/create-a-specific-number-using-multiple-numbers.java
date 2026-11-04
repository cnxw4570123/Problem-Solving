import java.util.Scanner;
public class Main {
    static int MAX = 1000;
    static int A, B, C;
    static boolean[] numbers = new boolean[MAX + 1]; 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        A = sc.nextInt();
        B = sc.nextInt();
        C = sc.nextInt();
        // Please write your code here.
        add(0);

        for (int i = MAX; i >= 0; i--) {
            if(numbers[i]) {
                System.out.println(i);
                return;
            }
        }
    }

    static void add(int current) {
        if (current > C || numbers[current]) return;

        numbers[current] = true;
        add(current + A);
        add(current + B);
    }
}