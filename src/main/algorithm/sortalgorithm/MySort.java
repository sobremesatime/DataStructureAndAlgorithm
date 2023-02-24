package algorithm.sortalgorithm;

import org.junit.Test;

public class MySort {
    @Test
    public void test() {
        //插入排序
        int[] array = new int[]{5, 4, 9, 8, 1, 3, 2, 7, 7, 6};
        int[] dt = {7, 5, 3, 1};
        shellSort(array, dt, 4);
        for (int t :
                array) {
            System.out.print(t);
        }
    }

    //插入排序
    public void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            if (array[i] < array[i - 1]) {
                int j = i - 1;
                for (; j >= 0 && array[j] > temp; j--) {
                    array[j + 1] = array[j];
                }
                array[j + 1] = temp;
            }
        }
    }

    //折半插入排序
    public void binInsertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                int temp = array[i];
                int low = 0, high = i - 1;
                while (low <= high) {
                    int mid = (low + high) / 2;
                    if (array[mid] <= temp)
                        low = mid + 1;
                    else
                        high = mid - 1;
                }
                int j = i - 1;
                for (; j >= low; j--) {
                    array[j + 1] = array[j];
                }
                array[j + 1] = temp;
            }
        }
    }

    //希尔排序
    public void shellSort(int[] array, int[] dt, int n) {
        for (int i = 0; i < n; i++) {
            shellInsertSort(array, dt[i]);
        }
    }

    public void shellInsertSort(int[] array, int dk) {
        for (int i = dk; i < array.length; i++) {
            if (array[i] < array[i - dk]){
                int temp = array[i];
                int j = i - dk;
                for (; j >= 0 && array[j] > temp; j -= dk) {
                    array[j + dk] = array[j];
                }
                array[j + dk] = temp;
            }
        }
    }

    //冒泡排序
    public void bubbleSort(int[] array) {
        boolean flag = true;
        int m = array.length - 1;
        while (m > 0 && flag) {
            flag = false;
            for (int i = 0; i < m - 1; i++) {
                if (array[i] > array[i + 1]) {
                    flag = true;
                    int temp = array[i + 1];
                    array[i + 1] = array[i];
                    array[i] = temp;
                }
            }
            m--;
        }
    }

    //快速排序
    public void quickSort(int[] array) {
        qSort(array, 0, array.length - 1);
    }

    public void qSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotloc = partition(array, low, high);
            qSort(array, low, pivotloc - 1);
            qSort(array, pivotloc + 1, high);
        }
    }

    public int partition(int[] array, int low, int high) {
        int temp = array[low];
        while (low < high) {
            while (low < high && array[high] >= temp)
                high--;
            array[low] = array[high];
            while (low < high && array[low] <= temp)
                low++;
            array[high] = array[low];
        }
        array[low] = temp;
        return low;
    }

    //简单选择排序
    public void selectSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int k = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[k]) {
                    k = j;
                }
            }
            if (k != i) {
                int temp = array[i];
                array[i] = array[k];
                array[k] = temp;
            }
        }
    }

    //堆排序

    //归并排序
    public int[] mergeSort(int[] array){
        int[] res = new int[array.length];
        mSort(array, res, 0, array.length - 1);
        return res;
    }
    public void mSort(int[] array, int[] res, int low, int high){
        if (low == high){
            res[low] = array[low];
        }else {
            int[] ints = new int[array.length];
            int mid = (low + high) / 2;
            mSort(array, ints, low, mid);
            mSort(array, ints, mid + 1, high);
            merge(ints, res, low, mid, high);
        }
    }
    public void merge(int[] array, int[] res, int low, int mid, int high) {
        int i = low, j = mid + 1, k = low;
        while (i <= mid && j <= high){
            if (array[i] <= array[j]) {
                res[k++] = array[i++];
            }else {
                res[k++] = array[j++];
            }
            while (i <= mid){
                res[k++] = array[i++];
            }
            while (j <= high){
                res[k++] = array[j++];
            }
        }
    }

}
