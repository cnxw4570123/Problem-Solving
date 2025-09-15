import sys


# print = sys.stdout.write
input = sys.stdin.readline

N = int(input().rstrip())

counsellings = sorted([tuple(map(int, input().split())) for _ in range(N)])


def main():
    cnt, end = 0, 0
    # end = 6
    # (4, 5)
    for st, en in counsellings:
        # 기존 회의 종료 시간 이후에 시작하는 회의 -> 가능
        if end <= st:
            cnt += 1
            end = en
            continue

        if end > en:
            end = en

    print(cnt)


if __name__ == "__main__":
    main()
