### note

1. pointers can start form any point in the list or cycle

### usage

1. decide whether there is a cycle in the linked list
2. find the starting node entering the cycle

### equation

t- time of two pointers meet

x- number of nodes before entering point

y- distance from entering point to meeting point

c- length of cycle

`x + y = t`

`x + y + c= 2t`

`x + y = c= t`

'c - y = x' (finding the entering point)

### What is Floyd's Cycle-Finding algorithm ?

- It is also called **Hare-Tortoise algorithm**
- The algorithm works by using two pointers, a slow pointer and a fast pointer.
- Initially, both pointers are set to the head of the linked list.
- The fast pointer moves twice as fast as the slow pointer.
- If there is a cycle in the linked list, eventually, the fast pointer will catch up with the slow pointer.
- If there is no cycle, the fast pointer will reach the end of the linked list.

### Approach :

- When the two pointers meet, we know that there is a cycle in the linked list.
- We then reset the slow pointer to the head of the linked list and move both pointers at the same pace, one step at a time, until they meet again.
- The node where they meet is the starting point of the cycle.
- If there is no cycle in the linked list, the algorithm will return null.

### problem 



