import java.io.*;
import java.util.*;


public class Main {
    static int N, K, start = 0, zero = 0;

    static int[] dy = {0, -1, 0, 1}, dx = {1, 0, -1, 0};
    static Conveyor[][] belt;

    static List<Conveyor> robotInIt = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        belt = new Conveyor[3][N + 1]; // 0 0은 안씀
        int idx;
        st = new StringTokenizer(br.readLine());
        for (idx = 1; idx < N + 1; idx++) {
            belt[1][idx] = new Conveyor(1, idx, Integer.parseUnsignedInt(st.nextToken()));
        }
        for (int i = idx - 1; i > 0; i--) {
            belt[2][i] = new Conveyor(2, i, Integer.parseInt(st.nextToken()));
        }

        while (zero < K) {
            rotateBelt();
            moveRobot();
            if (belt[1][1].durability > 0 && !belt[1][1].robot) {
                belt[1][1].robot = true;
                belt[1][1].reduceDurability();
                robotInIt.add(belt[1][1]);
            }
            zero = countZero();
            start++;
        }
        bw.write(start + "");
        bw.flush();
        bw.close();
        br.close();
    }


    static void rotateBelt() {
        int idx = 0, y = 2, x = 1;
        Conveyor temp = belt[2][1];

        while (idx < 3) {
            int ny = y + dy[idx], nx = x + dx[idx];
            if (ny < 1 || nx < 1 || ny > 2 || nx > N) {
                idx++;
                continue;
            }
            belt[y][x] = belt[ny][nx];
            belt[y][x].setPos(y, x);
            if (y == 1 && x == N && belt[ny][nx].robot) {
                belt[y][x].robot = false;
                robotInIt.remove(belt[y][x]);
            }
            y = ny;
            x = nx;
        }
        belt[1][1] = temp;
        belt[1][1].setPos(1, 1);
    }

    static void moveRobot() {
        ListIterator<Conveyor> conveyorListIterator = robotInIt.listIterator();
        while (conveyorListIterator.hasNext()) {
            Conveyor current = conveyorListIterator.next();
            int next = current.pos[1] + 1;
            if (next <= N && !belt[1][next].robot && belt[1][next].durability >= 1) {
                current.robot = false;
                belt[1][next].reduceDurability();
                if (next == N) {
                    conveyorListIterator.remove();
                    continue;
                }
                belt[1][next].robot = true;
                conveyorListIterator.set(belt[1][next]);
            }
        }
    }



    static int countZero() {
        return (int) Arrays.stream(belt)
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .mapToInt(value -> value.durability)
                .filter(value -> value <= 0)
                .count();
    }
}


class Conveyor {
    int[] pos;
    int durability;
    boolean robot;

    public Conveyor(int y, int x, int durability) {
        this.pos = new int[]{y, x};
        this.durability = durability;
    }

    public void setPos(int y, int x) {
        this.pos = new int[]{y, x};
    }

    @Override
    public String toString() {
        return "Conveyor{" +
                "pos=" + Arrays.toString(pos) +
                ", durability=" + durability +
                ", robot=" + robot +
                '}';
    }

    public void reduceDurability() {
        this.durability--;
    }
}