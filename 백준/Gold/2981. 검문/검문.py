import sys

# print = sys.stdout.write
input = sys.stdin.readline
# 반드시 아래 두 라인 주석 처리 후 제출
# f = open("input.txt", "rt")
# input = f.readline
# 반드시 위 두 라인 주석 처리 후 제출

N = int(input().rstrip())

arr = sorted([int(input().rstrip()) for _ in range(N)], reverse=True)


def find_div(gcd):
    result = set([gcd])
    for div in range(2, int(gcd**0.5) + 1):
        if gcd % div == 0:
            result.add(div)
            result.add(gcd // div)
    return result


def euclidean(a, b):
    if a < b:  # 항상 a > b를 보장
        a, b = b, a

    while b:
        a, b = b, a % b
    return a


def main():
    gcd = arr[0] - arr[1]
    for i in range(1, N - 1):
        # 제공되는 a,b에 대해 a > b를 보장해줄 수 없음.
        gcd = euclidean(gcd, arr[i] - arr[i + 1])

    print(" ".join(map(str, sorted(find_div(gcd)))))


if __name__ == "__main__":
    main()
