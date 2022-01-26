package pro.sky.java.course2;

import java.util.Arrays;

public class CheckSortsForArray {

    public static void main (String[]args) {

        Integer[] array = generateRandomArray();
        Integer[] array2 = Arrays.copyOf(array,array.length);
        Integer[] array3 = Arrays.copyOf(array,array.length);
        Integer[] array4 = Arrays.copyOf(array,array.length);
        Integer[] array5 = Arrays.copyOf(array,array.length);

        checkSortBubble(array); // 64949
        checkSortSelection(array2); // 8584
        checkSortInsertion(array3); // 25927
        checkQuickSort(array4); // 45
        checkMergeSort(array5); // 58

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

    public static void checkQuickSort(Integer[] array) {
        long start = System.currentTimeMillis();
        quickSort(array,0, array.length-1);
        System.out.println(System.currentTimeMillis() - start);
    }

    public static void checkMergeSort(Integer[] array) {
        long start = System.currentTimeMillis();
        mergeSort(array);
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

    public static void quickSort(Integer[] array, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(array, begin, end);

            quickSort(array, begin, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, end);
        }
    }

    private static int partition(Integer[] array, int begin, int end) {
        int pivot = array[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (array[j] <= pivot) {
                i++;

                swapElements(array, i, j);
            }
        }

        swapElements(array, i + 1, end);
        return i + 1;
    }

    public static void mergeSort(Integer[] array) {
        if (array.length < 2) {
            return;
        }
        int mid = array.length / 2;
        Integer[] left = new Integer[mid];
        Integer[] right = new Integer[array.length - mid];

        for (int i = 0; i < left.length; i++) {
            left[i] = array[i];
        }

        for (int i = 0; i < right.length; i++) {
            right[i] = array[mid + i];
        }

        mergeSort(left);
        mergeSort(right);

        merge(array, left, right);
    }

    public static void merge(Integer[] array, Integer[] left, Integer[] right) {

        int mainP = 0;
        int leftP = 0;
        int rightP = 0;
        while (leftP < left.length && rightP < right.length) {
            if (left[leftP] <= right[rightP]) {
                array[mainP++] = left[leftP++];
            } else {
                array[mainP++] = right[rightP++];
            }
        }
        while (leftP < left.length) {
            array[mainP++] = left[leftP++];
        }
        while (rightP < right.length) {
            array[mainP++] = right[rightP++];
        }
    }
    }



