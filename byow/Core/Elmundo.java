package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.LinkedList;
import java.util.Random;

public class Elmundo {

    private boolean [][] yaFueVisitado;
    private long seed;
    private Random rd;
    private LinkedList<Cuartos> cuartosDelMundo = new LinkedList<>();

    private int ANCHO;
    private int ALTURA;

    public Elmundo(int maximoX, int maximoY, long seed) {
        this.ANCHO = maximoX;
        this.ALTURA = maximoY;
        this.seed = seed;
        this.rd = new Random(seed);
        this.yaFueVisitado = new boolean[maximoX][maximoY];
    }

    public int numeroAletorioCuartos(long seed) {
        int aletorioNumero;
        return aletorioNumero = RandomUtils.uniform(rd, 15, 25);
    }

    public void nuevoMundoNada(TETile[][] tiles) {
        for (int equis = 0; equis < ANCHO; equis++) {
            for (int ygriega = 0; ygriega < ALTURA; ygriega++) {
                tiles[equis][ygriega] = Tileset.WATER;
                yaFueVisitado[equis][ygriega] = false;
            }
        }
    }

    public TETile[][] creacionDelCuarto(int numero, int cuartoNumero,
                                        Cuartos ct, TETile[][] tiles){
        int cero = 0;
        int uno = 1;
        boolean nuevoNumero = (numero == cuartoNumero - 1);
        boolean numeroCero = (numero == 0);
        int anchoDelCuarto = ct.getAncho();
        int alturaDelCuarto = ct.getAltura();
        int equisPotencialComienzo = ct.getEquisX();
        int ygriegaPotencialComienzo = ct.getYgriegaY();
        for(int x = 0; x < anchoDelCuarto; x++) {
            for(int y = 0; y < alturaDelCuarto; y++){
                yaFueVisitado[equisPotencialComienzo + x][ygriegaPotencialComienzo + y] = true;
                int nuevoXx = equisPotencialComienzo + x;
                int nuevoyy = ygriegaPotencialComienzo + y;
                int wcondicional1 = anchoDelCuarto - 1;
                int hcondicional2 = alturaDelCuarto - 1;
                boolean cdn = (x!= cero && y != cero);
                boolean cdn2 = (x == uno && y == uno);
                if (cdn && (x != wcondicional1) && (y != hcondicional2)) {
                    tiles[nuevoXx][nuevoyy] =  Tileset.FLOOR;
                    if (cdn2 && numeroCero) {
                        tiles[nuevoXx][nuevoyy] = Tileset.SAND;
                    }
                    if (cdn2 && nuevoNumero) {
                        tiles[nuevoXx][nuevoyy] = Tileset.AVATAR;
                        //Let's use the new file for the avatar methods for this part
                        //We need to set the avatar points.
                    }
                } else {
                    tiles[nuevoXx][nuevoyy] = Tileset.WALL;
                }
            }
        }
        return tiles;
    }

    public boolean limitesYfueVisitado(Cuartos ct) {
        boolean vOrt = false;
        int anchoDelCuarto = ct.getAncho();
        int alturaDelCuarto = ct.getAltura();
        int equisPotencial = ct.getEquisX();
        int ygriegaPotencial = ct.getYgriegaY();
        int sumaDeAnchoYequis = anchoDelCuarto + equisPotencial;
        int sumaDeAlturaYgriega = alturaDelCuarto + ygriegaPotencial;
        for(int x2 = 0; x2 < sumaDeAnchoYequis; x2+=1) {
            for (int y2 = 0; y2 < sumaDeAlturaYgriega; y2+=1) {
                if (x2 >= ANCHO) {
                    vOrt = true;
                } else if (y2 >= ALTURA) {
                    vOrt = true;
                } else if (yaFueVisitado[x2][y2]) {
                    vOrt = true;
                }
            }
        }
        return vOrt;
    }

    public void dibujoCuarto(TETile[][] tiles, int cuartoNumero) {
        int contadorInicial = 0;
        boolean cuartoValido = false;
        while(contadorInicial < cuartoNumero) {
            while(!cuartoValido) {
                Cuartos nuevoCuarto = new Cuartos(ANCHO, ALTURA, contadorInicial, rd);
                if(limitesYfueVisitado(nuevoCuarto)) {
                    cuartoValido = false;
                }
                else if (!limitesYfueVisitado(nuevoCuarto)) {
                    creacionDelCuarto(contadorInicial, cuartoNumero, nuevoCuarto, tiles);
                    cuartosDelMundo.add(nuevoCuarto);
                    cuartoValido = true;
                }
            }
            contadorInicial++;
        }
    }

    private LinkedList grafiquito(int cuartoNumero) {
        int[][] grafico = new int[cuartoNumero][cuartoNumero];
        grafico = Grafico.connectarTodo(cuartoNumero, cuartosDelMundo, grafico);
        LinkedList losBordes = Grafico.primMST(cuartoNumero, grafico);
        return losBordes;
    }

    public void dibujoVestibulo(TETile[][] tiles, int cuartoNumero) {
        LinkedList losBordes2 = grafiquito(cuartoNumero);
        int contador = 0;
        while (contador < losBordes2.size()) {
            PuntosCardinales misPuntos = (PuntosCardinales) losBordes2.get(contador);
            Object identificacionCuarto1 = misPuntos.getX();
            Object identificacionCuarto2 = misPuntos.getY();
            Cuartos cuartoUno = cuartosDelMundo.get((Integer) identificacionCuarto1);
            Cuartos cuartoDos = cuartosDelMundo.get((Integer) identificacionCuarto2);
            //Here we should call the method roomConnect that uses writeEast,writeWest,
            //writeNorth,writeSouth, this will connect cuartoUno y CuartoDos
        }
    }

    public boolean fueraDeLosLimites(int equis, int ygriega) {
        boolean retornoSies = false;
        boolean xFueraDeLimites = equis > ANCHO && equis <= 0;
        boolean yFueraDeLimites = ygriega > ALTURA && ygriega <= 0;
        if (xFueraDeLimites || yFueraDeLimites){
            retornoSies = true;
        }
        return retornoSies;
    }

    public void limpiadorAlpha(int x, int y, TETile[][] rt) {
        int nuevox = x + 1;
        int nuevox2 = x - 1;
        int nuevoy = y + 1;
        int nuevoy2 = y - 1;
        boolean uno = fueraDeLosLimites(nuevox, y);
        boolean dos = fueraDeLosLimites(nuevox2, y);
        boolean tres = fueraDeLosLimites(x, nuevoy);
        boolean cuatro = fueraDeLosLimites(x, nuevoy2);
        if (!uno && rt[nuevox][y] == Tileset.WATER) {
            rt[nuevox][y] = Tileset.WALL;
        }
        if (!dos && rt[nuevox2][y] == Tileset.WATER) {
            rt[nuevox2][y] = Tileset.WALL;
        }
        if (!tres && rt[x][nuevoy] == Tileset.WATER) {
            rt[x][nuevoy] = Tileset.WALL;
        }
        if (!cuatro && rt[x][nuevoy2] == Tileset.WATER) {
            rt[x][nuevoy2] = Tileset.WALL;
        }
    }

    public void limpiadorBetha(int x, int y, TETile[][] rt) {
        int nuevox = x + 1;
        int nuevox2 = x - 1;
        int nuevoy = y + 1;
        int nuevoy2 = y - 1;
        boolean uno = fueraDeLosLimites(nuevox2, nuevoy);
        boolean dos = fueraDeLosLimites(nuevox, nuevoy);
        boolean tres = fueraDeLosLimites(nuevox, nuevoy2);
        boolean cuatro = fueraDeLosLimites(nuevox2, nuevoy2);
        if(!uno && rt[nuevox2][nuevoy] == Tileset.WATER) {
            rt[nuevox2][nuevoy] = Tileset.WALL;
        }
        if(!dos && rt[nuevox][nuevoy] == Tileset.WATER) {
            rt[nuevox][nuevoy] = Tileset.WALL;
        }
        if(!tres && rt[nuevox][nuevoy2] == Tileset.WATER) {
            rt[nuevox][nuevoy2] = Tileset.WALL;
        }
        if(!cuatro && rt[nuevox2][nuevoy2] == Tileset.WATER) {
            rt[nuevox2][nuevoy2] = Tileset.WALL;
        }
    }

    public void limpiador(TETile[][] aleatorioTiles) {
        for (int x = 0; x < ANCHO; x+= 1) {
            for (int y = 0; y < ALTURA; y+= 1) {
                if (aleatorioTiles[x][y] == Tileset.FLOOR) {
                    limpiadorAlpha(x, y, aleatorioTiles);
                    limpiadorBetha(x, y, aleatorioTiles);
                }
            }
        }
    }
}