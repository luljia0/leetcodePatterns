### note

1. learn how to reverse the list, locate the node
2. preserve the node when doing list modification
3. isolate the modified group buy cutting the links
4. Change the links



### template

Three pointers: current, next, and previous

```java
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
```



### problem 

1. Reverse a LinkedList (easy) [Leetcode](https://leetcode.com/problems/reverse-linked-list/)
2. Reverse a Sub-list (medium) [Leetcode](https://leetcode.com/problems/reverse-linked-list-ii/)
3. Reverse every K-element Sub-list (medium) [Leetcode](https://leetcode.com/problems/reverse-nodes-in-k-group/)
4. Problem Challenge 1: Reverse alternating K-element Sub-list (medium) [Geeksforgeeks](https://www.geeksforgeeks.org/reverse-alternate-k-nodes-in-a-singly-linked-list/)
5. Problem Challenge 2: Rotate a LinkedList (medium) [Leetcode](https://leetcode.com/problems/rotate-list/)

## not in-place reversal

```java
    public ListNode reverseLinkedList(ListNode head) {
        if(head.next == null) return new ListNode(head.val, null); // when there is only one node
        ListNode curr = head;
        ListNode newPre = null;
        while(curr != null) {
            ListNode newCurr = new ListNode(curr.val, newPre);
            newPre = newCurr;
            curr = curr.next;
        }
        return newPre;
    }
```

