import sys
from collections import deque

# print = sys.stdout.write
input = sys.stdin.readline



MAX = 86401
A, B, N = map(int, input().split())
sangmin_q, jisoo_q = deque(), deque()


def main():
    global sangmin_q, jisoo_q

    for _ in range(N):
        order = input().split()
        if order[1] == "B":
            sangmin_q.append([int(order[0]), int(order[2])])
            continue
        jisoo_q.append([int(order[0]), int(order[2])])

    s_cnt, j_cnt = [], []

    present = 0
    while sangmin_q and jisoo_q:
        present += 1

        if sangmin_q[0][0] <= jisoo_q[0][0]:
            sangmin_q[0][1] -= 1
            sangmin_q[0][0] += A

            if not sangmin_q[0][1]:
                time, _ = sangmin_q.popleft()
                if sangmin_q and sangmin_q[0][0] < time:
                    sangmin_q[0][0] = time
            s_cnt.append(present)
            continue

        jisoo_q[0][1] -= 1
        jisoo_q[0][0] += B

        if not jisoo_q[0][1]:
            time, _ = jisoo_q.popleft()
            if jisoo_q and jisoo_q[0][0] < time:
                jisoo_q[0][0] = time
        j_cnt.append(present)

    present += 1

    while sangmin_q:
        _, cnt = sangmin_q.popleft()
        s_cnt += [i for i in range(present, present + cnt)]
        present = cnt + present

    while jisoo_q:
        _, cnt = jisoo_q.popleft()
        j_cnt += [i for i in range(present, cnt + present)]
        present = cnt + present

    print(len(s_cnt))
    print(*s_cnt)
    print(len(j_cnt))
    print(*j_cnt)


if __name__ == "__main__":
    main()
