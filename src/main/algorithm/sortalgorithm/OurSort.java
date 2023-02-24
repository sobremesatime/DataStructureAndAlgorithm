package algorithm.sortalgorithm;

import org.junit.Test;

/**
 * @Title: OurSort
 * @Description: 排序算法
 * @author: LYL
 * @date: 2023/2/23 9:30
 */
public class OurSort {
    @Test
    public void test() {
        //插入排序
        int[] array = new int[]{5, 4, 9, 8, 1, 3, 2, 7, 7, 6};
        int[] dt = {7, 5, 3, 1};
//        shellSort(array, dt, 4);
        array = mergeSort(array);
        for (int t :
                array) {
            System.out.print(t);
        }
    }
    //插入排序
    public void insertSort(int[] array){
        for (int i = 1; i < array.length; i++){
            if (array[i] < array[i - 1]){
                int temp = array[i];
                int j = i - 1;
                for (; j >= 0 && array[j] > temp; j--){
                    array[j + 1] = array[j];
                }
                array[j + 1] = temp;
            }
        }
    }

    //折半插入排序
    public void binInsertSort(int[] array){
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i -1]){
                int left = 0;
                int right = i - 1;
                while (left <= right){
                    int mid = (left + right) >> 1;
                    if (array[mid] <= array[i])
                        left = mid + 1;
                    else
                        right = mid - 1;
                }
                int temp = array[i];
                int j = i - 1;
                for (; j >= left; j--){
                    array[j + 1] = array[j];
                }
                array[j + 1] = temp;
            }
        }
    }

    //希尔排序
    public void shellSort(int[] array, int[] dt, int n){
        for (int i = 0; i < n; i++){
            shell(array, dt[i]);
        }
    }

    public void shell(int[] array, int dk){
        for (int i = dk; i < array.length; i++){
            if (array[i] < array[i - dk]){
                int temp = array[i];
                int j = i - dk;
                for (; j >= 0 && array[j] > temp; j -= dk){
                    array[j + dk] = array[j];
                }
                array[j + dk] = temp;
            }
        }
    }

    //冒泡排序
    public void bubbleSort(int[] array){
        int m = array.length - 1;
        boolean flag = true;
        while (m > 0 && flag == true){
            flag = false;
            for (int i = 0; i < m; i++) {
                if (array[i] > array[i + 1]){
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
    public void quickSort(int[] array){
        QSort(array, 0, array.length - 1);
    }

    public void QSort(int[] array, int low, int high){
        if (low < high){
            int pivotloc = partition(array, low, high);
            QSort(array, low, pivotloc - 1);
            QSort(array, pivotloc + 1, high);
        }
    }

    public int partition(int[] array, int low, int high){
        int temp = array[low];
        while (low < high){
            while (low < high && array[high] >= temp)
                high--;
            array[low] = array[high];
            while (low < high &&array[low] <= temp)
                low++;
            array[high] = array[low];
        }
        array[low] = temp;
        return low;
    }

    //简单选择排序
    public void selectSort(int[] array){
        for (int i = 0; i < array.length - 1; i++) {
            int k = i;
            for (int j = i + 1; j < array.length; j++){
                if (array[j] < array[k]){
                    k = j;
                }
            }
            if (k != i){
                int temp = array[i];
                array[i] = array[k];
                array[k] = temp;
            }
        }
    }

    //归并算法
    public int[] mergeSort(int[] array){
        int[] res = new int[array.length];
        mSort(array, res, 0, array.length - 1);
        return res;
    }
    public void mSort(int[] array, int[] res, int low, int high){
       if (low == high)
           res[low] = array[low];
       else{
           int[] tempArray = new int[array.length];
           int mid = (low + high) >> 1;
           mSort(array, tempArray, low, mid);
           mSort(array, tempArray, mid + 1, high);
           merge(tempArray, res, low, high, mid);
       }
    }

    public void merge(int[] array, int[] res, int low, int high, int mid){
        int i = low, j = mid + 1, k = low;
        while (i <= mid && j <= high){
            if (array[i] <= array[j]){
                res[k++] = array[i++];
            }else{
                res[k++] = array[j++];
            }
        }
        while (i <= mid)
            res[k++] = array[i++];
        while (j <= high)
            res[k++] = array[j++];
    }
}
