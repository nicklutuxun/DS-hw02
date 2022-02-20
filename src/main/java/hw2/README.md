# Discussion

The `Roster` class uses `IndexedList` to store a list of students. The
`Roster.find` implements the binary search algorithm. Which
implementation of the `IndexedList` should be used to implement the
`Roster` class? (It could be one or more of `ArrayIndexedList`,
`LinkedIndexList`, `SparseIndexedList`). And why?
   
--------------- Write your answers below this line ----------------

The answer to this question depends on the nature of this `Roster` class,
and I will make analysis in different cases.

### Roster with frequent access
`ArrayIndexedList` is the best choice.

In this case, once `Roster` is initialized and done entering elements, insertion
or deletion will rarely be called. One example for this is the roster of a 
university class. After the Add/Drop deadline, the information of students 
will rarely change, while access to each student element within the list is
frequent: Record attendance/homework/grades, etc. Among all three types,
both `LinkedIndexList`, `SparseIndexedList` are expensive doing access since
they are linked list, and linked list needs to start at the `head` and follow
nodes one by one until target is reached. However `ArrayIndexedList` has the
advantages that it allows cheap element access, and it also uses less memory than 
linked lists.

### Roster with frequent insertion/deletion
`LinkedIndexedList` is the best choice.

In this case, after `Rosrter` is initialized, insertion and deletion will be
called much more frequent than access operation. For example, it could be the roster
for an online MOOC course which massive users register and drop it. Compared to accessing
each user, insertion and deletion of enrolled users are far more important to keep track of.
Among all three types, `ArrayIndexedList` Insertion/deletion to the front or at the middle
of an array is expensive, since it requires shifting other elements to make space. Between
`LinkedIndexList` and `SparseIndexedList`, `LinkedIndexList`, I think `LinkedIndexedList` is
preferred. This is because we need different users' identifications, such as email addresses, to
identify them. It is the same reason we should need use `SparseIndexedList` since we cannot 
distinguish among default values.