package byow.Core;

import java.awt.*;
import java.nio.channels.Pipe;
import java.util.ArrayList;

public class Grafico {
    public static int[][] connectAll(int cuartoNumero, ArrayList<Cuartos> cuarto, int[][] grafico) {
        for (int i = 0; i < cuartoNumero; i++) {
            for (int j = 0; j < cuartoNumero; j++) {
                if (i != j) {
                    grafico[i][j] = cuarto.get(i).connectRoom(cuarto.get(j));
                }
            }
        }
        return grafico;
    }

    public static ArrayList primMST(int numRooms, int[][] grafico) {
        int origin[] = new int[numRooms];
        int weights[] = new int[numRooms];

        Boolean[] mst = new Boolean[numRooms];

        for (int i = 0; i < numRooms; i++) {
            weights[i] = Integer.MAX_VALUE;
            mst[i] = false;
        }
        weights[0] = 0;
        origin[0] = -1;
        for (int i = 0; i < numRooms; i++) {
            int u = closestRoomNum(weights, mst, numRooms);
            mst[u] = true;
            for (int j = 0; j < numRooms; j++) {
                if (grafico[i][j] != 0 && !mst[j] && grafico[i][j] < weights[j]) {
                    origin[j] = u;
                    weights[j] = grafico[i][j];
                }
            }
        }
        return getEdge(origin, numRooms);
    }

    /*
    @Source:
     */

    public static ArrayList<Point> getEdge(int[] origin, int numRooms) {
        ArrayList<Point> edgePoints = new ArrayList<>();
        for (int i = 0; i < numRooms; i++) {
            Point edge = new Point(origin[i], i);
            edgePoints.add(edge);
        }
        return edgePoints;
    }

    public static int closestRoomNum(int[] weights, Boolean[] mst, int roomNum) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < roomNum; i++) {
            if (!mst[i] && weights[i] < min) {
                min = weights[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
}
