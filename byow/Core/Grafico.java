//package byow.Core;
//
//
//import java.util.ArrayList;
//
//public class Grafico {
//
//    /**
//     *
//     * this whole file has been inspired by:
//     * @Source https://www.geeksforgeeks.org/prims-minimum-spanning-tree-mst-greedy-algo-5/
//     */
//
//    /*
//    Change how for loop works, maybe use while loop instead.
//     */
//    public static int[][] connectarTodo(int numDeCuartos, ArrayList<Cuartos> cuartos, int[][] grafico) {
//        for (int x = 0; x < numDeCuartos; x++) {
//            for (int y = 0; y < numDeCuartos; y++) {
//                if (x != y) {
//                    grafico[x][y] = cuartos.get(x).coneccionDeCuartos(cuartos.get(y));
//                }
//            }
//        }
//        return grafico;
//    }
//
//    public static ArrayList primMST(int numDeCuartos, int[][] grafico) {
//        Boolean[] MST = new Boolean[numDeCuartos];
//        int[] peso = new int[numDeCuartos];
//        int[] origin = new int[numDeCuartos];
//
//        int posEquis = 0;
//        while (posEquis < numDeCuartos) {
//            peso[posEquis] = Integer.MAX_VALUE;
//            MST[posEquis] = false;
//            posEquis += 1;
//        }
//        peso[0] = 0;
//        origin[0] = -1;
//        int nuevoPos = 0;
//        int nuevoPos2;
//        posEquis = 0;
//        while (nuevoPos < numDeCuartos - 1) {
//            nuevoPos2 = obtenerAcercaNumDeCuarto(peso, MST, numDeCuartos);
//            MST[nuevoPos2] = true;
//            while (posEquis < numDeCuartos) {
//                if (grafico[nuevoPos2][posEquis] != 0 && !MST[posEquis] && grafico[nuevoPos2][posEquis] < peso[posEquis]) {
//                    origin[posEquis] = nuevoPos2;
//                    peso[posEquis] = grafico[nuevoPos2][posEquis];
//                }
//                posEquis += 1;
//            }
//            nuevoPos += 1;
//        }
//        return obtenerBorde(origin, numDeCuartos);
//    }
//
//    public static ArrayList<PuntosCardinales> obtenerBorde(int[] origin, int numDeCuartos) {
//        ArrayList<PuntosCardinales> borderPuntos = new ArrayList<>();
//        int i = 0;
//        while (i < numDeCuartos) {
//            PuntosCardinales borde = new PuntosCardinales(origin[i], i);
//            borderPuntos.add(borde);
//            i += 1;
//        }
//        return borderPuntos;
//    }
//
//    public static int obtenerAcercaNumDeCuarto(int[] peso, Boolean[] MST, int numDeCuarto) {
//        int minIndexo = -1;
//        int min = Integer.MAX_VALUE;
//
//        int i = 0;
//        while (i < numDeCuarto) {
//            if (!MST[i] && peso[i] < min) {
//                min = peso[i];
//                minIndexo = i;
//            }
//            i += 1;
//        }
//        return minIndexo;
//    }
//}
