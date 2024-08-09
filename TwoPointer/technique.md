1. when do in-place operations, we usually use two pointers
2. Merge, sort, swap, we use two pointers

This is why the `two-pointer technique` is efficient. We are able to process two elements per loop instead of just one.

1. Two pointers, each starting from the beginning and the end until they both meet.
2. One pointer moving at a slow pace, while the other pointer moves at twice the speed.

### characteristic 

1. used in iterable structure, usually array
2. Two moving pointers, regardless of directions, moving dependently or independently;
3. A function that utilizes the entries referenced by the two pointers, which relates to the answer in a way;
4. An easy way of deciding which pointer to move;
5. A way to process the array when the pointers are moved.

### Same direction 

### opposite direction

### two pointers vs slide window

==slide window: left pointers moves until slide window is not valid. There is alway a nested iteration inside of the iteration of right pointers==

==2 pointers(same direction): left pointer usually only moves once or zero during each iteration==

Sliding window problems are similar to the same directions problems, only instead, the function performs on the ==entire interval== between the two pointers. Usually, however, we keep track of the overall result of the window, and each time we insert/remove an item from the window, we simply update the window according to the changes instead of recalculating everything.

### Non-array Applications

also can be applied to Non-array Applications as long as it is ==iterable==, such as ==linked list==