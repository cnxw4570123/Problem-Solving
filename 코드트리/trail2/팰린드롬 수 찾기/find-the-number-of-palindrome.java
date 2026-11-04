import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        // Please write your code here.

        int ans = 0;
        for (int i = x; i < y + 1; i++) {
            String current = String.valueOf(i);

            int start = 0, end = current.length() - 1;
            boolean isPalindrome = true;
            while(start <= end){
                if(current.charAt(start) != current.charAt(end)) {
                    isPalindrome = false;
                    break;
                }

                start++;
                end--;
            }

            if (isPalindrome) ans++;
        }
        System.out.println(ans);
    }
}