import sys

# print = sys.stdout.write
input = sys.stdin.readline
# f = open("input.txt", "rt")
# input = f.readline

s = []

value = {
    "(": 2,
    "[": 3,
    ")": 2,
    "]": 3,
}


def main():
    ans = 0
    coef = 1
    for exp in input().rstrip():
        if exp in ("(", "["):
            temp = 1
            coef *= value[exp]
            s.append(exp)
        elif exp == ")":
            if not can_make("(", "["):
                print(0)
                return
            ans += temp * coef
            # 계수 낮추기
            coef, temp = coef // value[exp], 0
        elif exp == "]":
            if not can_make("[", "("):
                print(0)
                return
            ans += temp * coef
            coef, temp = coef // value[exp], 0

    # 이래도 남아있으면 0
    if s:
        print(0)
        return
    print(ans)


# [[(]
# ]
def can_make(exp, opposite):
    while s:
        current = s.pop()
        if current == exp:
            return True
        if current == opposite:
            return False
    return False


if __name__ == "__main__":
    main()
