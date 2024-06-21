const fs = require('fs');
const input = fs.readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
    .toString().trim().split('\n');

input.forEach(element => {
    element = element.split(' ');
    let a = parseInt(element[0]);
    let b = parseInt(element[1]);
    console.log(a + b);
});

