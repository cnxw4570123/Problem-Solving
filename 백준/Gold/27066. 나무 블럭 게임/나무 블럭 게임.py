import sys

# print = sys.stdout.write
input = sys.stdin.readline
# 반드시 아래 두 라인 주석 처리 후 제출
# f = open("input.txt", "rt")
# input = f.readline
# 반드시 위 두 라인 주석 처리 후 제출
CORRECTION = 0.0_000_001
N = int(input().rstrip())


def main():
    numbers = sorted(list(map(lambda x: (int(x), 1), input().split())))
    score = numbers[int((len(numbers) + 1) / 2) - 1][0]

    while numbers:
        idx = int((len(numbers) + 1) / 2) - 1
        criteria = numbers[idx]

        comp = round(criteria[0] / criteria[1] + CORRECTION, 6)
        if comp < score:
            break
        score = comp
        left, right = numbers[0], numbers[-1]
        numbers[-1] = [left[0] + right[0], left[1] + right[1]]
        numbers.pop(0)

    if score == int(score):
        print(int(score))
        return
    print(f"{score:.6f}")


if __name__ == "__main__":
    main()
