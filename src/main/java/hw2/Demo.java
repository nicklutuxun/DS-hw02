package hw2;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Demo {
  public static void main(String[] args) {
    final long before = System.nanoTime();
    IndexedList<Integer> sparse = new SparseIndexedList<>(10000, 10);
//    Iterator it = sparse.iterator();
//    System.out.println(it.next());
//    final long after = System.nanoTime();
//    //long seconds = TimeUnit.NANOSECONDS.toSeconds(after - before);
//    System.out.println(after);
    Random rand = new Random();
//    Iterator it = sparse.iterator();
//    for (int i = 0; i < 10; i++) {
//      System.out.println(it.next());
//    }
    for (int i = 0; i < 100; i++) {
      int random1 = rand.nextInt(10);
      int random2 = rand.nextInt(5) + 6;
      sparse.put(random1, random2);
    }
  }
}
