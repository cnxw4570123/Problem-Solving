import sys
from collections import defaultdict

input = sys.stdin.readline
# print = sys.stdout.write

N = int(input().rstrip())
words = [input().rstrip() for _ in range(N)]
alpha = defaultdict(int)
numbers = []
for word in words:
    for i in range(len(word)):
        alpha[word[i]] += 10 ** (len(word) - i - 1)

for num in alpha.values():
    numbers.append(num)

numbers.sort(reverse=True)

sum, count = 0, 9
for number in numbers:
    sum += count * number
    count -= 1

print(sum)
