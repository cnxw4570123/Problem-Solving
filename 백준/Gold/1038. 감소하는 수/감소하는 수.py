import sys

# print = sys.stdout.write
input = sys.stdin.readline
# 반드시 아래 두 라인 주석 처리 후 제출
# f = open("input.txt", "rt")
# input = f.readline
# 반드시 위 두 라인 주석 처리 후 제출

N = int(input().rstrip())
decreasing_numbers = [[] for _ in range(11)]
v = set()
_max = 0


def is_num_decreasing(num):
    for i in range(1, len(num)):
        if num[i - 1] <= num[i]:
            return False

    return True


def find(num_str, num_len):
    global _max
    if len(num_str) > 10:
        return

    # 0 ~ 9의 아스키 코드
    for i in range(48, 58):
        tmp = num_str + chr(i)
        if is_num_decreasing(tmp) and tmp not in v:
            decreasing_numbers[num_len + 1].append(int(tmp))
            v.add(tmp)
            find(tmp, num_len + 1)
            _max += 1


def main():
    global _max
    find("", 0)

    # 0번째 수는 0이므로 전체 개수에서 1개 빼야함
    _max -= 1
    if N > _max:
        print(-1)
        return
    cnt, ans = 0, -1
    for i in range(1, 11):
        current = len(decreasing_numbers[i])
        if cnt + current <= N:
            cnt += current
            continue

        ans = decreasing_numbers[i][N - cnt]
        break
    if ans == -1:
        print(decreasing_numbers[10][0])
        return
    print(ans)


if __name__ == "__main__":
    main()
