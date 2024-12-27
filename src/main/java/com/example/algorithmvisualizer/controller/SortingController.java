package com.example.algorithmvisualizer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.algorithmvisualizer.service.SortingService;

@Controller
public class SortingController {

    private final SortingService sortingService;

    public SortingController(SortingService sortingService) {
        this.sortingService = sortingService;
    }

    @GetMapping("/sorting")
    public String showSortingPage(Model model, @RequestParam(defaultValue = "insertion") String algorithm) {
        model.addAttribute("algorithm", algorithm);
        model.addAttribute("array", sortingService.getArray(50)); // Customize the array size
        return "sorting"; // The Thymeleaf template
    }

    @GetMapping("/sortingSteps")
    @ResponseBody
    public List<List<Integer>> getSortingSteps(@RequestParam String algorithm) {
        List<Integer> array = sortingService.getArray(50); // Create an array for sorting
        List<List<Integer>> steps = new ArrayList<>();
        
        switch (algorithm) {
            case "bubble":
                steps = sortingService.bubbleSort(array);
                break;
            case "insertion":
                steps = sortingService.insertionSort(array);
                break;
            case "merge":
                steps = sortingService.mergeSort(array);
                break;
            default:
                return steps; // Return empty list if algorithm is unknown
        }
        
        return steps;
    }
}
