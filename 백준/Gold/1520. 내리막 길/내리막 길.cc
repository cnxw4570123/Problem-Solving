// 1890 점프
#include <algorithm>
#include <cstring>
#include <iostream>
using namespace std;

#define FastIO                                                                 \
    ios_base::sync_with_stdio(false), cin.tie(nullptr), cout.tie(nullptr)
#define MAX 500
#define endl '\n'
#define All(v) v.begin(), v.end()
typedef long long ll;
typedef pair<int, int> pii;

pii direction[] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
/*
dp[a][b][0] = c -> (a,b)에서 아래쪽으로 가는 방법은 c개이다.
dp[a][b][1] = c -> (a,b)에서 오른쪽으로 가는 방법은 c개이다.
*/
int M, N, boards[MAX][MAX];
int dp[MAX][MAX];

int DFS(int y, int x) {
    if (y == M - 1 && x == N - 1) {
        return 1;
    }
    if (dp[y][x] != -1) return dp[y][x];
    dp[y][x] = 0;
    for (auto  d: direction) {
        int ny = d.first + y, nx = d.second + x;
        if (ny >= M || nx >= N || ny < 0 || nx < 0 ||
            boards[ny][nx] >= boards[y][x])
            continue;
        dp[y][x] += DFS(ny, nx);
    }
    return dp[y][x];
}

int main() {
    FastIO;
    cin >> M >> N;
    for (int i = 0; i < M; i++) {
        for (int j = 0; j < N; j++) {
            cin >> boards[i][j];
        }
    }
    memset(dp, -1, sizeof(dp));
    DFS(0, 0);
    
    cout << dp[0][0];
    return 0;
}
