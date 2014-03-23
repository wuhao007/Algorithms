import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

  private int size = 0;
  private Item[] itemArray;

  public RandomizedQueue() {
    itemArray = (Item[]) new Object[1];
  }


  private void resize(int capacity) {
    Item[] newArray = (Item[]) new Object[capacity];
    for (int i = 0; i < size; i++) {
      newArray[i] = itemArray[i];
    }
    itemArray = newArray;
  }
  

  // construct an empty randomized queue
  private class RandomizedQueueIterator implements Iterator<Item> {
    private Item[] iteratorArray;
    private int currentIndex = 0;

    private RandomizedQueueIterator() {
      iteratorArray = (Item[]) new Object[size];
      for (int i = 0; i < size; i++) {
          iteratorArray[i] = itemArray[i];
      }
      StdRandom.shuffle(iteratorArray);
    }

    public boolean hasNext() {
      return currentIndex < size;
    }
    public Item next() {
      if (!hasNext()) throw new NoSuchElementException();
      return iteratorArray[currentIndex++];
    }
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  private void checkNullItem(Item item) {
    if (item == null) throw new NullPointerException();
  }
  private void checkElementNum() {
    if (size == 0) throw new NoSuchElementException();
  }


  // is the queue empty?
  public boolean isEmpty() {
    return size == 0;
  }       

  // return the number of items on the queue
  public int size() {
    return size;
  }       

  // add the item
  public void enqueue(Item item) {
    checkNullItem(item);
    itemArray[size] = item;
    size++;
    //resize 
    if (itemArray.length == size) {
      resize(size*2);
    }
  } 

  // delete and return a random item
  public Item dequeue() {
    checkElementNum();
    int index = StdRandom.uniform(size);
    Item item = itemArray[index];

    size--;
    //move last item into position index;
    if (index < size) {
      itemArray[index] = itemArray[size];
    }

    itemArray[size] = null;
    
    if (itemArray.length > 1 && size < itemArray.length/4) {
      resize(itemArray.length/2);
    }
    return item;
  }                    
  // return (but do not delete) a random item
  public Item sample() {
    checkElementNum();
    Item item = itemArray[StdRandom.uniform(size)];
    return item;
  }                


  // return an independent iterator over items in random order
  public Iterator<Item> iterator() {
    return new RandomizedQueueIterator();
  }         
  // unit testing
  public static void main(String[] args) {
    RandomizedQueue<Integer> rqueue = new RandomizedQueue<Integer>();

    StdOut.println("======== TEST 1 =======");
    StdOut.print("Test Result: ");
    try {     
      rqueue.dequeue();
    }
    catch (NoSuchElementException ex) {
      StdOut.println("throw NoSuchElementException exception");
    }
    StdOut.println("Should throw NoSuchElementException exception");    


    StdOut.println("======== TEST 2 =======");
    StdOut.print("Test Result: ");
    try {
     rqueue.enqueue(null);
    }
    catch (NullPointerException ex) {
      StdOut.println("throw NullPointerException exception");
    }
    StdOut.println("Should throw NullPointerException exception");

    StdOut.println("======== TEST 3 =======");  
    rqueue.enqueue(0);
    rqueue.enqueue(0);
    StdOut.print("Test Result: ");
    StdOut.print(rqueue.dequeue());
    StdOut.print(",");
    StdOut.print(rqueue.dequeue());
    StdOut.print(",");
    try {     
      rqueue.dequeue();
    }
    catch (NoSuchElementException ex) {
      StdOut.print("throw NoSuchElementException exception");
    }
    StdOut.println();
    StdOut.println("Should be 0,0,throw NoSuchElementException exception");

    StdOut.println("======== TEST 3 =======");
    StdOut.print("Test Result: ");
    rqueue.enqueue(0);
    StdOut.print(rqueue.dequeue());
    StdOut.print(",");
    rqueue.enqueue(1);
    StdOut.print(rqueue.dequeue());
    StdOut.print(",");
    rqueue.enqueue(2);
    StdOut.print(rqueue.dequeue());
    StdOut.println();
    StdOut.println("Should be 0,1,2");

    StdOut.println("======== TEST 4 =======");
    StdOut.print("Test Result: ");
    for (int i = 0; i < 10; i++) {
      rqueue.enqueue(i);  
    }
    for (int i = 0; i < 10; i++) {
      StdOut.print(rqueue.dequeue());
      StdOut.print(",");
    }
    StdOut.println();
    StdOut.println("Should be randomly from 1 to 9");

    StdOut.println("======== TEST 5 =======");
    StdOut.print("Test Result: ");
    for (int i = 0; i < 2; i++) {
      rqueue.enqueue(i);
    }
    for (int i = 0; i < 4; i++) {
      StdOut.print(rqueue.sample());
      StdOut.print(",");
    }
    StdOut.print(rqueue.sample());
    StdOut.println();
    StdOut.println("Should be randomly show 0,1 five times");

    StdOut.println("======== TEST 6 =======");
    StdOut.print("Test Result: ");
    rqueue.dequeue();
    for (int i = 0; i < 4; i++) {
      StdOut.print(rqueue.sample());
      StdOut.print(",");
    }    
    StdOut.print(rqueue.dequeue());
    StdOut.println();
    StdOut.println("Should be randomly show only 0 or only 1 five times");

    StdOut.println("======== TEST 7 =======");
    StdOut.print("Test Result: ");
    for (int i = 0; i < 4; i++) {
      rqueue.enqueue(i);   
    }    
    for (int item : rqueue) {
      StdOut.print(item);
      StdOut.print(",");
    }
    StdOut.println();
    StdOut.println("Should be 0,1,2,3,");

  }   
}
