package eu.kaluzinski.lib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

class CustomLinkedListTest {

  @Test
  void shouldReturnSizeOfEmptyList() {
    var list = new CustomLinkedList<>();
    assertTrue(list.isEmpty());
    assertEquals(0, list.size());
  }

  @Test
  void shouldAddFirstElementToTheList() {
    var element = Integer.valueOf(4);
    var list = new CustomLinkedList<Integer>();
    list.add(element);
    assertFalse(list.isEmpty());
    assertEquals(1, list.size());
    assertEquals(element, list.get(0));
    assertEquals(element, list.getTail());
  }

  @Test
  void shouldAddNextElementsToTheList() {
    var firstElement = Integer.valueOf(4);
    var secondElement = Integer.valueOf(5);
    var thirdElement = Integer.valueOf(1);
    var list = new CustomLinkedList<Integer>();

    list.add(firstElement);
    list.add(secondElement);
    list.add(thirdElement);

    assertFalse(list.isEmpty());
    assertEquals(3, list.size());
    assertEquals(firstElement, list.get(0));
    assertEquals(firstElement, list.getHead());

    assertEquals(secondElement, list.get(1));

    assertEquals(thirdElement, list.getTail());
    assertEquals(thirdElement, list.get(2));
  }

  @Test
  void shouldThrowAnExceptionWhileGettingIncorrectElement() {
    var list = new CustomLinkedList<Integer>();
    assertThrows(IndexOutOfBoundsException.class,
        () -> list.get(0),
        "Expected NoSuchElementException wasn't thrown"
    );

    list.add(Integer.valueOf(4));
    assertThrows(IndexOutOfBoundsException.class,
        () -> list.get(-1),
        "Expected NoSuchElementException wasn't thrown"
    );
    assertThrows(IndexOutOfBoundsException.class,
        () -> list.get(2),
        "Expected NoSuchElementException wasn't thrown"
    );

  }

  @Test
  void shouldSetNthElementsOfTheList() {
    var firstElement = 4;
    var secondElement = 5;
    var thirdElement = 1;
    var newSecondElementValue = 11;
    var list = new CustomLinkedList<Integer>();

    list.add(firstElement);
    list.add(secondElement);
    list.add(thirdElement);

    var result = list.set(1, newSecondElementValue);

    assertEquals(newSecondElementValue, result);
    assertEquals(firstElement, list.get(0));
    assertEquals(newSecondElementValue, list.get(1));
    assertEquals(thirdElement, list.get(2));
  }

  @Test
  void shouldThrowAnExceptionWhileSettingIncorrectElement() {
    var list = new CustomLinkedList<Integer>();
    assertThrows(IndexOutOfBoundsException.class,
        () -> list.set(0, 11),
        "Expected NoSuchElementException wasn't thrown"
    );
    list.add(1);
    assertThrows(IndexOutOfBoundsException.class,
        () -> list.set(1, 11),
        "Expected NoSuchElementException wasn't thrown"
    );
  }
}