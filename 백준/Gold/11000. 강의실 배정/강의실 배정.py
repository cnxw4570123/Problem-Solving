import sys
import heapq

input = sys.stdin.readline
# print = sys.stdout.write


N = int(input().rstrip())
lectures = [list(map(int, input().rstrip().split())) for _ in range(N)]

lectures.sort(key=lambda lecture: (lecture[0], lecture[1]))


pq = []
ans = 0
for lecture in lectures:
    while pq and pq[0] <= lecture[0]:
        heapq.heappop(pq)
    heapq.heappush(pq, lecture[1])
    ans = max(ans, len(pq))

print(ans)
