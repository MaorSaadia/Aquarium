/**
 * Memento class to save all the current details of the object
 */

import java.awt.Color;

public class Memento {
    private int size,x_front,y_front,horSpeed,verSpeed,ID,eatfreq;
    private Color col;
    private String animalType,planetType;
    private Swimmable sw=null;
    private Immobile im=null;

    public Memento(Swimmable sw)
    {
        this.sw=sw;
        this.animalType=sw.getAnimalName();
        this.col=sw.getColorAnimal();
        this.size=sw.getSize();
        this.x_front=sw.getX_front();
        this.y_front=sw.getY_front();
        this.horSpeed=sw.getHorSpeed();
        this.verSpeed=sw.getHorSpeed();
        this.ID=sw.getID();
        this.eatfreq=sw.getEatfreq();
    }

    public Memento(Immobile im){

        this.im=im;
        this.col=Color.GREEN;
        this.size=im.getSize();
        this.x_front=im.getX_front();
        this.y_front=im.getY_front();
        this.planetType=im.getPlanetName();
        this.ID=im.getID();
    }

    public String getColor() {
        if (col.equals(Color.BLACK))
            return "Black";
        if (col.equals(Color.RED))
            return "Red";
        if (col.equals(Color.BLUE))
            return "Blue";
        if (col.equals(Color.green))
            return "Green";
        if (col.equals(Color.CYAN))
            return "Cyan";
        if (col.equals(Color.ORANGE))
            return "Orange";
        if (col.equals(Color.YELLOW))
            return "Yellow";
        if (col.equals(Color.MAGENTA))
            return "Magenta";
        if (col.equals(Color.PINK))
            return "Pink";
        return String.valueOf(col);
    }

    public int getSize(){return size;}
    public int getXfront(){return x_front;}
    public int getYfront(){return y_front;}
    public int getHorSpeed(){return horSpeed;}
    public int getVerSpeed(){return verSpeed;}
    public int getid(){return ID;}
    public int getEatfreq(){return eatfreq;}
    public String getAnimalName(){return animalType;}
    public String getPlanetName(){return planetType;}

}