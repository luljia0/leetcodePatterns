==== has high priority than logical operator==

https://leetcode.com/discuss/interview-question/3695233/all-types-of-patterns-for-bits-manipulations-and-how-to-use-it

1. n & 1 is n itself
2. n ｜ 0 is itself
3. n % 2 to decide whether it is 0 or 1

**NOT ( ~ ):** The NOT operator, represented by the tilde (`~`) symbol, flips the bits of a binary number. It changes each 0 to 1 and each 1 to 0. This operator is useful for ==inverting the bits of a number.==

**AND ( & ):** BThe AND operator, denoted by the ampersand (`&`) symbol, compares two binary numbers bit by bit. It sets each bit to 1 only if both corresponding bits are also 1. Otherwise, it sets the bit to 0. The AND operator is commonly used for ==masking and checking the presence of specific bits.==

**OR ( | ):**The OR operator, represented by the vertical bar (`|`) symbol, compares two binary numbers bit by bit. It sets each bit to 1 if either of the corresponding bits is 1. If both bits are 0, it sets the resulting bit to 0. The OR operator is useful for ==combining or merging bits.==

**XOR ( ^ ):** The XOR operator, denoted by the caret (`^`) symbol, compares two binary numbers bit by bit. It sets each bit to 1 if exactly one of the corresponding bits is 1. If both bits are the same (either both 0 or both 1), it sets the resulting bit to 0. The XOR operator is often used for ==toggling or swapping bits.==

![image-20240212083903503](/Users/jiaolulu/Library/Application Support/typora-user-images/image-20240212083903503.png)

**Left Shift ( << ):** The left shift operator (`<<`) shifts the bits of a binary number to the left by a specified number of positions. This operation is equivalent to multiplying the number by 2 raised to the power of the shift amount. It is frequently used for efficient ==multiplication or creating space for additional bits.==
1 << 1 = 2 = 21
1 << 2 = 4 = 2^2 1 << 3 = 8 = 2^3
1 << 4 = 16 = 2^4
…
1 << n = 2^n

**Right Shift ( >> )**:The right shift operator (`>>`) shifts the bits of a binary number to the right by a specified number of positions. This operation is equivalent to dividing the number by 2 raised to the power of the shift amount. It is commonly used for ==efficient division or extracting specific bits.==
4 >> 1 = 2
6 >> 1 = 3
5 >> 1 = 2
16 >> 4 =  16/2^4 = 1

### extracting least significant bits(checking the last digit)

1. modular

   ```java
   n % 2 == 1
   n >>= 1
     
   
    //pop operation:
   pop = x % 10;
   x /= 10;
   
   //push operation:
   temp = rev * 10 + pop;
   rev = temp;
   ```

2. AND

```java
n & 1 == 1
n >>= 1
```

### flip least significant bit (1->0)

The key idea here is to realize that for any number nn*n*, doing a bit-wise AND of nn*n* and n−1n - 1*n*−1 flips the least-significant 111-bit in nn*n* to 000.

```
n &= (n - 1)

n = ~n
```

### combine 2 numbers

```
n = 2^a | 2^b (different number with different digits)
```

for example, 001 | 10 = 011

### Tips: start with XOR

How to start? There is an interview tip for bit manipulation problems: if you don't know how to start, start by computing XOR for your input data. Strangely, that helps out for quite a lot of problems, [Single Number II](https://leetcode.com/articles/single-number-ii/), [Single Number III](https://leetcode.com/articles/single-number-iii/), [Maximum XOR of Two Numbers in an Array](https://leetcode.com/articles/maximum-xor-of-two-numbers-in-an-array/), [Repeated DNA Sequences](https://leetcode.com/articles/repeated-dna-sequences/), [Maximum Product of Word Lengths](https://leetcode.com/articles/maximum-product-of-word-lengths/), etc.

![image-20240213193744685](/Users/jiaolulu/Library/Application Support/typora-user-images/image-20240213193744685.png)

### Sum and subtract 2 postive numbers

```java
// sum
sumWithoutCarry = x ^ y;
carry = ( x & y) << 1;

// difference
diffWithoutBorr = x ^ y;
borrow = ((~x) & y) << 1;
```

