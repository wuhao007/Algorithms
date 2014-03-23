import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

  private Node first = null;
  private Node last = null;
  private int size = 0;

  // construct an empty deque
  public Deque() {

  }


  private class DequeIterator implements Iterator<Item> {
    private Node current = first;

    public boolean hasNext() {
      return current != null;
    }

    public Item next() {
      if (!hasNext()) throw new NoSuchElementException();
      Node outNode = current;
      current = current.next;
      return outNode.item;
    }
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  private class Node {
    private Node prev;
    private Node next;
    private Item item;
  }



  private void checkNullItem(Item item) {
    if (item == null) throw new NullPointerException();
  }
  private void checkElementNum() {
    if (size == 0) throw new NoSuchElementException();
  }

	// is the deque empty?
  public boolean isEmpty() {
    return size == 0;
  }

	// return the number of items on the deque
  public int size() {
    return size;
  }

	// insert the item at the front
  public void addFirst(Item item) {
    checkNullItem(item);
    Node newNode = new Node();
    newNode.item = item;
    if (size == 0) {
      first = newNode;
      last = newNode;
    } else {
      first.prev = newNode;
      newNode.next = first;
      first = newNode;
    }
    size++;
  }     


  // insert the item at the end
  public void addLast(Item item) {
    checkNullItem(item);
    Node newNode = new Node();
    newNode.item = item;
    if (size == 0) {
      first = newNode;
      last = newNode;
    } else {
      last.next = newNode;
      newNode.prev = last;
      last = newNode;
    }
    size++;
  }


	// delete and return the item at the front
  public Item removeFirst() {
    checkElementNum();
    Node outNode = first;
    if (size == 1) {
      first = null;
      last = null;
    } else {			
      first = first.next;
      outNode.next = null;
      first.prev = null;
    }		
    size--;
    return outNode.item;
  }                


  // delete and return the item at the end
  public Item removeLast() {
    checkElementNum();
    Node outNode = last;
    if (size == 1) {
      first = null;
      last = null;
    } else {
      last = last.prev;
      outNode.prev = null;
      last.next = null;
   }		
   size--;
   return outNode.item;
  }                


	// return an iterator over items in order from front to end
  public Iterator<Item> iterator() {
    return new DequeIterator();
  } 

	// unit testing
  public static void main(String[] args) {
    Deque<Integer> deque = new Deque<Integer>();


    StdOut.println("======== TEST 1 =======");
    StdOut.print("Test Result: ");
    try {
     deque.removeFirst();
    }
    catch (NoSuchElementException ex) {
      StdOut.println("throw NoSuchElementException exception");
    }
    StdOut.println("Should throw NoSuchElementException exception");


    StdOut.println("======== TEST 2 =======");
    StdOut.print("Test Result: ");
    try {
     deque.addFirst(null);
    }
    catch (NullPointerException ex) {
      StdOut.println("throw NullPointerException exception");
    }
    StdOut.println("Should throw NullPointerException exception");

    StdOut.println("======== TEST 3 =======");
    deque.addFirst(0);
    deque.addFirst(1);
    deque.addFirst(2);
    deque.addFirst(3);		
    StdOut.print("Test Result: ");
    StdOut.print(deque.removeFirst());		
    StdOut.print(",");
    StdOut.print(deque.removeLast());		
    StdOut.print(",");
    StdOut.print(deque.removeLast());		
    StdOut.print(",");
    StdOut.print(deque.removeFirst());
    StdOut.print(",");
    StdOut.print(deque.isEmpty());
    StdOut.println();
    StdOut.println("Should be 3,0,1,2,true");


    StdOut.println("======== TEST 4 =======");
    deque.addLast(0);
    deque.addLast(1);
    deque.addLast(2);
    deque.addLast(3);		
    StdOut.print("Test Result: ");
    StdOut.print(deque.removeFirst());
    StdOut.print(",");
    StdOut.print(deque.removeLast());		
    StdOut.print(",");
    StdOut.print(deque.removeLast());	
    StdOut.print(",");
    StdOut.print(deque.isEmpty());
    StdOut.print(",");
    StdOut.print(deque.removeFirst());
    StdOut.print(",");
    StdOut.print(deque.isEmpty());
    StdOut.println();
    StdOut.println("Should be 0,3,2,false,1,true");


    StdOut.println("======== TEST 5 =======");		
    deque.addLast(4);
    deque.addFirst(5);
    StdOut.print("Test Result: ");
    StdOut.print(deque.removeLast());
    StdOut.print(",");
    StdOut.print(deque.removeLast());
    StdOut.print(",");
    try {
     deque.removeFirst();
    }
    catch (NoSuchElementException ex) {
      StdOut.print("throw exception");
    }
    StdOut.println();
    StdOut.println("Should be 4,5,throw exception");

    StdOut.println("======== TEST 6 =======");
    StdOut.print("Test Result: ");
    for (int i = 0; i < 4; i++) {
      deque.addFirst(i);   
    }    
    for (int item : deque) {
      StdOut.print(item);
      StdOut.print(",");
    }
    StdOut.println();
    StdOut.println("Should be 3,2,1,0");

  }
}