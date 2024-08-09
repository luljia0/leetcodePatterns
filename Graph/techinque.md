1. graph is connected tree, so it has loops. to avoid infinite loop, we often using hashmap/hashset to store the visited nodes.

2. directed graph

3. undirected graph

   adjacancy listshould include both direnctions

4. Some circumstances, you need to iterate all grid, sometimes you can start from special cells such as borders

5. BFS is used to solve problem related to level and same-level iteration

   

# Breadth First Search on Graphs

 the difference between a tree and a graph is the possibility of having a cycle, we just have to handle this situation. We use an extra `visited` variable to keep track of vertices we have already visited to prevent re-visiting and getting into infinite loops. `visited` can be any data structure that can answer existence queries quickly. For example, a **hash set** or an **array**

### template

The BFS template consists of two core functions:

1. `bfs`: uses a queue to keep track of the nodes to be visited
2. `get_neighbors`: A function that requires implementation which returns a node's neighbors. In an adjacency list representation, this would be returning the list of neighbors for the node. If the problem is about a matrix, this would be the surrounding valid cells as we will see in [number of islands](https://algo.monster/problems/number_of_islands) and [knight shortest path](https://algo.monster/problems/knight_shortest_path). If the graph is implicit, we have to generate the neighbors as we traverse. We will see this in [word ladder](https://algo.monster/problems/word_ladder).

```java
import java.util.*;

public void bfs(Node root) {
    ArrayDeque<Node> queue = new ArrayDeque<>();
    queue.add(root);
    Set<Node> visited = new HashSet<>();
    visited.add(root);
    while (queue.size() > 0) {
        Node node = queue.pop();
        for (Node neighbor : getNeighbors(node)) {
            if (visited.contains(neighbor)) {
                continue;
            }
            queue.add(neighbor);
            visited.add(neighbor);
        }
    }
}
```

## Tracking levels/Finding distance

BFS is by-level traversal. Sometimes we need to track how many levels we have traversed (much like level order traversal problem in BFS on Tree module).

Similar to [binary tree level order traversal](https://algo.monster/problems/binary_tree_level_order_traversal), we can get the number of nodes of a level from the queue size.

```java
import java.util.*;

public void bfs(Node root) {
    ArrayDeque<Node> queue = new ArrayDeque<>();
    queue.add(root);
    Set<Node> visited = new Hashset<>();
    visited.add(root);
    int level = 0;
    while (queue.size() > 0) {
        int n = queue.size();  // get # of nodes in the current level
        for (int i = 0; i < n; i++) {
            Node node = queue.pop();
            for ( Node neighbor : getNeighbors(node)) {
                if (visited.contains(neighbor)) {
                    continue;
                }
                queue.add(neighbor);
                visited.add(neighbor);
            }
        }
        // increment level after we have processed all nodes of the level
        level++;
    }
}
```

## When to use BFS?

 [Shortest path from A to B (unweight)](https://algo.monster/problems/shortest_path_unweight)

Graph of unknown or even infinite size, e.g. [knight shortest path](https://algo.monster/problems/knight_shortest_path)

[Dijkstra Intro | Shortest Path in a Weighted Graph](https://algo.monster/problems/dijkstra_intro)

# Depth First Search on Graphs

### template

Similar to BFS, we just have to add `visited` to keep track of the visited nodes and use `get_neighbors` to get the next nodes to visit.

```java
public void dfs(Node root, Set<Node> visited) {
    if (root == null) return;
    for (Node neighbor : root.neighbors) {
        if (visited.contains(neighbor)) {
            continue;
        }
        visited.add(neighbor);
        dfs(neighbor, visited);
    }
}
```

# Matrix as Graph

cell as node, move direction as edge

Instead of using the inefficient HashSet to store the visited Node, we could use a grid to store true/false as visited/unvisited because of the nature of the matrix



# Directed Graph | Topological Sort

## Kahn's Algorithm

1. add all nodes with 0 in-degree to queue
2. remove a node at a time. (Pop it from queue and add to sorting list)
3. subtract 1 to the indegree of all its outneighbors
4. Check its outneighbor, if no in-degree, add it to the queue

for connected graph, kahn's algo can be used to check if there is cycle. if the number of topo sort == number of node, then it has no cycle. 

## Topological Sort vs BFS

Notice the topological sort algorithm is very similar to [BFS](https://algo.monster/problems/bfs_intro). The main difference is that we **only push nodes with 0 in-degree into the queue** in topological sort whereas in BFS we push all the neighboring nodes into the queue.

## implementation (for connected graph)

```java
// find the indegree of a node by adding in-edge count
public static <T> Map<T, Integer> findInDegree(Map<T, List<T>> graph) {
    Map<T, Integer> inDegree = new HashMap<>();
    graph.keySet().forEach(node -> {
        inDegree.put(node, 0);
    });
    // loop through every node and add 1 in-edge count to its neighbors
  // for unconnected graph: notice that you need to add everynode instead of node in the edge lists because some nodes may not have edge
    graph.entrySet().forEach(entry -> {
        for (T neighbor : entry.getValue()) {
            inDegree.put(neighbor, Indegree.get(neighbor) + 1);
        }
    });
    return inDegree;
}

// topological sort the list
public static <T> List<T> topoSort(Map<T, List<T>> graph) {
    // return a list of the topological sorted list
  	// the list of visited, also list of nodee with 0 indegree
    List<T> res = new ArrayList<>();
    // make a queue that we will use for our solution
    Queue<T> q = new ArrayDeque<>();
    // loop through all nodes and add all nodes that have 0 in-degree
    Map<T, Integer> inDegree = findInDegree(graph);
    inDegree.entrySet().forEach(entry -> {
        if (entry.getValue() == 0) {
            q.add(entry.getKey());
        }
    });
    // perform bfs with queue, mostly the same as template bfs
    while (!q.isEmpty()) {
        T node = q.poll();
        // add node to list to keep track of topological order
        res.add(node);
 				// for unconneted graph:
       if(graph.get(node) == null) continue;
        for (T neighbor : graph.get(node)) {
            // subtract one from every neighbour
            inDegree.put(neighbor, inDegree.get(neighbor) - 1);
            // once the in-degree reaches 0 you add it to the queue
            if (inDegree.get(neighbor) == 0) {
                q.add(neighbor);
            }
        }
    }
    // check for cycle
    return (graph.size() == res.size()) ? res : null;
}
```

# Dijkstra's Algorithm | Shortest Path in a Weighted Graph

 It's easy to see Dijkstra's won't work with graphs with negative weighted edges 

```java
    private static int bfs(List<List<Map.Entry<Integer, Integer>>> graph, int root, int target) {
       
// pq<int[]{distance, node}> 
      PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(1,
                (a, b) -> (Integer.compare(a.getKey(), b.getKey())));
        List<Integer> distances = new ArrayList<>();
      // initialization
        for (int i = 0; i < graph.size(); i++) {
            if (i == root) {
                distances.add(0);
                pq.add(new HashMap.SimpleEntry<>(0, i));
            } else {gy
                distances.add(Integer.MAX_VALUE);
                pq.add(new HashMap.SimpleEntry<>(Integer.MAX_VALUE, i));
            }
        }
        while (pq.size() > 0) {
            Map.Entry<Integer, Integer> t = pq.poll();
            int node = t.getValue();
            for (Map.Entry<Integer, Integer> neighbour : getNeighbors(graph, node)) {
                int id = neighbour.getKey();
                int weight = neighbour.getValue();
                int d = distances.get(node) + weight;
                if (distances.get(id) <= d) {
                    continue;
                }
              // update the distance
                pq.add(new HashMap.SimpleEntry<>(d, id));
                distances.set(id, d);
            }
        }
        return distances.get(target) == Integer.MAX_VALUE ? -1 : distances.get(target);
    }
```

# Bellman-Ford | shortest path for nagative value

# Minimum Spanning Tree

A Minimum Spanning Tree is a tree with overall minimum weight generated from a graph that includes every node in the original graph. You will recall that a tree is defined as a graph with `n - 1` edges `n` nodes as well as no cycles throughout the tree. We can essentially think of it as generating the tree with smallest total weight by selecting edges from a graph.

## Kruskal's Algorithm

1. We sort the edges from lowest to highest using any method(Priority Queue, built-in sorting algorithms etc.)

2. We then try every edge, as long as the 2 nodes that the edge connects are not currently connected by some path we then add the new edge to our graph.

   how to confirm that 2 nodes are connected or not? use UnionandFind algo

   ==unionfind algo applied to union 2 trees, if there is a cycle, then don't union==

   ```java
   class Solution {
       public int countComponents(int n, int[][] edges) {
           int[] parent = new int[n];
           for(int i = 0; i < n; i++) {
               parent[i] = i;
           }
           int[] rank = new int[n];
           Arrays.fill(rank, 1);
   
           for(int[] edge: edges) {
               int node1 = edge[0];
               int node2 = edge[1];
               // if 
               if(find(node1, parent) == find(node2, parent)) continue;
               union(node1, node2, parent, rank);
           }
           int res = 0;
           for(int i = 0; i < n; i++) {
               if(rank[i] >=1) {
                   res++;
               }
           }
           return res;
       }
   
       public int find(int node, int[] parent) {
           if(parent[node] == node) return node;
           return find(parent[node], parent);
       }
   
       public void union(int node1, int node2, int[] parent, int[] rank) {
           int root1 = find(node1, parent);
           int root2 = find(node2, parent);
           if(rank[root1] >= rank[root2]) {
               parent[root2] = node1;
               rank[root1] += rank[root2];
               rank[root2] = 0; 
           } else {
               parent[root1] = node2;
               rank[root2] += rank[root1];
               rank[root1] = 0; 
   
           }
       }
   }
   ```

   

3. Repeat step 2. until we have connected `n - 1` edges then this process terminates.

```java

public static class UnionFind<T> {
    private HashMap<T, T> f = new HashMap<>();

    public T find(T x) {
        T y = f.getOrDefault(x, x);
        if (y != x) {
            y = find(y);
            f.put(x, y);
        }
        return y;
    }

    public void union(T x, T y) {
        f.put(find(x), find(y));
    }
}

public static class Edge {
    int weight, a, b;
    public Edge(int weight, int a, int b) {
        this.weight = weight;
        this.a = a;
        this.b = b;
    }
}

public static Comparator<Edge> cmp = (a, b) -> a.weight - b.weight;

public static int minimumSpanningTree(int n, List<Edge> edges) {
    // sort list, make sure to define custom comparator class cmp to sort edge based on weight from lowest to highest
    Collections.sort(edges, cmp);
    UnionFind<Integer> dsu = new UnionFind<Integer>();
    int ret = 0;
    int cnt = 0;
    for (Edge edge : edges) {
        // check if edges belong to same set before merging and adding edge to mst
        if (dsu.find(edge.a) != dsu.find(edge.b)) {
            dsu.union(edge.a, edge.b);
            ret += edge.weight;
            cnt++;
            if (cnt == n - 1) break; // because a tree has n-1 edges
        }
    }
    return ret;
}


```

## Prim's algorithm

1. Start at an arbitrary node
2. Push all neighboring edges of the node into our Priority Queue
3. If we have not visited the node that the edge leads to we then add the edge to our tree and push the new neighbouring edges
4. We repeat this process until we have `n - 1` edges selected

```java
// a minHeap
PriorityQueue<int[]> pq = new PriorityQueue<>((a , b) -> a[0] - b[0]);

// hashset of index of visited node
HashSet<Integer> visited = new HashSet<>();

// add the source node
pq.offer(new int[] {0, 0});
visited.add(0);

// the toral weight
int res = 0;
// while not all node hasn't been visited
        while(visited.size() < points.length) {
            int[] combo = pq.poll();
            int weight = combo[0];
            int source = combo[1];
            // pq has visited node but with lower weight, which might be popped out
            if(visited.contains(source)) continue;
            res += weight;
            visited.add(source);
            for (int neighbor: graph.getNeighbor(source)) {
                if(visited.contains(neighbor)) continue;      pq.offer(weightfromoriginalsourcetoNeighbor, Neighbor});
            }
        
        }
        return res;



```

# isCyclic(Tree) or not

### Tree = no cycle + connected graph(edge = node -1)

union&find can be used to decide there is cycle or not. If the nodes of an edge has already be connected, then there is a circle. 
