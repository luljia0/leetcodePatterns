1. the essence of the slide window technique is the slow-fast pointer> The fast pointer is to increase for every iteration. The point is to know when to move the slow pointer when the slide window is not valid; another essential part is to check what changed as the slide window changes, only check the changed part
2. note that condition of valid slide window usually use hashmap, you just need to check the changed element when everytime moving the slow/fast condition instead of all of the related elements. So you usually need a flag variable

```java
int left = 0;
int right = 0;

for(right = 0; right < length; right++) {
  // expand the window after right ++ 
  
  while(window is valid) {
    // shrink the window after left++
    left++;
  }
}
```

