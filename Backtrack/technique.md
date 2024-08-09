1. There always an add an remove operation at the same time in backtracking 
2. build up a decision tree without duplicates first
3. The same level in decision tree is iteration. The next level is recursive step 

## Combinatorial search problems

Combinatorial search problems involve finding groupings and assignments of objects that satisfy certain conditions. Finding all permutations, combinations, subsets and solving sudoku are classic combinatorial problems. 

![image-20231023112149724](/Users/jiaolulu/Library/Application Support/typora-user-images/image-20231023112149724.png)

In combinatorial search problems, the search space (a fancy way of saying all the possibilities to search) is in the shape of a tree. The tree that represents all the possible states is called a **state-space tree** (because it represents all the possible states in the search space).

Each node of the state-space tree represents a state we can reach in a combinatorial search (by making a particular combination). Leaf nodes are the solutions to the problem. **Solving combinatorial search problems boils down to DFS on the state-space tree**. Since the search space can be quite large, we often have to "[prune](https://algo.monster/problems/backtracking_pruning)" the tree, i.e. discard branches and stop further traversals. This is why it's often called backtracking.

### how to **construct the tree/ generate the edges and tree**

1. draw the tree

   Draw a state-space tree to visualize the problem. Then simply traverse the tree depth-first.

2. template

   ```java
   function dfs(start_index, path):
     if is_leaf(start_index):
       report(path)
       return
     for edge in get_edges(start_index):
       path.add(edge) // edge is the choice we make; the string a, b in the above state-space trees.
       dfs(start_index + 1, path) // start_index is used to keep track of the current **level** of the state-space tree we are in.
       path.pop() //pop the last we added not the one we just added
   ```

   

### Time/space complexity

We visit each node of the state-space tree exactly once so the time complexity of a backtracking algorithm is proportional to the number of nodes in the state-space tree. The number of nodes in a tree can be calculated by multiplying `number of children of each node ^ height of the tree`.

The space complexity of a backtracking algorithm is typically the height of the tree because that's where the [DFS recursive call stack](https://algo.monster/problems/dfs_intro) is of maximum height and uses the most memory.

## pruning

### when to use

When it's clear that going into that branch would not yield a valid final state. This happens when the problem comes with one or more constraints, and the branches violates those contraints.

### template

```java
function dfs(start_index, path):
if is_leaf(start_index):
   report(path.copy()) //copy here not the path itself
   return
for edge in get_edges(start_index):
  # prune if needed
  if is_valid(edge): // check if a branch is vaild
      path.add(edge)
      # increment start_index
      dfs(start_index + len(edge), path) // incrememt start_index by a variable size instead of always1
      path.pop()

```

### additional states

In some cases, the constraints imposed by the problem require us to keep additional states to check the validity of a branch.

```java
private static void dfs(int startIndex, List<T> path, List<List<T>> res, [...additional states]) {
    if (isLeaf(startIndex)) {
        res.add(new ArrayList<>(path)); // add a copy of the path to the result
        return;
    }
    for (T edge : getEdges(startIndex, [...additional states])) {
        path.add(choice);
        if (...additional states) update(...additional states) // update additional states here
        dfs(startIndex + edge.length(), res, [...additional states]);
        path.remove(path.size() - 1);
        // revert(...additional states) if necessary e.g. permutations
    }
}
```

## Aggregation 

Essentially, it don't have a path but a global variable. So it don't necessarily do backtracking 

### problem 

In this section, we will look at problems that ask questions such as

- **Is it possible** to make up a word using other words from a dictionary?
- Find the **number of ways** to decode a message
- Find the **minimum number** of coins to make up an amount

We categorize these "aggregation" problems because we aggregate the **return value** from child recursive calls to parent and bubble them up. It's very similar to how [Max Depth of a Tree](https://algo.monster/problems/tree_max_depth) and [Visible Tree Node](https://algo.monster/problems/visible_tree_node) aggregate return values.

### template

```java
private static int dfs(Integer startIndex, List<T> target) {
    if (isLeaf(startIndex)) {
        return 1;
    }

    ans = initialValue;
    for (T edge : getEdges(startIndex, [...additional states])) {
        if (additional states) {
            update([...additional states]);
        }
        ans = aggregate(ans, dfs(startIndex + edge.length(), [...additional states])
        if (additional states) {
            revert([...additional states]);
        }
    }
    return ans;
}
```

The main differences between this and the previous template are:

- no `path` and push/pop since we don't need to actually generate the solutions. We just need the aggregated value.
- use return value to aggregate results from child dfs calls.

Depending on what the problem asks for, the `initial_value` and `aggregate` function here can be

| Problem statement                | initial_value | aggregate         |
| -------------------------------- | ------------- | ----------------- |
| If it's possible? does it exist? | boolean value | logical OR (\|\|) |
| Number of ways to...             | 0             | addition (+)      |
| Maximum/minimum ways/value to... | 0, inf        | max/min           |

## memorization(dynamic programming) 

### When to memoize

After you draw the state-space tree, if you see duplicate subtrees, then it might be a good time to consider memoization.

Memoization is particularly useful for combinatorial problems that have large repeated state-space tree branches. 

### What to memoize

Think about the duplicate subtrees, what attribute(s) do they share? In the Fibonacci example, the duplicate subtrees has the same `n` value. Usually, the `key` to the `memo` is the `start_index` or any `additional states` that may appear multiple times during the recursion.

### time complexity

The time to do backtracking is proportional to the number of nodes in the state-space tree. However, with memoization, we avoid duplicate subtrees (e.g. the red subtrees in the 2nd and 3rd slides). Therefore the actual number of nodes visited is proportional to the size of the `memo` arrary.

For the above Fibonacci example, the size of the `memo` is `O(n)` (space complexity) and for each node we do `O(1)` work to combine the results from child recursive calls. Therefore the overall time complexity is `O(n)`.

## deduplication 

1. the origianl set has duplicate by sorting arrays and skipping the duplicate (79subset2 and 40combination sum 2)
2. avoid the duplicate combination by increasing the start index(78subset and 39combination sum)
