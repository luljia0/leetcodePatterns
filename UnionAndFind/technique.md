1. Parent[i] stores the root of node i; rank[i] stores the number of children of node i;

2. Don't forget to initiate the parent as itself and rank as 1

   ```java
   public int find(int node) {
           if(parent[node] == node) return node;
           return find(parent[node]);
       }
       public void union(int node1, int node2) {
           int parent1 = find(node1);
           int parent2 = find(node2);
           if(parent1 == parent2) return;
           if(rank[parent1] >= rank[parent2]) {
               // update all the parent of nodes whose parent is parent2
               for(int i = 0; i < parent.length; i++) {
                   if(parent[i] == parent2) parent[i] = parent1;
               }
               // parent[parent2] = parent1;
               rank[parent1] += rank[parent2];
           } else {
               parent[parent1] = parent2;
               for(int i = 0; i < parent.length; i++) {
                   if(parent[i] == parent1) parent[i] = parent2;
               }
               rank[parent2 ] += rank[parent1];
               
           }
         // for testing purpose
           int[] parentCopy = parent;
           int[] rankCopy = rank;
       }
   ```

   