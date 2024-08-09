The problems of Linked list are mainly in the following categories:

1. reverse

2. Merge

3. locate the nth or middle specially from the start or end using 2 pointers 

4. copy and generate

5. cycle: use  Floyd's Tortoise&Hare algorithm

   

regarding the problem that generates new node in order. we can have a arraylist or hashmap to store the nodes first, then iterate the structure to build the link.

### Floyd’s Cycle Finding Algorithm

- Initialize two-pointers and start traversing the linked list.
- Move the slow pointer by one position.
- Move the fast pointer by two positions.
- If both pointers meet at some point then a loop exists and if the fast pointer meets the end position then no loop exists.