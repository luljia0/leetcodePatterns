### prerequisite

A permutation of *n* elements is a rearrangement of those elements. When analyzing a permutation, we can decompose it into one or more cycles.

### identify

given an array of number ranging from 1-n or 0 - n-1, find the missing/duplicate number

### algo

1. First iteration: place the number in the right position (same as the index) by in-place swapping. iterate the array and swap the number until the current position has the right number
2. second iteration: find the duplicate/missing one

### template





### complexity

Time: O(n)

Space: O(1)

### problem

1. Introduction [emre.me](https://emre.me/coding-patterns/cyclic-sort/)
2. Cyclic Sort (easy) [Geeksforgeeks](https://www.geeksforgeeks.org/sort-an-array-which-contain-1-to-n-values-in-on-using-cycle-sort/)
3. Find the Missing Number (easy) [Leetcode](https://leetcode.com/problems/missing-number/)
4. Find all Missing Numbers (easy) [Leetcode](https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/)
5. Find the Duplicate Number (easy) [Leetcode](https://leetcode.com/problems/find-the-duplicate-number/)(can't modify the original array)
6. Find all Duplicate Numbers (easy) [Leetcode](https://leetcode.com/problems/find-all-duplicates-in-an-array/)
7. Problem Challenge 1: Find the Corrupt Pair (easy) [TheCodingSimplified](https://thecodingsimplified.com/find-currupt-pair/)
8. Problem Challenge 2: Find the Smallest Missing Positive Number (medium) [Leetcode](https://leetcode.com/problems/first-missing-positive/)
9. Problem Challenge 3: Find the First K Missing Positive Numbers (hard) [TheCodingSimplified](https://thecodingsimplified.com/find-the-first-k-missing-positive-number/)