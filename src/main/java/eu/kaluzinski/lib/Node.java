package eu.kaluzinski.lib;

class Node<T> {
  T value;
  Node<T> next;

  Node(T value) {
    this.value = value;
  }
}