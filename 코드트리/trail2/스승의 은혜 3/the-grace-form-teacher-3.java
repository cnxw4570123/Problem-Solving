import java.util.Arrays;
import java.util.Scanner;
public class Main {
    static Present[] presents;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int b = sc.nextInt();
        presents = new Present[n];

        for(int i = 0; i < n; i++){
            presents[i] = new Present(sc.nextInt(), sc.nextInt());
        }
        // Please write your code here.
        Arrays.sort(presents, 
        (p1, p2) -> Integer.compare(p1.deliveryFee + p1.price, p2.deliveryFee + p2.price)
        );

        int ans = 0;
        for (int i = 0; i < n; i++) {
            int cost = presents[i].price / 2 + presents[i].deliveryFee;
            if (cost > b) continue;

            int counts = 1;
            for (int j = 0; j < n; j++) {
                if(i == j) continue;

                int targetCost = presents[j].price + presents[j].deliveryFee;

                if(cost + targetCost > b) continue;
                cost += targetCost;
                counts++;
            }

            ans = Math.max(counts, ans);
        }
        System.out.println(ans);

    }

    static class Present {
        int price;
        int deliveryFee;

        Present(int price, int deliveryFee) {
            this.price = price;
            this.deliveryFee = deliveryFee;
        }
    }
}