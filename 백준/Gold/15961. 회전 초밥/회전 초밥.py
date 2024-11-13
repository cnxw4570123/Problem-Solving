import sys

# print = sys.stdout.write
input = sys.stdin.readline


N, D, K, C = map(int, input().split())
sushi = [int(input().rstrip()) for _ in range(N)]
types = dict()


def main():
    ans = 0

    for s in sushi[:K]:
        if types.get(s, 0) == 0:
            types[s] = 1
            continue
        types[s] += 1

    head = K
    while True:
        # 먹은 연속된 초밥 가운데 쿠폰으로 받을 수 있는 초밥이 없으면 쿠폰으로 한 개 더 먹음
        if not types.get(C, 0):
            ans = max(ans, len(types) + 1)
        else:
            ans = max(ans, len(types))

        if not types.get(sushi[head], 0):
            types[sushi[head]] = 1
        else:
            types[sushi[head]] += 1

        # 0부터 시작
        tail = (head - K + N) % N
        if types.get(sushi[tail]) == 1:
            types.pop(sushi[tail])
        else:
            types[sushi[tail]] -= 1

        head = (head + 1) % N

        if head == K:
            break

    print(ans)


if __name__ == "__main__":
    main()
