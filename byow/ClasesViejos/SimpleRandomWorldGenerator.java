package byow.ClasesViejos;

import java.util.HashSet;

public class SimpleRandomWorldGenerator {
    protected PuntosCardinales startPos = new PuntosCardinales<>(0,0);

    private int iteration = 10;
    public int walkLength = 10;
    public boolean startRandomlyEachIteration = true;

    public void RunProceduralGeneration() {
        HashSet<PuntosCardinales> floorPositions = RunRandomWalk();
    }

    protected HashSet<PuntosCardinales> RunRandomWalk() {
        var currentPos = startPos;
        HashSet<PuntosCardinales> floorPos = new HashSet<>();
        for (int i = 0; i < iteration; i++) {
            var path = MundoGenerador.caminataAlazar(currentPos, walkLength);
            floorPos.addAll(path);
            if (startRandomlyEachIteration) {
                currentPos = floorPos.
            }
        }
    }
}
