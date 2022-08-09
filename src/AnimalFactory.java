/**
 * animal factory class return the specific object by getting a string message
 */

import java.awt.*;

public class AnimalFactory implements AbstractSeaFactory{

    private int size,x_front, y_front,horSpeed,verSpeed,eatfreq;
    private Color col;

    public AnimalFactory(int size, int x_front, int y_front, int horSpeed, int verSpeed, Color col,int eatfreq){
        this.size=size;
        this.x_front=x_front;
        this.y_front=y_front;
        this.horSpeed=horSpeed;
        this.verSpeed=verSpeed;
        this.col=col;
        this.eatfreq=eatfreq;
    }

    @Override
    public SeaCreature produceSeaCreature(String type) {

        //Create a new Fish object or Jellyfish
        if(type.equalsIgnoreCase("Fish")){
            return new Fish(size,x_front,y_front,horSpeed,verSpeed,col,eatfreq);
        }
        if(type.equalsIgnoreCase("Jellyfish"))
            return new Jellyfish(size,x_front,y_front,horSpeed,verSpeed,col,eatfreq);
        return null;
    }
}
