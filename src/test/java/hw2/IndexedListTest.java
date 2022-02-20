package hw2;

import exceptions.IndexException;
import exceptions.LengthException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit Tests for any class implementing the IndexedList interface.
 */
public abstract class IndexedListTest {
  protected static final int LENGTH = 10;
  protected static final int INITIAL = 7;
  private IndexedList<Integer> indexedList;

  public abstract IndexedList<Integer> createArray();

  @BeforeEach
  public void setup() {
    indexedList = createArray();
  }

  @Test
  @DisplayName("get() throws exception if index is below the valid range.")
  void testGetWithIndexBelowRangeThrowsException() {
    try {
      indexedList.get(-1);
      fail("IndexException was not thrown for index < 0");
    } catch (IndexException ex) {
      return;
    }
  }
  
  @Test
  @DisplayName("get() obtains correct value after list is initiated.")
  void testGetCorrectValueAfterListInitiated() {
    for (int i = 0; i < indexedList.length(); i++ ) {
      assertEquals(INITIAL, indexedList.get(i));
    }
  }
  
  @Test
  @DisplayName("get() obtains correct value after insertion.")
  void testGetCorrectValueAfterInsertion() {
    for (int i = 0; i < indexedList.length(); i++ ) {
      indexedList.put(i, i);
    }
    
    for (int i = 0; i < indexedList.length(); i++ ) {
      assertEquals(i, indexedList.get(i));
    }
  }
  
  @Test
  @DisplayName("constructor throws exception if LENGTH is <= 0.")
  void testConstructorThrowsExceptionForInvalidSize() {
    try {
      indexedList = new ArrayIndexedList<>(-LENGTH, INITIAL);
      fail("LengthException was not thrown where it was expected!");
    } catch (LengthException ex) {
      return;
    }
  }
  
  @Test
  @DisplayName("get() throws exception if index is above the valid range.")
  void testGetWithIndexAboveRangeThrowsException() {
    try {
      indexedList.get(LENGTH + 1);
      fail("IndexException was not thrown for index > length");
    } catch (IndexException ex) {
      return;
    }
  }
  
  @Test
  @DisplayName("put() throws exception if index is below the valid range.")
  void testPutWithIndexBelowRangeThrowsException() {
    try {
      indexedList.put(-1, 10);
      fail("IndexException was not thrown for index < 0");
    } catch (IndexException ex) {
      return;
    }
  }
  
  @Test
  @DisplayName("put() throws exception if index is above the valid range.")
  void testPutWithIndexAboveRangeThrowsException() {
    try {
      indexedList.put(LENGTH + 1, 10);
      fail("IndexException was not thrown for index > length");
    } catch (IndexException ex) {
      return;
    }
  }
  
  @Test
  @DisplayName("put() changes the default value after IndexedList is instantiated.")
  void testPutChangesValueAfterConstruction() {
    indexedList.put(2, 7);
    assertEquals(7, indexedList.get(2));
  }
  
  @Test
  @DisplayName("put() changes the default value after IndexedList is instantiated.")
  void testPutChangesValueAfterConstructionReverseOrder() {
    for (int i = 0; i < 10; i++) {
      indexedList.put(i, 9-i);
    }
  
    for (int i = 0; i < 10; i++) {
      assertEquals(9-i, indexedList.get(i));
    }
  }
  
  @Test
  @DisplayName("put() overwrites the existing value at given index to provided value.")
  void testPutUpdatesValueAtGivenIndex() {
    indexedList.put(1, 8);
    assertEquals(8, indexedList.get(1));
    indexedList.put(1, -5);
    assertEquals(-5, indexedList.get(1));
  }
  
  @Test
  @DisplayName("length() returns the correct LENGTH after IndexedList is instantiated.")
  void testLengthAfterConstruction() {
    assertEquals(LENGTH, indexedList.length());
  }
  
  @Test
  @DisplayName("length does not change when values are modified.")
  void testLengthAfterModification() {
    for (int i = 0; i < 10; i++) {
      indexedList.put(i, i);
      assertEquals(LENGTH, indexedList.length());
    }
  
    for (int i = 0; i < 10; i++) {
      indexedList.put(i, 7);
      assertEquals(LENGTH, indexedList.length());
    }
  }
  
  @Test
  @DisplayName("test iterator after IndexedList is instantiated.")
  void testEnhancedLoopAfterConstruction() {
    int counter = 0;
    for (int element : indexedList) {
      assertEquals(INITIAL, element);
      counter++;
    }
    assertEquals(LENGTH, counter);
  }
  
  @Test
  @DisplayName("test iterator after IndexedList is instantiated and values are inserted.")
  void testEnhancedLoopAfterConstructionAndInserted() {
    for (int i = 0; i < 10; i++) {
      indexedList.put(i, 8);
    }
    int counter = 0;
    
    for (int element : indexedList) {
      assertEquals(8, element);
      counter++;
    }
    assertEquals(LENGTH, counter);
  }
  
  @Test
  @DisplayName("calling next when hasNext returns false")
  void testNextWhenHasNextReturnsFalse() {
    Iterator<Integer> it = indexedList.iterator();
    for (int i = 0; i < LENGTH; i++) {
      it.next();
    }
    try {
      it.next();
      fail("next doesn't throw NoSuchElementException when hasNext returns false");
    } catch (NoSuchElementException ex) {
      return;
    }
  }
  
  @Test
  @DisplayName("test if iterator returns correct value when list is initiated")
  void testIteratorReturnsCorrectValueWhenListInitiated() {
    Iterator<Integer> it = indexedList.iterator();
    for (int i = 0; i < LENGTH; i++) {
      assertEquals(INITIAL, it.next());
    }
  }
  
  @Test
  @DisplayName("test if iterator returns correct value when list is initiated and values modified")
  void testIteratorReturnsCorrectValueWhenListInitiatedAndModified() {
    for (int i = 0; i < 10; i++) {
      indexedList.put(i, i);
    }
    
    Iterator<Integer> it = indexedList.iterator();
    for (int i = 0; i < LENGTH; i++) {
      assertEquals(i, it.next());
    }
  }
  
}
