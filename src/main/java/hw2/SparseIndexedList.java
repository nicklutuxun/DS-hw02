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
    if (size <= 0) {
      throw new LengthException("ERROR: SIZE CANNOT <= 0");
    }
    this.length = size;
    this.defaultValue = defaultValue;
  }

  @Override
  public int length() {
    return length;
  }
  
  private boolean hasNextNode(Node<T> node) {
    if (node == null || node.next == null) {
      return false;
    }
    return true;
  }
  
  private Node<T> traverse(int index) throws IndexException {
    checkIndex(index);
    Node<T> tracker = head;
    if (head == null) {
      return null;
    }
    
    while (tracker.index < length) {
      if (tracker.index == index) {
        return tracker;
      }
      if (hasNextNode(tracker)) {
        tracker = tracker.next;
        continue;
      }
      break;
    }
    return null;
  }
  
  private void checkIndex(int index) throws IndexException {
    if (index < 0 || index >= length()) {
      throw new IndexException("ERROR: INVALID INDEX");
    }
  }
  
  @Override
  public T get(int index) throws IndexException {
    Node<T> tracker = traverse(index);
    if (tracker == null || tracker.index != index) {
      return defaultValue;
    }
    return tracker.data;
  }

  @Override
  public void put(int index, T value) throws IndexException {
    Node<T> tracker = traverse(index);
    if (tracker == null) {
      if (value == defaultValue) {
        return;
      } else {
        addNode(index, value);
      }
    } else {
      if (value == defaultValue) {
        deleteNode(index);
      } else {
        tracker.data = value;
      }
    }
  }
  
  private void addNode(int index, T value) {
    Node<T> newNode = new Node<T>(index, value);
    
    Node<T> target = find(index);
    if (head == null || target.index > index) {
      if (head != null) {
        newNode.next = head;
      }
      head = newNode;
    } else {
      target.next = newNode;
    }
  }
  
  private Node<T> find(int index) {
    Node<T> tracker = head;
    if (tracker == null) {
      return null;
    }
    while (hasNextNode(tracker)) {
      if (tracker.next.index >= index) {
        return tracker;
      }
    
      tracker = tracker.next;
    }
    return tracker;
  }
  
  private void deleteNode(int index) {
    Node<T> target = find(index);
    if (target == null || target == head) {
      head = null;
      return;
    }
    target.next = target.next.next;
  }

  @Override
  public Iterator<T> iterator() {
    return new SparseIndexedListIterator();
  }

  private class SparseIndexedListIterator implements Iterator<T> {
    private Node<T> current;
    private int cursor;
  
    SparseIndexedListIterator() {
      current = head;
      cursor = 0;
    }
    
    @Override
    public boolean hasNext() {
      return cursor < length && cursor >= 0;
    }

    @Override
    public T next() throws NoSuchElementException {
      T data = defaultValue;
      if (hasNext()) {
        if (current != null && cursor == current.index) {
          data = current.data;
          if (hasNextNode(current)) {
            current = current.next;
          }
        }
        cursor++;
      } else {
        throw new NoSuchElementException();
      }
      return data;
    }
  }
  
  private class Node<T> {
    T data;
    int index;
    Node<T> next;
  
    Node(int index, T data) {
      this.data = data;
      this.index = index;
      this.next = null;
    }
  }
}
