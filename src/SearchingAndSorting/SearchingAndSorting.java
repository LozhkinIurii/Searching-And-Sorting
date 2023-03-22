package SearchingAndSorting;

import java.util.Random;
import java.util.Scanner;


public class SearchingAndSorting {
    private final int[] searchArr = {0,1,2,3,4,5,6,7,8,9};
    private final Scanner scan = new Scanner(System.in);
    private static int bubbleComparisons;
    private static int selectionComparisons;
    private static int quickComparisons;
    private static int mergeComparisons;
    public void menu(){
        String menuChoice;
        boolean repeat = true;
        while(repeat) {
            System.out.print("Menu of Searching and Sorting Testbed.\n\n" +
                    "1)  Linear searching\n" +
                    "2)  Binary searching\n" +
                    "3)  O(n^2) type of sorting\n" +
                    "4)  O(n*log(n)) type of sorting\n" +
                    "5)  Sorting performance\n\n" +
                    "q/Q)  Quit\n\n" +
                    "Your choice:  ");
            menuChoice = scan.next();
            switch (menuChoice) {
                case "1" -> linearSearching();
                case "2" -> binarySearching();
                case "3" -> System.out.println(bubbleSort(10));
                case "4" -> System.out.println(quickSort(10));
                case "5" -> performance();
                case "q", "Q" -> repeat = false;
            }
        }
    }

    private void linearSearching(){
        int choice;
        int count = 0;
        System.out.print("\nIn the list are values 0, ..., 9; " +
                "which value would you like to search with linear search?:  ");
        choice = scan.nextInt();
        for (int i : searchArr) {
            if (i == choice) {
                System.out.println("\nFound\n");
                break;
            } else {
                count++;
                if(count == searchArr.length){
                    System.out.println("\nNot found\n");
                }
            }
        }
    }
    private void binarySearching(){
        int choice;
        int first = 0;
        int last = searchArr.length-1;
        int mid = (last+first)/2;
        System.out.print("\nIn the list are values 0, ..., 9; " +
                "which value would you like to search with binary search?:  ");
        choice = scan.nextInt();
        while(first <= last) {
            if (choice == searchArr[mid]) {
                System.out.println("\nFound\n");
                break;
            } else {
                if (choice > searchArr[mid]) {
                    first = mid+1;
                } else {
                    last = mid-1;
                }
                mid = (last + first)/2;
            }
        }
        if (first > last){
            System.out.println("\nNot found\n");
        }
    }


    private String bubbleSort(int size){
        bubbleComparisons = 0;
        String message = "";
        int[] bubbleArr;
        int position, scan;
        bubbleArr = randomArr(size);
        message+="\nData set before bubble sorting:\n";
        for(int a: bubbleArr)
            message += a+" ";
        for (position =  bubbleArr.length - 1; position >= 0; position--){
            for (scan = 0; scan <= position - 1; scan++){
                bubbleComparisons++;
                if (bubbleArr[scan] > bubbleArr[scan+1]) {
                    swap(bubbleArr, scan, scan+1);
                }
            }
        }
        message+= "\n\nData set after bubble sorting:\n";
        for(int a: bubbleArr){
            message += a + " ";
        }
        message += "\n";
        return message;
    }


    private void bubbleSortTime(int size){
        bubbleComparisons = 0;
        int[] bubbleArr;
        int position, scan;
        bubbleArr = randomArr(size);
        for (position =  bubbleArr.length - 1; position >= 0; position--){
            for (scan = 0; scan <= position - 1; scan++){
                bubbleComparisons++;
                if (bubbleArr[scan] > bubbleArr[scan+1]) {
                    swap(bubbleArr, scan, scan+1);
                }
            }
        }
    }

    private String quickSort(int size){
        quickComparisons=0;
        String message = "";
        int[] quickArr;
        quickArr = randomArr(size);
        message += "\nData set before quick sorting:\n";
        for(int a: quickArr)
            message += a + " ";

        quickSortHandler(quickArr, 0, quickArr.length-1);

        message += "\n\nData set after quick sorting:\n";
        for(int a:quickArr)
            message += a + " ";
        message += "\n";
        return message;
    }


    private void quickSortTime(int size){
        quickComparisons=0;

        int[] quickArr;
        quickArr = randomArr(size);

        quickSortHandler(quickArr, 0, quickArr.length-1);

    }

    private void quickSortHandler(int[] arr, int min, int max){
        if(min<max){
            int indexofpartition = partition(arr, min, max);
            quickSortHandler(arr, min, indexofpartition - 1);
            quickSortHandler(arr, indexofpartition + 1, max);

        }
    }

    private int partition(int[] arr, int min, int max){
        int partitionelement;
        int left, right;
        int middle = (min + max) / 2;

        partitionelement = arr[middle];
        swap(arr, middle, min);

        left = min;
        right = max;

        while (left < right){
            quickComparisons++;
            while (left < right && arr[left]<=partitionelement) {
                quickComparisons++;
                quickComparisons++;
                left++;
            }
            while (arr[right] > partitionelement) {
                quickComparisons++;
                right--;
            }
            quickComparisons++;
            if (left < right)
                swap(arr, left, right);
        }
        swap(arr, min, right);

        return right;
    }


    private void performance() {
        int situations = 10;
        long startTime;
        long endTime;
        int amount = 1000;
        System.out.printf("%-35s", "");
        for(int i = 1; i <= situations; i++){
            System.out.printf("%-13s", amount*i);
        }

        System.out.printf("\n%-35s","BubbleSort,random,comparisons");
        for(int i=1; i<=situations; i++){
            bubbleSort(1000*i);
            System.out.printf("%-13s", bubbleComparisons);
        }
        System.out.printf("\n%-35s","BubbleSort,random,ms");
        for(int i=1; i<= situations; i++) {
            startTime = System.nanoTime();
            bubbleSortTime(1000 * i);
            endTime = System.nanoTime();
            long bubbleSortTime = (endTime - startTime) / 1000000;
            System.out.printf("%-13s", bubbleSortTime);
        }

        System.out.printf("\n%-35s","SelectionSort,random,comparisons");
        for(int i=1; i<=situations; i++){
            selectionSort(1000*i);
            System.out.printf("%-13s", selectionComparisons);
        }
        System.out.printf("\n%-35s","SelectionSort,random,ms");
        for(int i=1; i<= situations; i++) {
            startTime = System.nanoTime();
            selectionSort(1000 * i);
            endTime = System.nanoTime();
            long selectionSortTime = (endTime - startTime) / 1000000;
            System.out.printf("%-13s", selectionSortTime);
        }

        System.out.printf("\n%-35s","QuickSort,random,comparisons");
        for(int i=1; i<=situations; i++){
            quickSort(1000*i);
            System.out.printf("%-13s", quickComparisons);
        }
        System.out.printf("\n%-35s","QuickSort,random,ms");
        for(int i=1; i<= situations; i++) {
            startTime = System.nanoTime();
            quickSortTime(1000 * i);
            endTime = System.nanoTime();
            long quickSortTime = (endTime - startTime) / 1000000;
            System.out.printf("%-13s", quickSortTime);
        }

        System.out.printf("\n%-35s","MergeSort,random,comparisons");
        for(int i=1; i<=situations; i++){
            mergeSort(1000*i);
            System.out.printf("%-13s", mergeComparisons);
        }
        System.out.printf("\n%-35s","MergeSort,random,ms");
        for(int i=1; i<= situations; i++) {
            startTime = System.nanoTime();
            mergeSort(1000 * i);
            endTime = System.nanoTime();
            long mergeSortTime = (endTime - startTime) / 1000000;
            System.out.printf("%-13s", mergeSortTime);
        }

        System.out.println("\n");
    }

    private int[] randomArr(int size){
        Random random = new Random();
        int[] arr = new int[size];
        for (int i = 0; i<arr.length; i++){
            arr[i] = random.nextInt(50);
        }
        return arr;
    }

    private void swap(int[] arr, int index1, int index2){
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }




    private void selectionSort(int size){
        selectionComparisons = 0;
        int[] selectArr;
        int min;
        selectArr = randomArr(size);
        for (int index = 0; index < selectArr.length-1; index++) {
            min = index;
            for (int scan = index + 1; scan < selectArr.length; scan++) {
                selectionComparisons++;
                if (selectArr[scan] < selectArr[min]) {
                    min = scan;
                }
            }
            swap(selectArr, min, index);
        }
    }


    private void mergeSort(int size){
        mergeComparisons=0;
        int[] mergeArr;
        mergeArr = randomArr(size);
        mergeSortHandler(mergeArr, 0, mergeArr.length-1);
    }


    private void mergeSortHandler(int[] arr, int min, int max){
        if (min < max)
        {
            int mid = (min + max) / 2;
            mergeSortHandler(arr, min, mid);
            mergeSortHandler(arr, mid+1, max);
            merge(arr, min, mid, max);
        }

    }

    private void merge(int[] arr, int first, int mid, int last){
        int[] temp = new int[arr.length];

        int first1 = first, last1 = mid;  // endpoints of first subarray
        int first2 = mid+1, last2 = last;  // endpoints of second subarray
        int index = first1;  // next index open in temp array

        //  Copy smaller item from each subarray into temp until one
        //  of the subarrays is exhausted
        while (first1 <= last1 && first2 <= last2){
            mergeComparisons++;
            mergeComparisons++;
            if (arr[first1] < arr[first2]){
                mergeComparisons++;
                temp[index] = arr[first1];
                first1++;
            } else {
                mergeComparisons++;
                temp[index] = arr[first2];
                first2++;
            }
            index++;
        }

        //  Copy remaining elements from first subarray, if any
        while (first1 <= last1){
            mergeComparisons++;
            temp[index] = arr[first1];
            first1++;
            index++;
        }

        //  Copy remaining elements from second subarray, if any
        while (first2 <= last2){
            mergeComparisons++;
            temp[index] = arr[first2];
            first2++;
            index++;
        }

        //  Copy merged data into original array
        for (index = first; index <= last; index++) {
            mergeComparisons++;
            arr[index] = temp[index];
        }
    }
}
