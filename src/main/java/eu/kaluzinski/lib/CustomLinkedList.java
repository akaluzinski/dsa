package eu.kaluzinski.lib;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class CustomLinkedList<T> implements List<T> {

  private Node<T> tail;
  private Node<T> head;
  private int length;

  @Override
  public int size() {
    return length;
  }

  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  @Override
  public boolean contains(Object o) {
    return false;
  }

  @Override
  public Iterator<T> iterator() {
    return null;
  }

  @Override
  public void forEach(Consumer<? super T> action) {
    List.super.forEach(action);
  }

  @Override
  public Object[] toArray() {
    return new Object[0];
  }

  @Override
  public <T1> T1[] toArray(T1[] a) {
    return null;
  }

  @Override
  public <T1> T1[] toArray(IntFunction<T1[]> generator) {
    return List.super.toArray(generator);
  }

  @Override
  public boolean add(T value) {
    var newNode = new Node<T>(value);
    if (isEmpty()) {
      head = newNode;
    } else {
      tail.next = newNode;
    }
    tail = newNode;
    ++length;
    return true;
  }

  @Override
  public boolean remove(Object o) {
    return false;
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return false;
  }

  @Override
  public boolean addAll(Collection<? extends T> c) {
    return false;
  }

  @Override
  public boolean addAll(int index, Collection<? extends T> c) {
    return false;
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return false;
  }

  @Override
  public boolean removeIf(Predicate<? super T> filter) {
    return List.super.removeIf(filter);
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return false;
  }

  @Override
  public void replaceAll(UnaryOperator<T> operator) {
    List.super.replaceAll(operator);
  }

  @Override
  public void sort(Comparator<? super T> c) {
    List.super.sort(c);
  }

  @Override
  public void clear() {

  }

  @Override
  public T get(int index) {
    verifyListSize(index);

    if (index == 0) {
      return getHead();
    }

    if (index == size() - 1) {
      return getTail();
    }

    return getNode(index).value;
  }

  private Node<T> getNode(int index) {
    var temp = head;
    for (int i = 0; i < index; ++i) {
      temp = temp.next;
    }

    return temp;
  }

  public T getHead() {
    return head.value;
  }

  public T getTail() {
    return tail.value;
  }

  @Override
  public T set(int index, T element) {
    verifyListSize(index);
    var temp = getNode(index);
    if (temp != null) {
      temp.value = element;
    }
    return element;
  }

  @Override
  public void add(int index, T element) {

  }

  @Override
  public T remove(int index) {
    verifyListSize(index);

    if (index == 0) {
      return removeFirst();
    }

    var previous = getNode(index - 1);
    var temp = previous.next;
    previous.next = temp.next;
    temp.next = null;
    length--;
    return temp.value;
  }

  public T removeFirst() {
    var temp = head;
    head = head.next;
    temp.next = null;
    length--;
    if (isEmpty()) {
      tail = null;
    }

    return temp.value;
  }

  @Override
  public int indexOf(Object o) {
    return 0;
  }

  @Override
  public int lastIndexOf(Object o) {
    return 0;
  }

  @Override
  public ListIterator<T> listIterator() {
    return null;
  }

  @Override
  public ListIterator<T> listIterator(int index) {
    return null;
  }

  @Override
  public List<T> subList(int fromIndex, int toIndex) {
    return null;
  }

  @Override
  public Spliterator<T> spliterator() {
    return List.super.spliterator();
  }

  @Override
  public Stream<T> stream() {
    return List.super.stream();
  }

  @Override
  public Stream<T> parallelStream() {
    return List.super.parallelStream();
  }

  private void verifyListSize(int index) {
    if (index < 0 || index >= size()) {
      throw new IndexOutOfBoundsException(
          "No element at index %d. List size is %d".formatted(index, size()));
    }
  }

}
