package byow.Core4;

import byow.Core3.Point;
import byow.Core3.Room;

import java.util.LinkedList;

public class GraficoConnector {

    /**
     * This code was inspired by:
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

    public static int[][] connectRoomToHall(int roomNum, LinkedList<Room> cuartos, int[][] grafico) {
        for (int yxRoom1 = 0; yxRoom1 < roomNum; yxRoom1++){
            for(int yxRoom2 = 0; yxRoom2 < roomNum; yxRoom2++) {
                if (yxRoom1 != yxRoom2) {
                    Room unido1 = cuartos.get(yxRoom1);
                    Room unido2 = cuartos.get(yxRoom2);
                    grafico[yxRoom1][yxRoom2] = unido1.coneccionDeCuartos(unido2);
                }
            }
        }
        return grafico;
    }
}
