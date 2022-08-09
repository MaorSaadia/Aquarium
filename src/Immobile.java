/**
 * Create a base class of all the Immobile Planet
 */

import java.awt.*;

public abstract class Immobile implements SeaCreature{

    private static int counter=0;
    protected int ID=counter++;//Make all Planet with unique id
    protected int size,x,y;
    protected Color colorr;

    //Constructor
    public Immobile(int size,int x,int y){
        this.size=size;
        this.x=x;
        this.y=y;
        colorr=Color.GREEN;
    }

    //Getters
    abstract public String getPlanetName();

    public int getID() {return ID;}

    public int getSize() {
        return this.size;
    }

    public int getX_front() {
        return x;
    }

    public int getY_front() {
        return y;
    }

    abstract public void setState(int size,int x_front,int y_front);

    public String getColor() {
        return "Green";
    }

}
