/**
 * Laminaria class inherite from Immobile and implements all is methods
 */

import java.awt.*;

public class Laminaria extends Immobile {

    public Laminaria(int size, int x, int y) {

        super(size, x, y);

    }

    @Override
    public String getPlanetName() {
       return this.getClass().getSimpleName();
    }


    @Override
    public void setState(int size, int x_front, int y_front) {
        this.size=size;
        this.x=x_front;
        this.y=y_front;

    }

    @Override
    public void drawCreatrue(Graphics g) {
        g.setColor(colorr);
        g.fillArc(x-size/20, y-size, size/10, size*4/5,0, 360);
        g.fillArc(x-size*3/20, y-size*13/15, size/10, size*2/3,0, 360);
        g.fillArc(x+size/20,y-size*13/15,size/10, size*2/3, 0, 360);
        g.drawLine(x, y, x, y-size/5);
        g.drawLine(x,y,x-size/10,y-size/5);
        g.drawLine(x, y, x+size/10, y-size/5);

    }
}
