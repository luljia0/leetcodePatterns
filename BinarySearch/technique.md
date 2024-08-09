1. the essense of binary search is divide and conquer. the key is to decide which division to go, and also include mid or not

2. how to the division. is the direction where to go is definite

3. binary search is usually just a small part to optimize the searching process in an array or arrayilst, usually in ascending order or descending order

4. binary search can solve the problem of minimum with definite lower and upper limits. For example, problem 875

5. binary search后的两部分一定是缩小的，不能包涵mid，不然只剩最后一个元素的时候会陷入无限循环

6. be careful of the data overflow when it comes to addition and multiplication. It can be avoided by subtraction and division. See problem 367. When dealing with division, be careful the divide num can't be zero or negative

   

### when to use

1. ==the array is sorted or implicitly sorted==
2. the size of array is limited
3. Come with max/min element
4. sometimes given an n(usually n is small, or we need to get the limit of n by ourselves), we can contrust an array of 0-n-1 or 1-n by ourself and do the binary search


### vanilla binary search(find the target index in sorted array)

```java
    public static int binarySearch(List<Integer> arr, int target) {
        int left = 0;
        int right = arr.size() - 1;

        while (left <= right) { // <= here because left and right could point to the same element, < would miss it
            int mid = left + (right - left) / 2; // use `(right - left) /2` to prevent `left + right` potential overflow
            // found target, return its index
            if (arr.get(mid) == target) return mid;
            if (arr.get(mid) < target) {
                // middle less than target, discard left half by making left search boundary `mid + 1`
                left = mid + 1;
            } else {
                // middle greater than target, discard right half by making right search boundary `mid - 1`, discard the current element
                right = mid - 1;
            }
        }
        return -1; // if we get here we didn't hit above return so we didn't find target
    }
```



### Find the First True in a Sorted Boolean Array

==the variation can be finding the biggest true. see problem 69. The essense is to to find the feasible function and make sure the array is in ascending order and to find out you want the biggest or smallest border number==

An array of boolean values is divided into two sections; the left section consists of all `false` and the right section consists of all `true`. Find the First True in a Sorted Boolean Array of the right section, i.e. **the index of the first `true` element**. If there is no `true` element, return -1.

Input: `arr = [false, false, true, true, true]`

1. if the element is `false`, we discard everything to the left and the current element itself.
2. if the element is `true`, the current element *could be* the first `true` although there may be other `true` to the left. We discard everything to the right but what about the *current element*?

#### Approach 1

We keep a variable `boundary_index` that represents the leftmost `true`'s index currently recorded. If the current element is `true`, then we update `boundary_index` with its index and discard everything to the right including the current element itself since its index has been recorded by the variable.

```java
    public static int findBoundary(List<Boolean> arr) {
        int left = 0;
        int right = arr.size() - 1;
        int boundaryIndex = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr.get(mid)) {
                boundaryIndex = mid; // here to record the index of true
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return boundaryIndex;
    }
```

#### Approach 2

Another approach to handle case 2 above is to keep the current element in the search range instead of discarding it, i.e. `if arr[mid]: right = mid` instead of `right = mid - 1`. However, doing this without modifying the `while` condition will result in an **infinite loop**. This is because when `left == right`, `right = mid` will not modify `right` and thus, not shrink search range and we will be stuck in the while loop forever. 

To make this work we have to remove the equality in the `while` condition. In addition, as mentioned in the last module, a `while` loop without equality will miss the single-element edge case so we have to add an additional check after the loop to handle this case. Overall, we have to make three modifications to the vanilla binary search to make it work.

```java
    public static int findBoundary(List<Boolean> arr) {
  
        int left = 0;
        int right = arr.size() - 1;
      // additional: check the missing case - one-element array
        while (left < right) { // remove equality here
            int mid = left + (right - left) / 2;
            if (arr.get(mid)) {
                right = mid; // don't discard the index of true here by not minus 1
            } else {
                left = mid + 1;
            }
        }
      
        return right >= arr.size() ? right : -1;
    }
```



### `feasible` function

The pre-condition for binary search is to find a monotonic function `f(x)` that returns either `True` or `False`. Then the problem becomes [Find the First True in a Sorted Boolean Array](https://algo.monster/problems/binary_search_boundary) that we already know how to solve using binary search. We will call the function **`feasible`** to signify that whether the element at the current index is feasible (True) or not (False) to meet the problem constraints.

### Template

```java
public static int binarySearch(List<Integer> arr, int target) {
    int left = 0;
    int right = arr.size() - 1;
    int firstTrueIndex = -1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (feasible(mid)) {
            firstTrueIndex = mid;
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }
    return firstTrueIndex >= arr.size() ? firstTrueIndex : -1;
}
```

## Implicitly sorted array

But remember binary search can work beyond sorted arrays, **as long as there is a binary decision we can use to shrink the search range**.

### rotated array

we can use the relationship with the last element to decide which part the element is in, And in each left and right part, it is sorted, so we can go left or right based on the inequality
