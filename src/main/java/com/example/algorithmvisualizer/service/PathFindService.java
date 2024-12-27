package com.example.algorithmvisualizer.service;

import com.example.algorithmvisualizer.algorithms.PathFind;
import com.example.algorithmvisualizer.algorithms.implementation.AStar;
import com.example.algorithmvisualizer.algorithms.implementation.Bfs;
import com.example.algorithmvisualizer.algorithms.implementation.Dfs;
import com.example.algorithmvisualizer.algorithms.implementation.Dijkstra;
import com.example.algorithmvisualizer.enums.PathFindType;
import com.example.algorithmvisualizer.model.PathFindResult;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.example.algorithmvisualizer.enums.PathFindType.*;


@Service
public class PathFindService {

    private Random random;
    private Map<PathFindType, PathFind> pathFindMap;
    private final int ROW = 40;
    private final int COL = 40;

    @PostConstruct
    public void init(){
        random = new Random();
        pathFindMap = new HashMap<>();
        pathFindMap.put(BFS, new Bfs());
        pathFindMap.put(A_STAR, new AStar());
        pathFindMap.put(DFS, new Dfs());
        pathFindMap.put(DIJKSTRA, new Dijkstra());
    }

    public PathFindResult getByType(PathFindType type) {
        int[][] matrix = generateMatrix();
        return pathFindMap.get(type).find(matrix);
    }

    private int[][] generateMatrix(){
        int[][] matrix = new int[ROW][COL];
        matrix[ROW-1][COL-1] = 2;
        int x = ROW * COL / 4;
        for(int  i = 0; i < x; i++){
            int r = random.nextInt(ROW);
            int c = random.nextInt(COL);
            matrix[r][c] = 1;
        }
        matrix[0][0] = 0;
        matrix[ROW-1][COL-1] = 2;
        return matrix;
    }
}