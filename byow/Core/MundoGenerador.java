package byow.Core;

import java.lang.invoke.DirectMethodHandle$Holder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

//was static in video, maybe make class static?
public class MundoGenerador {

    public static HashSet<PuntosCardinales> caminataAlazar(PuntosCardinales start, int walkLength)
    {
        HashSet<PuntosCardinales> path = new HashSet<>();

        path.add(start);
        var prevPos = start;

        for (int i = 0; i < walkLength; i++)
        {
            PuntosCardinales randomNum = Dirreccion2D.direccionAlazar();
            var newPosX = prevPos.getX() + randomNum.getX();
            var newPosY = prevPos.getY() + randomNum.getY();

            PuntosCardinales nuevoPos = new PuntosCardinales(newPosX, newPosY);
            path.add(nuevoPos);
            prevPos = nuevoPos;
        }
        return path;
    }

}
