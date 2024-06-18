import sys

# print = sys.stdout.write
input = sys.stdin.readline
# 반드시 아래 두 라인 주석 처리 후 제출
# f = open("input.txt", "rt")
# input = f.readline
# 반드시 위 두 라인 주석 처리 후 제출

T = int(input().rstrip())


def check(number, prefix):
    for i in range(len(number)):
        if number[:i] in prefix:
            return True
    return False


def main():
    ans = []
    for _ in range(T):
        N = int(input().rstrip())
        numbers = sorted([input().rstrip() for _ in range(N)])
        prefix = set()
        for number in numbers:
            if check(number, prefix):
                ans.append("NO")
                break
            prefix.add(number)
        else:
            ans.append("YES")

    print("\n".join(ans))


if __name__ == "__main__":
    main()
