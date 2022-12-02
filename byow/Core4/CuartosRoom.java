//package byow.Core4;
//
////import byow.Core3.Room;
//
//import java.util.Random;
//
//public class CuartosRoom {
//
//    private final int Width;
//    private final int Height;
//    private final int Number;
//    private final PuntosCardinales<Integer, Integer> PointOfStart;
//
//
//    public CuartosRoom(Random rd, int mX, int mY, int numero){
//        int EquisPotencial = RandomUtils.uniform(rd, 0, mX);
//        int YgriegaPotencial = RandomUtils.uniform(rd, 0, mY);
//
//        this.PointOfStart = new PuntosCardinales<>(EquisPotencial, YgriegaPotencial);
//        this.Width = RandomUtils.uniform(rd, 8, 12);
//        this.Height = RandomUtils.uniform(rd, 8, 12);
//        this.Number = numero;
//    }
//
//    public int getAncho(){
//        return Width;
//    }
//
//    public int getAltura(){
//        return Height;
//    }
//
//    public int getEquisX() {
//        return PointOfStart.getX();
//    }
//
//    public int getYgriegaY() {
//        return PointOfStart.getY();
//    }
//
//    public int getMidX() {
//        int startX = getEquisX();
//        int mitadX = startX + Width / 2;
//        return mitadX;
//    }
//
//    public int getMidY() {
//        int startY = getYgriegaY();
//        int mitadY = startY + Height / 2;
//        return mitadY;
//    }
//
//    public int formulaDistancia(int x, int x1, int y, int y1) {
//        int distancia = (int) Math.sqrt((y1 - y) *  (y1 * y) + (x1 - x) * (x1 - x));
//        return distancia;
//    }
//
//}
