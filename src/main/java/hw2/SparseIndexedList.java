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
  }

  @Override
  public int length() {
    // TODO
    return 0;
  }

  @Override
  public T get(int index) throws IndexException {
    // TODO
    return null;
  }

  @Override
  public void put(int index, T value) throws IndexException {
    // TODO
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
}
