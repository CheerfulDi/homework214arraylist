package pro.sky.java.course2;

import java.util.Arrays;

public class CheckSortsForArray {

    public static void main (String[]args) {

        Integer[] array = generateRandomArray();
        Integer[] array2 = Arrays.copyOf(array,array.length);
        Integer[] array3 = Arrays.copyOf(array,array.length);

        checkSortBubble(array); // 64949
        checkSortSelection(array2); // 8584 - the fastest!
        checkSortInsertion(array3); // 25927

    }

    public static Integer[] generateRandomArray() {
        java.util.Random random = new java.util.Random();
        Integer[] array = new Integer[100_000];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100_000) + 100_000;
        }
        return array;
    }

    public static void checkSortBubble(Integer[] array) {
        long start = System.currentTimeMillis();
        sortBubble(array);
        System.out.println(System.currentTimeMillis() - start);
    }

    public static void checkSortSelection(Integer[] array) {
        long start = System.currentTimeMillis();
        sortSelection(array);
        System.out.println(System.currentTimeMillis() - start);
    }

    public static void checkSortInsertion(Integer[] array) {
        long start = System.currentTimeMillis();
        sortInsertion(array);
        System.out.println(System.currentTimeMillis() - start);
    }


    public static void sortBubble(Integer[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    swapElements(array, j, j + 1);
                }
            }
        }
    }

    public static void sortSelection(Integer[] array) {
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

    public static void sortInsertion(Integer[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i;
            while (j > 0 && array[j - 1] >= temp) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = temp;
        }
    }

        private static void swapElements(Integer[] array, int indexA, int indexB) {
            int tmp = array[indexA];
            array[indexA] = array[indexB];
            array[indexB] = tmp;
        }
    }



