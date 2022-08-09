/**
 * MarineAnimalDecorator class implements MarineAnimal interface and  change the color of animals
 */

import java.awt.*;

public class MarineAnimalDecorator implements MarineAnimal{

    private MarineAnimal swimmable;

    public MarineAnimalDecorator(MarineAnimal swimmable){
        this.swimmable=swimmable;
    }

    @Override
    public void PaintFish(Color col) {
        swimmable.PaintFish(col);
    }
}
