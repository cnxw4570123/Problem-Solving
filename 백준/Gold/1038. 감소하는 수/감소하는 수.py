import sys

# print = sys.stdout.write
input = sys.stdin.readline
# 반드시 아래 두 라인 주석 처리 후 제출
# f = open("input.txt", "rt")
# input = f.readline
# 반드시 위 두 라인 주석 처리 후 제출

N = int(input().rstrip())
decreasing_numbers = []


def find(num_str):
    if len(num_str) > 10:
        return

    # : 은 ascii 코드 58, 0 ~ 9는 48부터 57
    left = num_str[-1] if num_str else ":"
    # 숫자를 나타내는 아스키 코드 중 현재 끝나는 수보다 작은 것만 취함
    for i in range(48, ord(left)):
        tmp = num_str + chr(i)
        decreasing_numbers.append(int(tmp))
        find(tmp)


def main():
    find("")
    decreasing_numbers.sort()
    # 0번째 수는 0이므로 전체 개수에서 1개 빼야함
    _max = len(decreasing_numbers) - 1
    if _max < N:
        print(-1)
        return
    print(decreasing_numbers[N])


if __name__ == "__main__":
    main()
