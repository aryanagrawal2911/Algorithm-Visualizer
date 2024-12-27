package com.example.algorithmvisualizer.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
// import java.util.Collections;
import java.util.List;

@Service
public class SortingService {

    // Generate a random array of integers
    public List<Integer> getArray(int size) {
        List<Integer> array = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            array.add((int) (Math.random() * 100)); // Random values between 0 and 100
        }
        return array;
    }

    // Bubble Sort Algorithm with steps
    public List<List<Integer>> bubbleSort(List<Integer> array) {
        List<List<Integer>> steps = new ArrayList<>();
        int n = array.size();
        // Push initial state
        steps.add(new ArrayList<>(array));
    
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array.get(j) > array.get(j + 1)) {
                    // Swap
                    int temp = array.get(j);
                    array.set(j, array.get(j + 1));
                    array.set(j + 1, temp);
    
                    // Add the current state to the steps
                    steps.add(new ArrayList<>(array));
                }
            }
        }
        return steps;
    }
    
    // Insertion Sort Algorithm with steps
    public List<List<Integer>> insertionSort(List<Integer> array) {
        List<List<Integer>> steps = new ArrayList<>();
        // Push initial state
        steps.add(new ArrayList<>(array));
    
        for (int j = 1; j < array.size(); j++) {
            int key = array.get(j);
            int i = j - 1;
            while (i >= 0 && array.get(i) > key) {
                array.set(i + 1, array.get(i));
                i--;
                // Add the current state to the steps after each swap
                steps.add(new ArrayList<>(array));
            }
            array.set(i + 1, key);
            // Add the current state after placing the key
            steps.add(new ArrayList<>(array));
        }
        return steps;
    }
    

    // Merge Sort Algorithm with steps
    public List<List<Integer>> mergeSort(List<Integer> array) {
        List<List<Integer>> steps = new ArrayList<>();
        // Call the recursive mergeSort
        recursiveMergeSort(array, 0, array.size() - 1, steps);
        return steps;
    }
    
    private void recursiveMergeSort(List<Integer> array, int low, int high, List<List<Integer>> steps) {
        if (low < high) {
            int mid = (low + high) / 2;
            recursiveMergeSort(array, low, mid, steps);
            recursiveMergeSort(array, mid + 1, high, steps);
            merge(array, low, mid, high, steps);
        }
    }
    
    private void merge(List<Integer> array, int low, int mid, int high, List<List<Integer>> steps) {
        int n1 = mid - low + 1;
        int n2 = high - mid;
        List<Integer> left = new ArrayList<>(n1);
        List<Integer> right = new ArrayList<>(n2);
    
        for (int i = 0; i < n1; i++) {
            left.add(array.get(low + i));
        }
        for (int i = 0; i < n2; i++) {
            right.add(array.get(mid + 1 + i));
        }
    
        int i = 0, j = 0, k = low;
        while (i < n1 && j < n2) {
            if (left.get(i) <= right.get(j)) {
                array.set(k, left.get(i));
                i++;
            } else {
                array.set(k, right.get(j));
                j++;
            }
            k++;
            // Add the current state of the array after each merge step
            steps.add(new ArrayList<>(array));
        }
    
        while (i < n1) {
            array.set(k, left.get(i));
            i++;
            k++;
            steps.add(new ArrayList<>(array));
        }
    
        while (j < n2) {
            array.set(k, right.get(j));
            j++;
            k++;
            steps.add(new ArrayList<>(array));
        }
    }
    


}
