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
  
  private Node<T> head;
  private T defaultValue;
  private int length;
  /**
   * Constructs a new SparseIndexedList of length size
   * with default value of defaultValue.
   *
   * @param size Length of list, expected: size > 0.
   * @param defaultValue Default value to store in each slot.
   * @throws LengthException if size <= 0.
   */
  public SparseIndexedList(int size, T defaultValue) throws LengthException {
    // TODO
    if (size <= 0) throw new LengthException("ERROR: SIZE CANNOT <= 0");
    this.length = size;
    this.defaultValue = defaultValue;
  }

  @Override
  public int length() {
    return length;
  }
  
  private Node<T> find(int index) throws IndexException {
    if (!isValid(index)) {
      throw new IndexException("ERROR: INDEX NOT VALID");
    }
    
    Node<T> node = head;
    int counter = 0;
    while (node != null && counter < index-1) {
      node = node.next;
      counter = counter + 1;
    }
    return node;
  }
  
  private boolean isValid(int index) {
    return index >= 0 && index < length();
  }
  
  @Override
  public T get(int index) throws IndexException {
    // TODO
    Node<T> tracker = find(index-1);
    return tracker.next.data;
  }

  @Override
  public void put(int index, T value) throws IndexException {
    // TODO
    if (!isValid(index)) throw new IndexException("ERROR: INDEX NOT VALID");
  
    Node<T> newNode = new Node<T>(index, value);
    Node<T> tracker = head;
    if (tracker == null) {
      head = newNode;
      return;
    }
    tracker = find(index-1);
    tracker.next = newNode;
  }

  @Override
  public Iterator<T> iterator() {
    return new SparseIndexedListIterator();
  }

  private class SparseIndexedListIterator implements Iterator<T> {
    @Override
    public boolean hasNext() {
      // TODO
      return false;
    }

    @Override
    public T next() throws NoSuchElementException {
      // TODO
      return null;
    }
  }
  
  private static class Node<T> {
    T data;
    int index;
    Node<T> next;
  
    public Node(int index, T data) {
      this.data = data;
      this.index = index;
      this.next = null;
    }
  }
}
