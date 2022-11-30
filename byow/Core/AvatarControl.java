package byow.Core;

import byow.Core2.World;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;



import java.util.LinkedList;

//public class AvatarControl {
//
//
//    public boolean fueraDeLosLimites2 (int x, int y) {
//        boolean retornoSies = false;
//        boolean xFueraDeLimites = x > ANCHO && x <= 0;
//        boolean yFueraDeLimites = y > ALTURA && y <= 0;
//        if (xFueraDeLimites || yFueraDeLimites){
//            retornoSies = true;
//        }
//        return retornoSies;
//    }
//
//    public  void movimientoArriba(int xPrincipal, int yPrincipal,
//                                        TETile[][] tiles, TETile[][] tilesOscuros) {
//        int yy = yPrincipal + 1;
//        boolean requerimiento = !fueraDeLosLimites2(xPrincipal, yy);
//        boolean requerimiento2 = tiles[xPrincipal][yy].description().equals("floor");
//        boolean requerimiento3 = tiles[xPrincipal][yy].description().equals("gold");
//        if (requerimiento) {
//            if (requerimiento2) {
//                tilesOscuros[xPrincipal][yPrincipal] = Tileset.NOTHING;
//                tilesOscuros[xPrincipal][yy] = Tileset.AVATAR;
//                tiles[xPrincipal][yPrincipal] = Tileset.FLOOR;
//                tiles[xPrincipal][yy] = Tileset.AVATAR;
//                puntosCAvatar.setY(yy);
//                movimientosDelAvatar.add("W");
//            }
//            if (requerimiento3) {
//                movimientosDelAvatar.add("W");
//            }
//        }
//    }
//
//    public  void movimientoAbajo(int xPrincipal, int yPrincipal,
//                                       TETile[][] tiles, TETile[][] tilesOscuros) {
//        int yy = yPrincipal - 1;
//        int yy2 = yPrincipal + 1;
//        boolean requerimiento = !fueraDeLosLimites2(xPrincipal, yy);
//        boolean requerimiento2 = tiles[xPrincipal][yy].description().equals("floor");
//        boolean requerimiento3 = tiles[xPrincipal][yy2].description().equals("gold");
//        if(requerimiento){
//            if(requerimiento2) {
//                tilesOscuros[xPrincipal][yPrincipal] = Tileset.NOTHING;
//                tilesOscuros[xPrincipal][yy] = Tileset.AVATAR;
//                tiles[xPrincipal][yPrincipal] = Tileset.FLOOR;
//                tiles[xPrincipal][yy] = Tileset.AVATAR;
//                puntosCAvatar.setY(yy);
//                movimientosDelAvatar.add("S");
//            }
//            if(requerimiento3) {
//                movimientosDelAvatar.add("S");
//            }
//        }
//    }
//
//    public  void movimientoDerecha(int xPrincipal, int yPrincipal,
//                                         TETile[][] tiles, TETile[][] tilesOscuros) {
//        int xx = xPrincipal + 1;
//        boolean requerimiento = !fueraDeLosLimites2(xx, yPrincipal);
//        boolean requerimiento2 = tiles[xx][yPrincipal].description().equals("floor");
//        boolean requerimiento3 = tiles[xx][yPrincipal].description().equals("gold");
//        if(requerimiento){
//            if(requerimiento2){
//                tiles[xPrincipal][yPrincipal] = Tileset.FLOOR;
//                tiles[xx][yPrincipal] = Tileset.AVATAR;
//                tilesOscuros[xPrincipal][yPrincipal] = Tileset.NOTHING;
//                tilesOscuros[xx][yPrincipal] = Tileset.AVATAR;
//                puntosCAvatar.setX(xx);
//                movimientosDelAvatar.add("D");
//            }
//            if(requerimiento3) {
//                movimientosDelAvatar.add("D");
//            }
//        }
//    }
//
//    public  void movimientoIzquierda(int xPrincipal, int yPrincipal,
//                                           TETile[][] tiles, TETile[][] tilesOscuros) {
//        int xx = xPrincipal - 1;
//        boolean requerimiento = !fueraDeLosLimites2(xx, yPrincipal);
//        boolean requerimiento2 = tiles[xx][yPrincipal].description().equals("floor");
//        boolean requerimiento3 = tiles[xx][yPrincipal].description().equals("gold");
//        if(requerimiento){
//            if(requerimiento2){
//                tiles[xPrincipal][yPrincipal] = Tileset.FLOOR;
//                tiles[xx][yPrincipal] = Tileset.AVATAR;
//                tilesOscuros[xPrincipal][yPrincipal] = Tileset.NOTHING;
//                tilesOscuros[xx][yPrincipal] = Tileset.AVATAR;
//                puntosCAvatar.setX(xx);
//                movimientosDelAvatar.add("A");
//            }
//            if(requerimiento3) {
//                movimientosDelAvatar.add("A");
//            }
//        }
//    }
//
//    public LinkedList<String> obtenerLosMovimientos() {
//        return movimientosDelAvatar;
//    }
//
//    public void arribaOabajo (TETile[][] tiles, TETile[][] tilesOscuros, char teclado) {
//        boolean tecladoW = teclado == 'W' || teclado == 'w';
//        boolean tecladoS = teclado == 'S' || teclado == 's';
//        if(tecladoW) {
//            movimientoArriba(puntosCAvatar.getX(), puntosCAvatar.getY(), tiles, tilesOscuros);
//        } else
//            if(tecladoS) {
//            movimientoAbajo(puntosCAvatar.getX(), puntosCAvatar.getY(), tiles, tilesOscuros);
//        }
//    }
//
//    public void derechaOizquierda(TETile[][] tiles, TETile[][] tilesOscuros, char teclado) {
//        boolean tecladoA = teclado == 'A' || teclado == 'a';
//        boolean tecladoD = teclado == 'D' || teclado == 'd';
//        if(tecladoA) {
//            movimientoIzquierda(puntosCAvatar.getX(), puntosCAvatar.getY(), tiles, tilesOscuros);
//        } else
//            if (tecladoD) {
//                movimientoDerecha(puntosCAvatar.getX(), puntosCAvatar.getY(), tiles, tilesOscuros);
//            }
//    }
//
//    public void controlarAlAvatar(TETile[][] tiles, TETile[][] tilesOscuros, char teclado){
//        boolean tecladoWS = (teclado == 'W' || teclado == 'w') || (teclado == 'S' || teclado == 's');
//        boolean tecladoDA = (teclado == 'A' || teclado == 'a') || (teclado == 'D' || teclado == 'd');
//
//        if(tecladoWS) {
//            arribaOabajo(tiles, tilesOscuros, teclado);
//            return;
//        }
//        if(tecladoDA) {
//            derechaOizquierda(tiles, tilesOscuros, teclado);
//            return;
//        }
//        if(teclado == ' ') {return;}
//    }
//
//    public void establecerAvatarPuntos(TETile[][] mundofinal, PuntosCardinales equis) {
//        Integer cx = puntosCAvatar.getX();
//        Integer cy = puntosCAvatar.getY();
//        mundofinal[cx][cy] = Tileset.FLOOR;
//        puntosCAvatar = equis;
//        mundofinal[cx][cy] = Tileset.AVATAR;
//    }
//
//    public void ponerOscuroTodoAlpha(TETile[][] brillanteTile, TETile[][] oscuroTile,
//                                     int equis, int ygriega, int equis2, int ygriega2) {
//
//        boolean requerimiento1 = !fueraDeLosLimites2(equis + equis2, ygriega);
//        boolean requerimiento2 = !fueraDeLosLimites2(equis - equis2, ygriega);
//        boolean requerimiento3 = !fueraDeLosLimites2(equis, ygriega + ygriega2);
//        boolean requerimiento4 = !fueraDeLosLimites2(equis, ygriega - ygriega2);
//        boolean requerimiento5 = !fueraDeLosLimites2(equis + equis2, ygriega + ygriega2);
//        boolean requerimiento6 = !fueraDeLosLimites2(equis - equis2, ygriega - ygriega2);
//        boolean requerimiento7 = !fueraDeLosLimites2(equis + equis2, ygriega - ygriega2);
//        boolean requerimiento8 = !fueraDeLosLimites2(equis - equis2, ygriega + ygriega2);
//        if (requerimiento1) {
//            oscuroTile[equis + equis2][ygriega] = brillanteTile[equis + equis2][ygriega];
//        }
//        if (requerimiento2) {
//            oscuroTile[equis - equis2][ygriega] = brillanteTile[equis - equis2][ygriega];
//        }
//        if (requerimiento3) {
//            oscuroTile[equis][ygriega + ygriega2] = brillanteTile[equis][ygriega + ygriega2];
//        }
//        if (requerimiento4) {
//            oscuroTile[equis][ygriega - ygriega2] = brillanteTile[equis][ygriega - ygriega2];
//        }
//        if (requerimiento5) {
//            oscuroTile[equis + equis2][ygriega + ygriega2] = brillanteTile[equis + equis2][ygriega + ygriega2];
//        }
//        if (requerimiento6) {
//            oscuroTile[equis - equis2][ygriega - ygriega2] = brillanteTile[equis - equis2][ygriega - ygriega2];
//        }
//        if (requerimiento7) {
//            oscuroTile[equis + equis2][ygriega - ygriega2] = brillanteTile[equis + equis2][ygriega - ygriega2];
//        }
//        if (requerimiento8) {
//            oscuroTile[equis - equis2][ygriega + ygriega2] = brillanteTile[equis - equis2][ygriega + ygriega2];
//        }
//    }
//
//    public void ponerOscuroTodo(TETile[][] brillanteTile, TETile[][] oscuroTile) {
//
//        for (int a = 0; a < ANCHO; a += 1) {
//            for (int b = 0; b < ALTURA; b += 1) {
//
//                // Make sure gold disappears when avatar reaches it
//                if (oscuroTile[a][b] != Tileset.AVATAR) {
//                    oscuroTile[a][b] = Tileset.NOTHING;
//                }
//            }
//
//
//            for (int x = 0; x < ANCHO; x += 1) {
//                for (int y = 0; y < ALTURA; y += 1) {
//                    // Make sure gold disappears when avatar reaches it
//                /*if (lightTiles[x][y] == Tileset.GOLD) {
//                    darkTiles[x][y] = Tileset.GOLD;
//                }*/
//                    if (oscuroTile[x][y] == Tileset.AVATAR) {
//                        for (int c = 0; c < luzDistancia; c++) {
//                            for (int d = 0; d < luzDistancia; d++) {
//                                ponerOscuroTodoAlpha(brillanteTile,oscuroTile,x,y,c,d);
//                            }
//                        }
//                    }
//                }
//            }
//
//        }
//    }
//}
