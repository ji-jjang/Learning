package chapter3;

public class MyQueue<T> {

  private static class QueueNode<T> {
    private T data;
    private QueueNode next;
    public QueueNode(T data) {
      this.data = data;
    }
  }

  private QueueNode<T> first;
  private QueueNode<T> last;

  public T remove() throws NoSuchFieldException {
    if (first == null) {
      throw new NoSuchFieldException();
    }
    T data = first.data;
    first = first.next;
    if (first == null) {
      last = null;
    }
    return data;
  }

  public void add(T data) {
    QueueNode<T> newNode = new QueueNode<>(data);
    if (last != null) {
      last.next = newNode;
    }
    last = newNode;
    if (first == null) {
      first = last;
    }
  }

  public T peek() throws NoSuchFieldException {
    if (first == null) {
      throw new NoSuchFieldException();
    }
    return first.data;
  }

  public boolean isEmpty() {
    return first == null;
  }

  public static void main(String[] args) throws NoSuchFieldException {
    MyQueue<Integer> myQueue = new MyQueue<Integer>();
    myQueue.add(1);
    myQueue.add(2);
    myQueue.add(3);
    while (!myQueue.isEmpty()) {
      System.out.println("myQueue.peek() = " + myQueue.peek());
      myQueue.remove();
    }
  }
}
