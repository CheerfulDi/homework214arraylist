package pro.sky.java.course2.service;

import pro.sky.java.course2.exceptions.StringListItemDoesNotExistException;
import pro.sky.java.course2.exceptions.StringListItemIndexOutOfBoundsException;
import pro.sky.java.course2.exceptions.StringListItemIsNullException;

import java.util.Arrays;

public class StringListImpl implements StringList {

    // Т.е. в случае удаления элемента, нужно смещать все элементы на ячейку влево,
    // а при добавлении в середину или начало, на ячейку вправо.

    public static final int DEFAULT_CAPACITY = 10;

    private String[] array;
    private int size;

    public StringListImpl() {this(DEFAULT_CAPACITY);}

    public StringListImpl (int capacity) {this.array = new String[capacity];}


    @Override
    public String add(String item) {
        checkNotNull(item);
        checkCapacity();
        array[size++] = item;

        return item;
    }

    @Override
    public String add(int index, String item) {
        checkNotNull(item);
        checkCapacity();
        checkIndex(index);
        System.arraycopy(array, index, array, index + 1, array.length - index);
        array[size++] = item;
        return item;
    }

    @Override
    public String set(int index, String item) {
        checkNotNull(item);
        checkIndex(index);
        array[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        checkNotNull(item);
        checkItemExists(item);
        int index = indexOf(item);
        removeItem(index);
        return "Item " + item + " is removed.";
    }

    @Override
    public String remove(int index) {
        checkIndex(index);
        removeItem(index);
        return "Item № " + array[index] + " is removed.";
    }

    public void removeItem(int index) {
        if (size - 1 > index) {
            System.arraycopy(array, index + 1, array, index, array.length - index);
        }
        array[--size] = null;
    }

    @Override
    public boolean contains(String item) {
        checkItemExists(item);
        return true;
    }

    @Override
    public int indexOf(String item) {
        checkItemExists(item);
        for (int i = 0; i < size; i++) {
            if (item.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        checkItemExists(item);
        for (int i = size-1; i >= 0; i--) {
            if (item.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            return false;
        }
        if (size != otherList.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!get(i).equals(otherList.get(i))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        array = new String[DEFAULT_CAPACITY];

    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(array, array.length);
    }

    private void checkNotNull(String item) {
        if (item == null) {
            throw new StringListItemIsNullException();
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new StringListItemIndexOutOfBoundsException();
        }
    }

    private void checkItemExists(String item) {
        if (indexOf(item) == -1) {
            throw new StringListItemDoesNotExistException();
        }
    }

    private void checkCapacity() {
        if (size == array.length) {
            array = grow();
        }
    }

    private String[] grow() {
        return Arrays.copyOf(array,array.length * 2);
    }
}
