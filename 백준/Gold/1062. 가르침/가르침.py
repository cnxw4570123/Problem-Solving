import sys

# print = sys.stdout.write
input = sys.stdin.readline
# 반드시 아래 두 라인 주석 처리 후 제출
# f = open("input.txt", "rt")
# input = f.readline
# 반드시 위 두 라인 주석 처리 후 제출


N, K = map(int, input().split())

ans = 0
alphabet, words = set(), []
for i in range(N):
    word = set(input().rstrip()[4:-4])
    alphabet.update(word)

    words.append(word)

alphabet = list(alphabet)
5
known = set("atcin")  # 최소 anta, tica는 알아야함


def learn(known, cnt, idx):
    global ans
    # 배울 수 있는 글자 다 배우거나
    # 마지막 글자까지 배웠다면
    if cnt == K or idx == len(alphabet):
        same = 0
        for i in range(N):
            if words[i].issubset(known):
                same += 1
        ans = max(same, ans)
        return

    for i in range(idx, len(alphabet)):
        alp = alphabet[i]
        if alp in known:  # 배운 글자면 패스
            continue

        known.add(alp)
        learn(known, cnt + 1, i + 1)
        known.remove(alp)


def main():
    global K
    if K < 5:
        print(0)
        return

    K -= 5  # 이미 배운 단어 5개는 빼야함
    learn(known, 0, 0)
    print(ans)


if __name__ == "__main__":
    main()
