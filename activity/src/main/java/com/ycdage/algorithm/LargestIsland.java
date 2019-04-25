package com.ycdage.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 在二维地图上， 0代表海洋， 1代表陆地，我们最多只能将一格 0 海洋变成 1变成陆地。
 * 进行填海之后，地图上最大的岛屿面积是多少？（上、下、左、右四个方向相连的 1 可形成岛屿）
 */
public class LargestIsland {
    private static int island = 0x10;
    private static HashMap<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) {
//        int[][] grid = {{0, 1, 0, 1, 1, 1}, {1, 1, 0, 1, 1, 1}};
        int[][] grid = {{1, 1}, {1, 1}};
        int i = largestIsland(grid);
        System.out.println(i);
    }

    /**
     * 遍历所有节点，将所有相连的陆地置为同一个值，而后只要找到海洋节点的四个方向
     * 所连接不同陆地节点的大小
     * @param grid
     * @return
     */
    private static int largestIsland(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    island = island + 1;
                    grid[i][j] = island;
                    map.put(island, 1);
                    findAllIsLand(grid, i, j);
                }
            }
        }
        int lastResult = 1;
        boolean isAllLand = true;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    isAllLand = false;
                    int posAllIsland = findDirection(grid, i, j);
                    if (posAllIsland > lastResult) {
                        lastResult = posAllIsland;
                    }
                }
            }
        }
        if(isAllLand){
            lastResult = grid.length * grid[0].length;
        }
        return lastResult;
    }

    private static int findDirection(int[][] grid, int x, int y) {
        List<Integer> list = new ArrayList<>();
        int findAllIslandCount = 1;
        //上
        if (x - 1 >= 0) {
            if (grid[x - 1][y] > 0x10) {
                list.add(grid[x - 1][y]);
                findAllIslandCount = findAllIslandCount + map.get(grid[x - 1][y]);
            }
        }
        //下
        if (x + 1 < grid.length) {
            if (grid[x + 1][y] > 0x10) {
                if (!list.contains(grid[x + 1][y])) {
                    list.add(grid[x + 1][y]);
                    findAllIslandCount = findAllIslandCount + map.get(grid[x + 1][y]);
                }
            }
        }
        //左
        if (y - 1 >= 0) {
            if (grid[x][y - 1] > 0x10) {
                if (!list.contains(grid[x][y - 1])) {
                    list.add(grid[x][y - 1]);
                    findAllIslandCount = findAllIslandCount + map.get(grid[x][y - 1]);
                }
            }
        }
        //右
        if (y + 1 < grid[x].length) {
            if (grid[x][y + 1] > 0x10) {
                if (!list.contains(grid[x][y + 1])) {
                    list.add(grid[x][y + 1]);
                    findAllIslandCount = findAllIslandCount + map.get(grid[x][y + 1]);
                }
            }
        }
        return findAllIslandCount;
    }

    private static void findAllIsLand(int[][] grid, int x, int y) {
        //上
        if (x - 1 >= 0) {
            if (grid[x - 1][y] == 1) {
                grid[x - 1][y] = island;
                map.put(island, map.get(island) + 1);
                findAllIsLand(grid, x - 1, y);
            }
        }
        //下
        if (x + 1 < grid.length) {
            if (grid[x + 1][y] == 1) {
                grid[x + 1][y] = island;
                map.put(island, map.get(island) + 1);
                findAllIsLand(grid, x + 1, y);
            }
        }
        //左
        if (y - 1 >= 0) {
            if (grid[x][y - 1] == 1) {
                grid[x][y - 1] = island;
                map.put(island, map.get(island) + 1);
                findAllIsLand(grid, x, y - 1);
            }
        }
        //右
        if (y + 1 < grid[x].length) {
            if (grid[x][y + 1] == 1) {
                grid[x][y + 1] = island;
                map.put(island, map.get(island) + 1);
                findAllIsLand(grid, x, y + 1);
            }
        }

    }
}
