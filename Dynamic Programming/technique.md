1. 1-D dynamic programming

2. The key to dynamic programming is to break the problem into subproblem and solve them recursively, and then find out which variables you should store and keep updated

3. include top-down and bottom up

4. try the brute force solution first using the tree and try to find where we can do the dynamic programming。 The thinking route should be decision-cache-dp

5. for 2d dynamic programming, use grid

6. starts from the part that has no duplicate

## dynamic programming 

### Conditions

1. The problem can be divided into sub-problems, and its optimal solution can be constructed from optimal solutions of the sub-problems.
2. The sub-problems from 1) overlap.

### solution components

1. a single/multipe dimentional array
2. a reoccrence relation
3. base case

## DP == DFS + memoization + pruning

1. ==top-down:==  this is basically DFS + memoization as we have seen [memoization](https://algo.monster/problems/memoization_intro). We split large problems and **recursively** solve smaller sub-problems.

   - The order of computing sub-problems doesn't matter.

2. ==Bottom-up==

   Bottom-up: we try to solve sub-problems and then use their solutions to find the solutions to bigger sub-problems. This is usually done in a tabular form.

   - Easier to analyze the time complexity (since it's just the time to fill the table)
   - No recursion, and thus no system stack overflow—although not a huge concern for normal coding interviews.

   Key:

   1. base case
   2. recurrence relation 
   3. order of iteration
   4. edge case: dummy node

## When to use dynamic programming

- The **maximum/longest**, **minimal/shortest** **value/cost/profit** you can get from doing operations on a **sequence**.
- How many ways there are to do something. This can often be solved by DFS + memoization, i.e., top-down dynamic programming.
- Is it possible to accomplish something? Often this kind of problem will ask you to return a boolean.

## Greedy Algorithm vs. Dynamic Programming

The only way you can really distinguish between the two is to check whether or not a greedy solution works by testing a few ideas. If you can reason with logic or counterexamples why some greedy approaches aren't optimal, then the solution will most likely be dynamic programming.

## Divide and Conquer vs. Dynamic Programming

Both [Divide and Conquer](https://algo.monster/problems/divide_and_conquer_intro) and dynamic programming break the original problem down into multiple sub-problems. The difference is that in dynamic programming, the sub-problems **overlap**, whereas in divide and conquer, they don't.

## Grid DP

### **Identifying Grid DP Problems:**

1. **Presence of Grids/Matrices:**
2. The problem should typically fit within one of the three primary categories that DP is renowned for:
   - Optimization problems
   - Counting problems
   - "Is it possible"

other hints:  something along the lines of only moving **rightwards or downwards.**Be mindful for [problems](https://algo.monster/problems/walls_and_gates) that allow movement in all directions (up, down, left, right) as they might align more closely with [graph theory](https://algo.monster/problems/graph_intro) than DP.

### approach

to find the intermediate point to solve the sub-problem

 A cell `(r, c)` often depends on:

- Above: `(r - 1, c)`
- To the left: `(r, c - 1)`
- Diagonal, up and left: `(r - 1, c - 1)` Think of these neighboring states as "stepping stones" leading up to the current cell `(r, c)`.

You can approach these problems recursively (top-down) or iteratively (bottom-up). However, the **iterative method** is often preferred due to the inherent ordered nature of grids. Recall that bottom-up solutions will require you to fill in the `dp` table in the order which you solve sub-problems in.

**in-place** or **not**

For convenience, we often add one dummy row and one column to avoid index out of range. We start ietrating from 1.

### base case 

- An empty grid

- A grid of a single-cell

- Single row or column grids

  often the first column and first row because they can only go right or go down

## Dynamic number of sub-problems

This type of DP problem is unique in that the current state depends on a dynamic number of previous states, e.g. `dp[i] = max(d[j]..) for j from 0 to i`. The number of states in this category is typically linear, however the transition varies from problem to problem.

## Interval DP 

### identify

1. the problem is a string or array 
2. either option will make string shorter from 2 directions
3. the dp for memerization is a 2d array, 
4. dp\[left\][right], right always no less than left, so we only care about the upper half of the grid
5. the base case is the dignal. Then we ieterate the diganal by difference

**Linear Sequences:** Interval DP solve problems that involve linear sequences like arrays or strings.

**Time Complexity**:  `O(n^2)` . so the array size if no greater than 3000 generally

1. **Optimization problems:** Determining the maximum or minimum solution.
2. **Counting problems:** Calculating the number of possible solutions.
3. **"Is it possible" problems:** Determining if a solution exists or if some task is achievable.

### solution 

An interval DP problem almost always requires a **2-dimensional** `dp` array, with the states often represented as `dp[l][r]`. Here, `l` and `r` define the boundaries of the interval being evaluated.

==Notice that the only difference between this and the original problem is the original problem considers all the coins while this sub-problem considers only a specific interval of coins, from `l` to `r`.==

### Base case

- **Empty Sequences:** Some problems might define behaviors or return values for empty intervals.
- **Single Element Intervals:** For most interval DP problems, the simplest non-empty interval is a single element. The behavior or value for this smallest interval often forms the base case upon which larger solutions are built.

### transition between states

- **Interval Splitting:** Divide the current interval into two or more smaller intervals. For instance, in solving a problem on interval `l` to `r` (inclusive), you might split it at a point `m` and combine solutions from intervals `l` to `m` (inclusive) and `m + 1` to `r` (inclusive).
- **Element Removal:** Sometimes, in a problem specifically, you may have to remove elements as some operation. By removing an element in an interval, you'll create smaller intervals and use the solutions from those smaller intervals.

## Dual-Sequence DP Introduction

### identify

1. **Two Distinct Sequences**:

If the problem statement highlights two main sequences, it’s a significant indicator. For instance, the classic example provides two strings, `word1` and `word2`.

### dp states

The sub-problems pertain to the prefixes of these sequences. A standard representation involves a 2D array, `dp[i][j]`, where `i` and `j` represent positions in the two sequences.

### base case

when one or both sequences are vacant

### transition

We consider solutions where one or both prefixes are reduced, often by removing the last character. Typically, `dp[i - 1][j]`, `dp[i][j - 1]`, or `dp[i - 1][j - 1]` helps derive `dp[i][j]`.

### bottom-up or top-down 

bottom up is preferred

## Knapsack DP Introduction

### identify

1. Check if there's a **constraint** involving a combination of elements to fulfill a certain capacity. Look for terms such as "size", "capacity", "space available", or "target".

   One distinguishing feature of Knapsack problems in dynamic programming is the ==presence of the "capacity" state==. This state typically represents a specific constraint (like the maximum weight in a bag or a target amount) in relation to which items (or choices) are to be optimized.

### **types**

1. Optimization Problems [0/1 Knapsack](https://algo.monster/problems/problems/knapsack_intro)
2. counting problems [Coin Change II](https://algo.monster/problems/problems/coin_change_ii)
3. feasibility problems [Partition to Two Equal Sum Subsets](https://algo.monster/problems/problems/partition_equal_subset_sum)

### dp states

This results in a two-dimensional state, commonly represented by a 2D array: `dp[i][j]`. Here, `i` refers to the current prefix being considered, while `j` refers to the corresponding capacity. Thus, we get `dp[i][j]` represents the number of combinations to make the target `j` while only considering the first `i` coins.

### Base case

- No items considered: i = 0
- Zero capacity/target: With a zero capacity or target,

### transitions

#### 0-1 knapsack

there are only two options for each item (include or not include)

![image-20240223141905822](/Users/jiaolulu/Library/Application Support/typora-user-images/image-20240223141905822.png)

#### Unbounded Knapsack:

Items can be chosen infinitely in unbounded knapsack problems. For every item, the decision revolves around the frequency of its selection.



### Time and space complexity

O(n * capacity)
