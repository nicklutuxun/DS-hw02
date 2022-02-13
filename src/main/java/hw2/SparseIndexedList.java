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
  
  private Node<T> find(int index) throws IndexException {
    // Index out of bound
    if (!isValid(index)) {
      throw new IndexException("ERROR: INDEX NOT VALID");
    }
    
    Node<T> tracker = head;
    if (tracker == null) {
      return null;
    }
    
    while (hasNextNode(tracker)) {
      if (tracker.next.index < index) {
        tracker = tracker.next;
      }
      
      if (tracker.next.index == index) {
        return tracker.next;
      }
      
      if (tracker.index > index) {
        break;
      }
    }
    
    return tracker;

//    Node<T> tracker = head;
//    if (tracker == null) {
//      return null;
//    }
//    for (int i = 1; i <= index; i++) {
//      if (tracker.next == null) {
//        break;
//      }
//      if (tracker.index == index && i == index) {
//        break;
//      }
//      if (tracker.index == i && tracker.next.index < index) {
//        tracker = tracker.next;
//      }
//    }
//    return tracker;
  }
  
  private Node<T> traverse(int index) {
    Node<T> tracker = head;
    while (hasNextNode(tracker)) {
      if (tracker.next.index >= index) {
        return tracker;
      }
      
      tracker = tracker.next;
    }
    return tracker;
  }
  
  private boolean isValid(int index) {
    return index >= 0 && index < length();
  }
  
  @Override
  public T get(int index) throws IndexException {
    Node<T> tracker = find(index);
    if (tracker == null) {
      return defaultValue;
    }
    return tracker.data;
  }

  @Override
  public void put(int index, T value) throws IndexException {
    if (!isValid(index)) {
      throw new IndexException("ERROR: INDEX NOT VALID");
    }
  
    Node<T> newNode = new Node<T>(index, value);
    if (head == null) {
      head = newNode;
      return;
    }
    
    // SparseIndexedList contains at least one element
    Node<T> node = find(index);
    Node<T> tracker = traverse(index);
    // No element with the index
    if (node == null) {
      if (value == defaultValue) {
        return;
      }
      newNode.next = tracker.next;
      tracker.next = newNode;
    } else {
      if (value == defaultValue) {
        tracker.next = tracker.next.next;
      } else {
        node.data = value;
      }
    }
    
    
    
//    Node<T> tracker = find(index);
//    if (tracker == null) {
//      if (value != defaultValue) {
//        head = new Node<T>(index, value);
//      } else {
//        return;
//      }
//    } else {
//      if (value != defaultValue) {
//        if (find(index).index != index) {
//          tracker.next = new Node<T>(index, value);
//        } else {
//          tracker.data = value;
//        }
//      } else {
//        if (find(index).index != index) {
//          return;
//        } else {
//          tracker.data = value;
//        }
//      }
//    }


//    Node<T> tracker = head;
//    // value is not default
//    if (value != defaultValue) {
//      if (tracker == null) {
//        head = new Node<T>(index, value);
//        return;
//      } else if (find(index).index != index) {
//        tracker = find(index);
//        tracker.next = new Node<T>(index, value);
//      } else {
//        tracker = find(index);
//        tracker.data = value;
//      }
//    } else {
//      tracker = find(index);
//      if (tracker == null) {
//        return;
//      }
//      tracker.next = null;
//    }
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
      cursor = 1;
    }
    
     @Override
    public boolean hasNext() {
      return cursor <= length;
    }

    @Override
    public T next() throws NoSuchElementException {
      T data = defaultValue;
      if (hasNext()) {
        if (current == null) {
        } else if (cursor == current.index) {
          data = current.data;
          if (hasNextNode(current)) {
            current = current.next;
          }
        }
        cursor++;
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
