package com.example.algorithmvisualizer.algorithms;

import com.example.algorithmvisualizer.model.PathFindResult;

public interface PathFind {

    final int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    PathFindResult find(int[][] matrix);
}