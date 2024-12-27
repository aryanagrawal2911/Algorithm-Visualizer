package com.example.algorithmvisualizer.controller;

import com.example.algorithmvisualizer.enums.PathFindType;
import com.example.algorithmvisualizer.model.PathFindResult;
import com.example.algorithmvisualizer.service.PathFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.example.algorithmvisualizer.enums.PathFindType.DIJKSTRA;


@Controller
@RequiredArgsConstructor
@RequestMapping
public class AlgoController {

    private final PathFindService pathFindService;
    
    @GetMapping
    public String home(){

        return "home";
    }

    @GetMapping("path-find")
    public String getPathFindSteps(@RequestParam("name") PathFindType name, Model model){
        PathFindResult result = pathFindService.getByType(name);
        model.addAttribute("result" , result);
        return "path-find";
    }

    @GetMapping("/path")
    public String getIndex(Model model) {
        PathFindResult result = pathFindService.getByType(DIJKSTRA);
        model.addAttribute("result" , result);
        return "path"; // Corresponds to home.html in src/main/resources/templates
    }

    
}