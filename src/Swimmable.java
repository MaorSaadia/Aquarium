/**
 * Create a base class of all the swimmable Animals
 */

import java.awt.*;
import java.util.Observable;
import java.util.concurrent.CyclicBarrier;

public abstract class Swimmable extends Observable implements SeaCreature,Cloneable,Runnable {

    private static int counter=0;
    protected int ID=counter++;//Make all Animal with unique id
    protected int horSpeed;
    protected int verSpeed;
    protected int eatPop_up=0;

    //def constructor
    public Swimmable(){
        this.horSpeed=0;
        this.verSpeed=0;
    }

    /**
     * constructor
     * @param horSpeed
     * @param verSpeed
     */
    public Swimmable(int horSpeed,int verSpeed){
        this.horSpeed=horSpeed;
        this.verSpeed=verSpeed;
    }
    /**
     * copy constructor
     * @param other
     */
    public Swimmable(Swimmable other){
        this.horSpeed=other.horSpeed;
        this.verSpeed=other.verSpeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Swimmable swimmable = (Swimmable) o;
        return horSpeed == swimmable.horSpeed && verSpeed == swimmable.verSpeed;
    }
    public void setID(int ID) {
        counter=0;
        this.ID = ID;
    }
    public int getHorSpeed() { return horSpeed; }
    public int getVerSpeed() { return verSpeed; }
    public void setHorSpeed(int hor) { horSpeed = hor; }
    public void setVerSpeed(int ver) { verSpeed = ver; }
    public int getID() {return ID;}
    abstract public String getAnimalName();
    abstract public Color getColorAnimal();
    abstract public int getX_front();
    abstract public int getY_front();
    abstract public int getEatfreq();
    abstract public void drawCreatrue(Graphics g);
    abstract public void setSuspend();
    abstract public void setResume();
    abstract public void setBarrier(CyclicBarrier b);
    abstract public void run();
    abstract public void setFlag(boolean flag);
    abstract public int getSize();
    abstract public void eatInc();
    abstract public int getEatCount();
    abstract public String getColor();
    abstract public Swimmable clone();
    abstract public void Edit_Swimmable(int size,int x_front,int y_front, int horSpeed, int verSpeed, Color col);
    abstract public void setState(int size,int x_front,int y_front, int horSpeed, int verSpeed, Color col,int eatfreq);
    abstract public void setHungerState(HungerState state);
    abstract public String getHungerState();
    abstract public void Stop();


    public int compareTo(Swimmable other){
        return this.getSize()-other.getSize();
    }
}