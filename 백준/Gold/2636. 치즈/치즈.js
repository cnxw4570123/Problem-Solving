const fs = require('fs');
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt';

// const input = fs.readFileSync(0, 'utf-8').toString().trim().split('\n');
const input = fs.readFileSync(filePath).toString().trim().split('\n');
const DIRECTIONS = [[-1, 0], [1, 0], [0, -1], [0, 1]];

const [N, M] = input[0].split(' ').map(e => +e);
_map = input.slice(1).map(e => e.split(' ').map(e => +e));

let cheeseCount = 0;
for (const row of _map) {
    cheeseCount += row.filter(c => c === 1).length;
}
const v = Array.from(Array(N), () => Array(M).fill(false));
const q = [[0, 0]];
v[0][0] = true;

console.log(find(_map).join('\n'));

function findCheese(arr, board) {
    let cheeseQ = [...arr];
    q.length = 0;
    while (cheeseQ.length) {
        let [y, x] = cheeseQ.shift();

        for (const [dy, dx] of DIRECTIONS) {
            let ny = dy + y, nx = dx + x;
            if (canNotVisit(ny, nx)) {
                continue;
            }
            v[ny][nx] = true;
            if (board[ny][nx] === 1) {
                q.push([ny, nx]);
                continue;
            }
            cheeseQ.push([ny, nx]);
        }
    }
}

function find(board) {
    let t = -1, lastOneHour, done = false;

    do {
        t++;
        newBoard = Array.from(board, arr => Array.from(arr));
        findCheese(q, board);
        cheeseCount -= q.length;
        for (const [r, c] of q) {
            newBoard[r][c] = 0;
        }

        if (cheeseCount === 0 && !done) {
            lastOneHour = q.length;
            done = true;
        }
        board = newBoard;
    } while (q.length);
    return [t, lastOneHour];
}

function canNotVisit(ny, nx) {
    return ny >= N || nx >= M || ny < 0 || nx < 0 || v[ny][nx];
}