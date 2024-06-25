const fs = require('fs');

const input = fs.readFileSync(0, 'utf-8').toString().trim().split('\n');
const DIRECTIONS = [[0, -1], [-1, 0], [0, 1]];

const [N, M, D] = input[0].trim().split(' ').map(e => +e);
const _map = input.slice(1).map(e => Array.from(e.trim().split(' '), (num) => +num));

let points = Array.from({ length: 1 << M }, () => 0);

function find_max() {
    for (let archer = (1 << M) - 1; archer; archer--) {
        const bin = archer.toString(2);
        if (bin.match(/1/g).length !== 3) {
            continue;
        }
        points[archer] = process(bin);
    }
}

function process(archer) {
    let point = 0;
    board = Array.from(_map, (arr) => Array.from(arr));
    while (board.length) {
        let v = new Set();
        for (let idx = 0; idx < archer.length; idx++) {
            if (archer[idx] !== '1') {
                continue;
            }
            v.add(BFS(board.length - 1, (M - archer.length) + idx, board));
            /*
            11100 -> 0, 1, 2
            ...
            00111 -> 2, 3, 4
            */
        }

        for (let [r, c] of v) {
            if ((r === -1 && c === -1) || !board[r][c]) {
                continue;
            }
            board[r][c] = 0;
            point += 1;
        }
        board.pop();
        v.clear();
    }
    return point;
}

function BFS(y, x, board) {
    const q = [[y, x, 1]];
    const v = Array.from({ length: y + 1 }, () => Array(M).fill(false));
    v[y][x] = true;
    while (q.length) {
        let [y, x, dist] = q.shift();
        if (board[y][x] === 1) {
            return [y, x];
        }
        for (let [dy, dx] of DIRECTIONS) {
            let ny = y + dy, nx = x + dx;
            if (nx >= M || ny < 0 || nx < 0 || dist + 1 > D || v[ny][nx]) {
                continue;
            }
            q.push([ny, nx, dist + 1]);
            v[ny][nx] = true;
        }
    }
    return [-1, -1];
}
find_max();
console.log(points.reduce((a, b) => Math.max(a, b)));
