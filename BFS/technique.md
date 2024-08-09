BFS uses a queue (First In First Out). **When we dequeue a node, we enqueue its children**.



### template

```java

def bfs_by_queue(root):
    queue = deque([root]) # at least one element in the queue to kick start bfs
    while len(queue) > 0: # as long as there is an element in the queue 
        node = queue.popleft() # dequeue

        for child in node.children: # enqueue children
            if OK(child): # early return if problem condition met
                return FOUND(child)
            queue.append(child)
    return NOT_FOUND

```



## Dfs vs BFS

DFS is better at:

- finding nodes far away from the root

BFS is better for:

- finding nodes close/closest to the root

## 

## BFS Trees

102 & 109

we can use size of queue to iterate the same level

in each loop of while loop, the elements in queue at the time  is in the same level. We can use the size of the queue to iterate  the same level