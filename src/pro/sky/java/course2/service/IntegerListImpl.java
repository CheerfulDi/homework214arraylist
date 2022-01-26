package pro.sky.java.course2.service;

import pro.sky.java.course2.exceptions.*;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList{

    private Integer[] array;
    private int size;

    public IntegerListImpl() {this.array = generateRandomArray();}

    public static Integer[] generateRandomArray() {
        java.util.Random random = new java.util.Random();
        Integer[] array = new Integer[100_000];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100_000) + 100_000;
        }
        return array;
    }


    @Override
    public Integer add(Integer item) {
        checkNotNull(item);
        checkCapacity();
        array[size++] = item;

        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        checkNotNull(item);
        checkIndex(index);
        checkCapacity();
        System.arraycopy(array, index, array, index + 1, array.length - index);
        array[size++] = item;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        checkNotNull(item);
        checkIndex(index);
        array[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        checkNotNull(item);
        checkItemExists(item);
        int index = indexOf(item);
        removeItem(index);
        return item;
    }

    @Override
    public Integer remove(int index) {
        checkIndex(index);
        removeItem(index);
        return array[index];
    }

    public void removeItem(int index) {
        if (size - 1 > index) {
            System.arraycopy(array, index + 1, array, index, array.length - index);
        }
        array[--size] = null;
    }

    @Override
    public boolean contains(Integer[] array, int element) {
        sortSelection(array);
        int min = 0;
        int max = array.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (element == array[mid]) {
                return true;
            }

            if (element < array[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        checkItemExists(item);
        for (int i = 0; i < size; i++) {
            if (item.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        checkItemExists(item);
        for (int i = size-1; i >= 0; i--) {
            if (item.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
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
        array = new Integer[100_000];

    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(array, array.length);
    }

    private void checkNotNull(Integer item) {
        if (item == null) {
            throw new IntegerListItemIsNullException();
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IntegerListItemIndexOutOfBoundsException();
        }
    }

    private void checkItemExists(Integer item) {
        if (indexOf(item) == -1) {
            throw new IntegerListItemDoesNotExistException();
        }
    }

    private void checkCapacity() {
        if (size == array.length) {
            array = grow();
        }
    }

    private Integer[] grow() {
        return Arrays.copyOf(array,array.length * 2);
    }

    private void sortSelection(Integer[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(array, i, minElementIndex);
        }
    }

    private void swapElements(Integer[] array, int indexA, int indexB) {
        int tmp = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = tmp;
    }
}
