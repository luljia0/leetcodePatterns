## note that

1. before using root.val or to==root.right, don't forget the edge case that root == null
1. tree not necessarily has 2 nodes. Tree is not binary tree!!!

## traversal

![image-20230618094012275](/Users/jiaolulu/Library/Application Support/typora-user-images/image-20230618094012275.png)

1. inorder

   Left - root- right

   the inorder of binary search tree is in ascending order

   ```java
       public void inorder(TreeNode root) {
           if(root == null) return;
           inorder(root.left);
           list.add(root.val);
           inorder(root.right);
           
       }
   ```

   

2. preorder

   root - left -right

3. Postorder

   Left-right-root
   
4.  Pre-order is essentially a greedy way of solving problems - you make the decision before looking at your children. Post-order is the opposite: you make the decision after collecting information on children.

## recursion

### Type of recursion

### 1. Return value (passing value up from child to parent)

What do we want to return after visiting a node. For example, for the max depth problem this is the max depth for the current node's subtree. If we are looking for a node in the tree, we'd want to return that node if found, else return null. Use the return value to pass information from children to parent.

### 2. Identify state(s) (passing value down from parent to child)

such as problem 1448

What states do we need to maintain to compute the return value for the current node. For example, to know if the current node's value is larger than its parent we have to maintain the parent's value as a state. State becomes DFS's function arguments. Use states to pass information from parent to children.

### using return value vs global variable

**Using return value (divide and conquer)**

One way to solve it is to use return value to pass the maximum value we have encountered back to parent node, and let the parent node compare it with the return value from the other child. This is more of a divide and conquer approach. We have seen this in [merge sort](https://algo.monster/problems/advanced_sorting).

**Using global variable**

Another way to solve it is to traverse the tree while keeping a global variable that keeps track of the maximum value we have encountered. After the dfs, we return the global variable.

The recursive function `dfs` does not return any value in this case. We "fire-and-forget" the `dfs` call.

## Type if recursion 

1. Mutable varible can be a parameter passed along the recursion

   Eg. dfs(List<Integer> list)

2. but immutable variable such as a string must be global (297); of course, mutable can be glocal too (543. 1448); and immutable such as strings also can be muted by substring and array by copyOfRange or index

3.  have a global variable to update and a different return (543)

4. only when the last value or variable is related to the next, we need to return it. When we just need to traverse everynode and get the value, we need a global variable





## dfs 

Dfs is essentially pre-order tree traversal 

see DFS file folder

## bfs

bfs is iterative and need a queue;

