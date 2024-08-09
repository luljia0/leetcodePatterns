### locate the end of the first half

first half is more or equal to second when the number is odd or even

```java
private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
  			StartofSecondHalf = slow.next;
    }


```

