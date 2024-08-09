- What your code is doing line by line
- Why you’ve chosen to solve the problem this way
- Include any comments of special considerations that we need to manage in C (memory allocation, pointers, etc.)
- Any relevant edge cases that you need to consider
- Include a discussion of the Big-O time complexity of your solution, as well as how much space the solution requires
- Discuss any trade-offs that you’ve made, including any other ways that you have considered solving the problem, and what the trade-offs of that solution would be.
  - For example, you may want to compare your chosen solution to a naïve or brute-force solution, as well as to any other more efficient solutions that you’ve considered

  
  
- The chosen LeetCode problem is solved from start to finish with technically correct C code written live during the interview (10 points)
- Code functionality is explained as it is coded line-by-line (5 points)
- The Big-O time and space complexity of the solution are described and explained (5 points)
- At least one alternative solution is described, which may include the brute force or naïve solution to the problem. If the chosen solution is brute force or naïve, it is clearly stated, and you explain why the brute force or naïve solution is the best solution. The trade-offs between the alternative and the chosen solution are described in terms of their Big-O space and time complexity (5 points)
- Edge cases and constraints for the chosen solution are described, along with how those edge cases are being dealt with, if necessary (5 points)
- The candidate, to the best of their ability, answers the interviewers’ questions (5 points)
- The interview code walk is well-paced, clear, and demonstrates the student’s abilities and understanding of the topic (5 points)

### 1 problem restate and solution 

the lowest common ancestor is the lowest node such that both given nodes are descendants of the node or either of the nodes is equal to the node.

The root is obviously the highest common ancestor of 2 nodes. So if we want to find the lowest, we can start from the root and go all the way down until reaching a node such that either node is not a descendant of the node. 

So how can we decide where to go down? A binary search tree makes it easier. Because the left subtree is smaller than the root while the right is bigger. So we can decide where to go depending on the value of the root and desired nodes. That is if the nodes are both smaller than the root, which indicates that the 2 nodes are both in left subtree of the root, so we go to the left subtree. And the right subtree is the same.

So how do we know that we reached the lowest? If we go down further, either of the 2 nodes will no longer be the descendant. which means 

1. the nodes are currently in different subtrees or 
2. one node is the ancestor of the other. In binary search tree, 

which means 

1. one of the nodes is smaller than the root and the other is bigger or in reverse
2. the root equals either of the nodes.

This is exactly the complement of the 2 situations we need to go down.

### 2 line by line explanation 

1. iterative

   ```c
   /**
    * Definition for a binary tree node.
    * struct TreeNode {
    *     int val;
    *     struct TreeNode *left;
    *     struct TreeNode *right;
    * };
    */
   
   struct TreeNode* lowestCommonAncestor(struct TreeNode* root, struct TreeNode* p, struct TreeNode* q) {
       while(root != NULL){
           if(p->val < root->val && q->val < root->val){
               root = root->left;
               continue;
           }else if(p->val > root->val && q->val > root->val){
               root = root->right;
               continue;
           }else{ 
               break;
           }
       }
       return root;
   }
   
   ```

2. recursive

   ```c
   /**
    * Definition for a binary tree node.
    * struct TreeNode {
    *     int val;
    *     struct TreeNode *left;
    *     struct TreeNode *right;
    * };
    */
   struct TreeNode* lowestCommonAncestor(struct TreeNode* root, struct TreeNode* p, struct TreeNode* q) {
       //base case: it is not gonna reach to the null because nodes exist in tree. The lowest they can get is the second to last level when one node is the ancestor of the other
       //if(root == NULL) return NULL;
       if(p->val == root->val || q->val == root->val || ( p->val > root->val && q->val < root->val) || (p->val < root->val && q->val > root->val)) return root;
       if(p->val > root->val && q->val > root->val) return lowestCommonAncestor(root->right, p, q);
       return lowestCommonAncestor(root->left, p, q);
       
   }
   ```
   
   

### contraints

- The number of nodes in the tree is in the range [2, $10^5$].

  1. The tree is not empty and  has at least the 2 nodes that we want to look for their lowest ancestor. That way, either of the nodes will be the lowest ancestor

- `-109 <= Node.val <= 109`

  that within the range of integer -2147483648($-2^{31}$-1) to 2147483647($2^{31}$)

- All `Node.val` are **unique**.

  if there are nodes with equal values, we'll have difficulty deciding which one is what we're looking for their ancestor

- `p != q`

  means they're not the same node in BST. if they're equal, the lowest ancestor will always be themselves

- `p` and `q` will exist in the BST.

  let us assume that either of node doesn't exist, then the problem does't make sense because there is no such common ancestor

### edge cases

the root is NULL

### time and space complexity

the time complexity is $O(logn)$ because we only have to visit one node in each level. And the number of the level would $logn$

### brute force solution

using breadth first search or deapth first search to check that if the 2 nodes are still the descendants. The time complexity is $O(n^2)$ . This applies to the situation when the tree is not a binary search tree.

The better solution would be convert it to a binary search tree. the time complexity is $O(nlogn)$. Because we have to visit every single node and insert it to the tree. The space complexity is $O(n)$. The time complexity is $O(nlog^2n)$

### if the tree is not a BST

### the trade-off between time and space

### BST

