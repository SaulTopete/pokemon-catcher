package byow.ClasesViejos;

import java.util.ArrayList;
import java.util.Random;

public class Dirreccion2D {

    //video says static, but don't know what static does
    public static ArrayList<PuntosCardinales> directionList = new ArrayList<>();

    public Dirreccion2D() {
        directionList.add(new PuntosCardinales<>(0,1));
        directionList.add(new PuntosCardinales<>(1,0));
        directionList.add(new PuntosCardinales<>(0,-1));
        directionList.add(new PuntosCardinales<>(-1, 0));
    }

    public static PuntosCardinales direccionAlazar() {
        Random random = new Random(4);
        return directionList.get(random.nextInt());
    }
}
