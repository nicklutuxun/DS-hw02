# Discussion

The `Roster` class uses `IndexedList` to store a list of students. The
`Roster.find` implements the binary search algorithm. Which
implementation of the `IndexedList` should be used to implement the
`Roster` class? (It could be one or more of `ArrayIndexedList`,
`LinkedIndexList`, `SparseIndexedList`). And why?
   
--------------- Write your answers below this line ----------------

`ArrayIndexedList` is the best choice.

I will analyze this problem from four aspects:
### Binary search
Since the basic requirement of data structure `Roster` is to perform binary search, 
We should be relatively cheap to obtain the "middle" element of two pointers. In `ArrayIndexedList`, we can obtain the middle element using 
index calculation and direct access,and search
time complexity is O(nlogn). While in `LinkedList` binary
search performs like a normal linear search in the worst case, and time complexity is O(nlogn). so we have no point to use `Linkedlists`. `ArrayIndexedList` wins.

### Access
Among all three types, both `LinkedIndexList`, `SparseIndexedList` are expensive
(Time Complexity O(N)) doing access since
they are linked list, and linked list needs to start at the `head` and follow
nodes one by one until target is reached. However `ArrayIndexedList` has the
advantages that it allows cheap element access (Time Complexity O(1)). `ArrayIndexedList` wins.

### Insertion and deletion
Since we are doing binary search, it is necessary to keep our list sorted.
When inserting/deleting element to/from in `ArrayIndexedList`, we need to move all 
the elements either before or after the target, so the time complexity is O(n). 
If we use `LinkedLists`, we need to start from the head and traverse through the whole
list to find the target, thus time complexity is also O(n). Three structures have a draw here.

### Space
Since we need to distinguish between different students in the list, e.g. Using their emails, we cannot use default value to 
save space. Therefore `SparseIndexedList` is not feasible.`LinkedIndexlists` need to store the data as well as the next node 
it points to, so they both use more space then `ArrayIndexedList`. `ArrayIndexedList` wins.


