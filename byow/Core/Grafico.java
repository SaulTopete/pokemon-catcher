package byow.Core;

import java.util.ArrayList;
import java.util.LinkedList;

public class Grafico {
    /*
    Change how for loop works, maybe use while loop instead.
     */
    public static int[][] connectarTodo(int numDeCuartos, LinkedList<Cuartos> cuartos, int[][] grafico) {
        for (int i = 0; i < numDeCuartos; i++) {
            for (int j = 0; j < numDeCuartos; j++) {
                if (i != j) {
                    grafico[i][j] = cuartos.get(i).coneccionDeCuartos(cuartos.get(j));
                }
            }
        }
        return grafico;
    }

    public static LinkedList primMST(int numDeCuartos, int[][] grafico) {
        Boolean[] MST = new Boolean[numDeCuartos];
        int[] peso = new int[numDeCuartos];
        int[] origin = new int[numDeCuartos];

        int posEquis = 0;
        while (posEquis < numDeCuartos) {
            peso[posEquis] = Integer.MAX_VALUE;
            MST[posEquis] = false;
            posEquis += 1;
        }
        peso[0] = 0;
        origin[0] = -1;
        int nuevoPos = 0;
        int nuevoPos2;
        posEquis = 0;
        while (nuevoPos < numDeCuartos - 1) {
            nuevoPos2 = obtenerAcercaNumDeCuarto(peso, MST, numDeCuartos);
            MST[nuevoPos2] = true;
            while (posEquis < numDeCuartos) {
                if (grafico[nuevoPos2][posEquis] != 0 && !MST[posEquis] && grafico[nuevoPos2][posEquis] < peso[posEquis]) {
                    origin[posEquis] = nuevoPos2;
                    peso[posEquis] = grafico[nuevoPos2][posEquis];
                }
                posEquis += 1;
            }
            nuevoPos += 1;
        }
        return obtenerBorde(origin, numDeCuartos);
    }

    public static LinkedList obtenerBorde(int[] origin, int numDeCuartos) {
        LinkedList<PuntosCardinales> borderPuntos = new LinkedList<>();
        int i = 0;
        while (i < numDeCuartos) {
            PuntosCardinales borde = new PuntosCardinales(origin[i], i);
            borderPuntos.add(borde);
            i += 1;
        }
        return borderPuntos;
    }

    public static int obtenerAcercaNumDeCuarto(int[] peso, Boolean[] MST, int numDeCuarto) {
        int minIndexo = -1;
        int min = Integer.MAX_VALUE;

        int i = 0;
        while (i < numDeCuarto) {
            if (!MST[i] && peso[i] < min) {
                min = peso[i];
                minIndexo = i;
            }
            i += 1;
        }
        return minIndexo;
    }
}
