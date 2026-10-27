import java.util.Scanner;
import java.util.*;

public class Main {
    static List[] cheeseEatens;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int D = sc.nextInt();
        int S = sc.nextInt();

        cheeseEatens = new List[M + 1];
        for (int i = 0; i < M + 1; i++) {
            cheeseEatens[i] = new ArrayList<CheeseEaten>();
        }

        for (int i = 0; i < D; i++) {
            int eatPerson = sc.nextInt();
            int eatCheese = sc.nextInt();
            int eatTime = sc.nextInt();

            cheeseEatens[eatCheese].add(new CheeseEaten(eatPerson, eatTime));
        }

        

        int[] sickPerson = new int[S];
        int[] sickTime = new int[S];
        for (int i = 0; i < S; i++) {
            sickPerson[i] = sc.nextInt();
            sickTime[i] = sc.nextInt();
        }

        // Please write your code here.
        int ans = 0;
        for (int i = 1; i < M + 1; i++) { // i번 치즈가 상했다고 가정.
            int medicine = 0;

            boolean satisfy = true;
            for (int j = 0; j < S; j++) { // sickPerson[j]는 반드시 i번 치즈를 먹었고, eatTime 이후에 아프다고 해야함.
                boolean contains = false;
                for (int k = 0; k < cheeseEatens[i].size(); k++) {
                    CheeseEaten ce = (CheeseEaten)cheeseEatens[i].get(k);
                    if(sickPerson[j] == ce.who && ce.time < sickTime[j]) {
                        contains = true;
                        break;
                    }
                }

                if(!contains) {
                    satisfy = false;
                    break;
                }
            }

            if(!satisfy) {
                continue;
            }

            Set<Integer> people = new HashSet<>();
            for (int j = 0; j < cheeseEatens[i].size(); j++) {
                people.add(((CheeseEaten)cheeseEatens[i].get(j)).who);
            }
            ans = Math.max(ans, people.size());
            
        }
        System.out.println(ans);
    }

    static class CheeseEaten {
        int who;
        int time;

        CheeseEaten(int who, int time) {
            this.who = who;
            this.time = time;
        }

        @Override
        public String toString(){
            return String.format("{who = %d, time = %d}", who, time);
        }
    }
}
