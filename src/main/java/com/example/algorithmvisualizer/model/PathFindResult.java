package com.example.algorithmvisualizer.model;

import com.example.algorithmvisualizer.enums.PathFindType;

import java.util.List;

public record PathFindResult(PathFindType type, List<int[]> moves, List<int[]> shortestPath, int[][] matrix) {
}