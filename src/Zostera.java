/**
 * Zostera class inherite from Immobile and implements all is methods
 */

import java.awt.*;

public class Zostera extends Immobile{

    public Zostera(int s, int x, int y) {
        super(s, x, y);
    }

    @Override
    public String getPlanetName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void setState(int size, int x_front, int y_front) {
        super.size=size;
        super.x=x_front;
        super.y=y_front;
    }


    @Override
    public void drawCreatrue(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.setColor(colorr);
        g.drawLine(x, y, x, y-size);
        g.drawLine(x-2, y, x-10, y-size*9/10);
        g.drawLine(x+2, y, x+10, y-size*9/10);
        g.drawLine(x-4, y, x-20, y-size*4/5);
        g.drawLine(x+4, y, x+20, y-size*4/5);
        g.drawLine(x-6, y, x-30, y-size*7/10);
        g.drawLine(x+6, y, x+30, y-size*7/10);
        g.drawLine(x-8, y, x-40, y-size*4/7);
        g.drawLine(x+8, y, x+40, y-size*4/7);
        g2.setStroke(new BasicStroke(1));

    }
}
