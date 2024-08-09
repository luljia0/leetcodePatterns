Treenode0, children = {1, 2, 4}

TreeNode1 = {3}

Dfs(TreeNode0)

childHasApple = false;

1. res = 1;

​	childHasApple = false | dfs(1);

​	dfs(1)

​	childHasApple = false;

​	res==2

​	childHasApple = false | dfs(3)

​		dfs(3)

​		childHasApple = false

​		return true

​	childHasapple = true;

​	res = 3;

​	return true

Res

​	





