var fs = require('fs');
var input = fs.readFileSync(process.platform === 'linux' ? '/dev/stdin' : './input.txt')
    .toString().split('\n');

console.log(input[1].split(' ').filter(elem => elem === input[2]).length);