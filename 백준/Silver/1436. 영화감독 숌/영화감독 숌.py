# prob : 1436
# https://www.acmicpc.net/problem/1436

import sys

input = sys.stdin.readline

n = int(input().rstrip())

start = 666
while n > 0:
    if "666" in str(start):
        n -= 1
        if n == 0:
            break
    start += 1
print(start)
