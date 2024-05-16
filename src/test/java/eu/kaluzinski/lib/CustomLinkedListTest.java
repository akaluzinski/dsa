package eu.kaluzinski.lib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CustomLinkedListTest {

  @Test
  void shouldReturnSizeOfEmptyList() {
    var list = new CustomLinkedList<>();
    assertTrue(list.isEmpty());
    assertEquals(0, list.size());
  }

  @Test
  void shouldAddSingleElementToTheList() {
    var element = Integer.valueOf(4);
    var list = new CustomLinkedList<Integer>();
    list.add(element);
    assertFalse(list.isEmpty());
    assertEquals(1, list.size());
    assertEquals(list.get(0), element);
  }
}