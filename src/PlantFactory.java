/**
 * Planet factory class return the specific object by getting a string message
 */

import java.awt.*;

public class PlantFactory implements AbstractSeaFactory {

    int size, x, y;
    Color col;

    public PlantFactory(int size, int x, int y) {
        this.size = size;
        this.x = x;
        this.y = y;
        this.col = Color.GREEN;
    }

    @Override
    public SeaCreature produceSeaCreature(String type) {

        if (type.equalsIgnoreCase("Laminaria"))
            return new Laminaria(size, x, y);
        if (type.equalsIgnoreCase("Zostera"))
            return new Zostera(size, x, y);
        return null;
    }
}

