package byow.Core3;

import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {
    public static int[][] connectAll(int roomNum, LinkedList<Room> rooms, int[][] graph) {
        for (int i = 0; i < roomNum; i++) {
            for (int j = 0; j < roomNum; j++) {
                if (i != j) {
                    Room unido1 = rooms.get(i);
                    Room unido2 = rooms.get(j);
                    graph[i][j] = unido1.coneccionDeCuartos(unido2);
                }
            }
        }
        return graph;
    }

    /**
     * Inspired by:
     * @Source: https://www.geeksforgeeks.org/prims-minimum-spanning-tree-mst-greedy-algo-5/
     */

    public static LinkedList primMST(int numRooms, int[][] graph) {
        int[] origin = new int[numRooms];
        int[] weights = new int[numRooms];
        Boolean[] mst = new Boolean[numRooms];

        for (int i = 0; i < numRooms; i++) {
            weights[i] = Integer.MAX_VALUE;
            mst[i] = false;
        }
        weights[0] = 0;
        origin[0] = -1;
        for (int count = 0; count < numRooms - 1; count++) {
            int u = closestRoomNum(weights, mst, numRooms);
            mst[u] = true;
            for (int v = 0; v < numRooms; v++) {
                if (graph[u][v] != 0 && !mst[v] && graph[u][v] < weights[v]) {
                    origin[v] = u;
                    weights[v] = graph[u][v];
                }
            }
        }
        return getEdgy(origin, numRooms);
    }

    /**
     * Inspired by
     * @Source: https://www.geeksforgeeks.org/prims-minimum-spanning-tree-mst-greedy-algo-5/
     */

    public static LinkedList<Point> getEdgy(int[] origin, int numRooms) {

        LinkedList<Point> edgePoints = new LinkedList<>();
        for (int i = 1; i < numRooms; i++) {
            Point edge = new Point(origin[i], i);
            edgePoints.add(edge);
        }
        return edgePoints;
    }

    /**
     * Inspired by:
     * @Source: https://www.geeksforgeeks.org/prims-minimum-spanning-tree-mst-greedy-algo-5/
     */

    public static int closestRoomNum(int[] weights, Boolean[] mst, int roomNum) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < roomNum; v++) {
            if (!mst[v] && weights[v] < min) {
                min = weights[v];
                minIndex = v;
            }
        }

        return minIndex;

    }

}


