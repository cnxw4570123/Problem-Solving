const fs = require('fs');
const filePath = process.platform === 'linux' ? '/dev/stdin' : './input.txt';

// const input = fs.readFileSync(0, 'utf-8').toString().trim().split('\n');
const input = fs.readFileSync(filePath).toString().trim().split('\n');

const arr = input[1].split(' ').map(e => +e).sort((a, b) => a - b);

let criteria = Infinity, ans;
for (let i = 0; i < arr.length; i++) {
    find(arr[i], i + 1);
}
console.log(ans.sort((a, b) => a - b).join(' '));

function find(fixed, start) {
    let end = arr.length - 1;

    let currentSum;

    while (start < end) {
        currentSum = fixed + arr[start] + arr[end];
        if (criteria > Math.abs(currentSum)) {
            criteria = Math.abs(currentSum);
            ans = [fixed, arr[start], arr[end]]
        }
        if (currentSum < 0) {
            start += 1
        } else if (currentSum === 0) {
            break;
        } else {
            end -= 1;
        }
    }
}