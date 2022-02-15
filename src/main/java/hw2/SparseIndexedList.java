package hw2;

import exceptions.IndexException;
import exceptions.LengthException;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * An implementation of an IndexedList designed for cases where
 * only a few positions have distinct values from the initial value.
 *
 * @param <T> Element type.
 */
public class SparseIndexedList<T> implements IndexedList<T> {
  private final T defaultValue;
  private final int length;
  private Node<T> head;
  
  /**
   * Constructs a new SparseIndexedList of length size
   * with default value of defaultValue.
   *
   * @param size Length of list, expected: size > 0.
   * @param defaultValue Default value to store in each slot.
   * @throws LengthException if size <= 0.
   */
  public SparseIndexedList(int size, T defaultValue) throws LengthException {
    if (size <= 0) {
      throw new LengthException("ERROR: SIZE CANNOT <= 0");
    }
    this.length = size;
    this.defaultValue = defaultValue;
  }
  
  /**
   * Return the length of SparseIndexedList.
   *
   * @return length of SparseIndexedList
   */
  @Override
  public int length() {
    return length;
  }
  
  /**
   * Check whether a node has next (valid) node.
   *
   * @param node The node to check
   * @return false if node is the head of empty list or next of node is null
   */
  private boolean hasNextNode(Node<T> node) {
    return node != null && node.next != null;
  }
  
  /**
   * Find whether a valid node of index exists in the list.
   *
   * @param index The index of node
   * @return Node if found, null if not
   * @throws IndexException when index < 0 or index >= length()
   */
  private Node<T> find(int index) throws IndexException {
    checkIndex(index);
    Node<T> tracker = head;
    // List is empty
    if (head == null) {
      return null;
    }
    
    // Proceed when index of tracker is smaller
    while (tracker.index < length) {
      // Found
      if (tracker.index == index) {
        return tracker;
      }
      // Proceed to next valid node
      if (hasNextNode(tracker)) {
        tracker = tracker.next;
        continue;
      }
      break;
    }
    // Not found
    return null;
  }
  
  /**
   * Check whether index is in bound.
   *
   * @param index index to be checked
   * @throws IndexException if index < 0 or index >= length()
   */
  private void checkIndex(int index) throws IndexException {
    if (index < 0 || index >= length()) {
      throw new IndexException("ERROR: INVALID INDEX");
    }
  }
  
  /**
   * Get the value of node at certain index.
   *
   * @param index representing a position in this list.
   * @return node's data T
   * @throws IndexException if index < 0 or index >= length()
   */
  @Override
  public T get(int index) throws IndexException {
    Node<T> tracker = find(index);
    // No node with given index
    if (tracker == null) {
      return defaultValue;
    }
    // Node found
    return tracker.data;
  }
  
  /**
   * Put a node into the list at a certain index.
   *
   * @param index representing a position in this list.
   * @param value to be written at the given index.
   *              Post: this.get(index) == value
   * @throws IndexException if index < 0 or index >= length()
   */
  @Override
  public void put(int index, T value) throws IndexException {
    Node<T> tracker = find(index);
    // No node with give index
    if (tracker == null) {
      if (value == defaultValue) {
        // Do nothing
      } else {
        addNode(index, value);
      }
    } else {
      if (value == defaultValue) {
        deleteNode(index);
      } else {
        // Modify value
        tracker.data = value;
      }
    }
  }
  
  /**
   * Helper for put(), do specific job to insert node.
   *
   * @param index index at which to be inserted
   * @param value value of inserted node
   */
  private void addNode(int index, T value) {
    // Find the node before target
    Node<T> target = traverse(index);
    
    Node<T> newNode = new Node<>(index, value);
    
    // When node is after head
    if (target == null || target.index > index) {
      // List is not empty
      if (head != null) {
        newNode.next = head;
      }
      head = newNode;
    } else {
      target.next = newNode;
    }
  }
  
  /**
   * Find the previous node of given index, in order to modify the node at index.
   *
   * @param index index of node
   * @return the previous node, null if list is empty
   */
  private Node<T> traverse(int index) throws IndexException {
    checkIndex(index);
    Node<T> tracker = head;
    // List is empty
    if (tracker == null) {
      return null;
    }
    // Proceed while next node is valid
    while (hasNextNode(tracker)) {
      // Stop and return
      if (tracker.next.index >= index) {
        return tracker;
      }
      // Proceed to next node
      tracker = tracker.next;
    }
    return tracker;
  }
  
  /**
   * Delete a node at given index.
   *
   * @param index index of node to be deleted
   */
  private void deleteNode(int index) {
    // Find node before target
    Node<T> tracker = traverse(index);
    
    // Empty list or first node
    if (tracker == null || tracker == head) {
      head = null;
      return;
    }
    tracker.next = tracker.next.next;
  }
  
  /**
   * Creates an iterator for SparseIndexedList.
   *
   * @return the iterator
   */
  @Override
  public Iterator<T> iterator() {
    return new SparseIndexedListIterator();
  }
  
  /**
   * An implementation of type Node.
   *
   * @param <T> element type
   */
  private static class Node<T> {
    T data;
    int index;
    Node<T> next;
    
    /**
     * Constructor of Node.
     *
     * @param index index of node
     * @param data  data of node
     */
    Node(int index, T data) {
      this.data = data;
      this.index = index;
      this.next = null;
    }
  }
  
  /**
   * An implementation of iterator of SparseIndexedListIterator.
   */
  private class SparseIndexedListIterator implements Iterator<T> {
    private Node<T> current;
    private int cursor;
    
    /**
     * Constructor of iterator.
     */
    SparseIndexedListIterator() {
      current = head;
      cursor = 0;
    }
    
    /**
     * Check whether iterator has next node.
     *
     * @return true if cursor between 0 and length-1(inclusive)
     */
    @Override
    public boolean hasNext() {
      return cursor < length && cursor >= 0;
    }
    
    /**
     * Returns data of current node, then proceed to next node.
     *
     * @return Data of current node, defaultValue if node not valid
     * @throws NoSuchElementException if current node is cursor out of bound
     */
    @Override
    public T next() throws NoSuchElementException {
      T data = defaultValue;
      // Index in bound
      if (hasNext()) {
        // List not empty and cursor at current node
        if (current != null && cursor == current.index) {
          data = current.data;
          // Proceed to next valid node
          if (hasNextNode(current)) {
            current = current.next;
          }
        }
        // Increase cursor
        cursor++;
      } else {
        throw new NoSuchElementException();
      }
      return data;
    }
  }
}
