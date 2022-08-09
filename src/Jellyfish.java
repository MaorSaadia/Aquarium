/**
 * Create a base class of jellyfish
 */

import java.awt.*;
import java.util.concurrent.CyclicBarrier;

public class Jellyfish extends Swimmable implements MarineAnimal {

    private final int EAT_DISTANCE = 5;
    private int size;
    private Color col;
    private int eatCount;
    private int x_front, y_front, x_dir, y_dir,eatfreq;
    private boolean flag = true;
    private CyclicBarrier cb;
    private HungerState state;
    private boolean Stopflag=false; //Stop the jellyfish from running

    //def constructor
    public Jellyfish() {
        super(0, 0);
        this.size = 0;
        this.x_front = 0;
        this.y_front = 0;
        this.col = Color.white;
        eatCount = 0;
        x_dir = 1;
        y_dir = 1;
    }

    public Jellyfish(int size, int x_front, int y_front, int horSpeed, int verSpeed, Color col,int eatfreq) {
        super(horSpeed, verSpeed);
        this.size = size;
        this.x_front = x_front;
        this.y_front = y_front;
        this.col = col;
        this.eatfreq=eatfreq;
        this.eatCount = 0;
        this.x_dir = 0;
        this.y_dir = 0;
        addObserver(AquaFrame.getPanel());//add the panel to be Observer to the jellyfish
        this.state=new Satiated();
    }

    public Jellyfish(Jellyfish other) {
        super(other.horSpeed, other.verSpeed);
        this.size = other.size;
        this.x_front = other.x_front;
        this.y_front = other.y_front;
        this.col = other.col;
        eatCount = other.eatCount;
        x_dir = other.x_dir;
        y_dir = other.y_dir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jellyfish jellyfish = (Jellyfish) o;
        return EAT_DISTANCE == jellyfish.EAT_DISTANCE && size == jellyfish.size && col == jellyfish.col && eatCount == jellyfish.eatCount && x_front == jellyfish.x_front && y_front == jellyfish.y_front && x_dir == jellyfish.x_dir && y_dir == jellyfish.y_dir;
    }

    //Getter
    @Override
    public String getAnimalName() {
        String classname = this.getClass().getSimpleName();
        return classname;
    }

    @Override
    public Color getColorAnimal() {
        return this.col;
    }

    @Override
    public void drawCreatrue(Graphics g) {
        int numLegs;
        if (size < 40)
            numLegs = 5;
        else if (size < 80)
            numLegs = 9;
        else
            numLegs = 12;
        g.setColor(col);
        g.fillArc(x_front - size / 2, y_front - size / 4, size, size / 2, 0, 180);
        for (int i = 0; i < numLegs; i++)
            g.drawLine(x_front - size / 2 + size / numLegs + size * i / (numLegs + 1),
                    y_front, x_front - size / 2 + size / numLegs + size * i / (numLegs + 1),
                    y_front + size / 3);
    }


    @Override
    //Function that set the Jellyfish sleep
    public void setSuspend() {
        synchronized (this) {
            flag = false;
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    //Function that Wakeup Jellyfish from sleep
    public void setResume() {
        synchronized (this) {
            flag = true;
            notify();
        }
    }

    @Override
    public void setBarrier(CyclicBarrier b) {
        cb = b;
    }

    //Check for flag
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    //clone the Jellyfish
    public Jellyfish clone() {
        return new Jellyfish(size,x_front,y_front,horSpeed,verSpeed,col,eatfreq);
    }

    @Override
    //Edit the Jellyfish
    public void Edit_Swimmable(int size,int x_front,int y_front, int horSpeed, int verSpeed, Color col) {
        this.size=size;
        this.x_front=x_front;
        this.y_front=y_front;
        this.horSpeed=horSpeed;
        this.verSpeed=verSpeed;
        this.col=col;
    }

    @Override
    //
    public void setState(int size, int x_front, int y_front, int horSpeed, int verSpeed, Color col,int eatfreq) {
        this.size = size;
        this.x_front = x_front;
        this.y_front = y_front;
        this.horSpeed = horSpeed;
        this.verSpeed = verSpeed;
        this.col = col;
        this.eatfreq=eatfreq;

    }

    //implements run from thread and make the Jellyfish swims
    public void run() {

        Rectangle r = AquaFrame.getPanel().getBounds();//get bounds of the screen
        while (Stopflag==false) {
            while (flag == false) {
                setSuspend();
            }

            if (eatPop_up == eatfreq) {
                setChanged();
                notifyObservers(getColor() + " Jellyfish With ID " + ID);
                eatPop_up = 0;
                state = new Hungry();
                state.doAction(this);
            }

            //Swims with food on the screen and the jellyfish Hungry
            if (state.toString() == "Hungry" && AquaPanel.checkFood()) {

                double m = (271.0 - y_front) / (519.0 - x_front);//Gradient calculation
                if (x_front > (r.width / 2)) {
                    if (horSpeed > 0) {
                        horSpeed = horSpeed * -1;
                    }
                    x_front = x_front + horSpeed;
                    y_front = (int) ((m * (double) (x_front - (r.width / 2))) + (r.height / 2));//Straight line equation
                } else {
                    if (horSpeed < 0) {
                        horSpeed = horSpeed * -1;
                    }
                    x_front = x_front + horSpeed;
                    y_front = (int) ((m * (double) (x_front - (r.width / 2))) + (r.height / 2));//Straight line equation
                }

                //Check if the jellyfish is close to the worm
                if ((Math.abs(r.width / 2 - x_front) <= 5) && (Math.abs(r.height / 2 - y_front) <= 5)) {
                    AquaFrame.getPanel().eatFood(this);// call callback on AquaPanel
                    state=new Satiated();
                    state.doAction(this);
                }
            }

            //Swims without food on the screen
            else {
                if (x_front > r.width-(size/2)) {
                    horSpeed = horSpeed * -1;
                    x_front = r.width - size - (size / 4);
                    eatPop_up++;
                }
                if (y_front > (int) (r.height-30-size*0.25)) {
                    verSpeed = verSpeed * -1;
                }
                if (x_front < 0 + (size/2)) {
                    horSpeed = horSpeed * -1;
                    x_front = size + (size / 4);
                    eatPop_up++;
                }
                if (y_front < (int) (size*0.25)) {
                    verSpeed = verSpeed * -1;
                }
                y_front += verSpeed;
                x_front += horSpeed;
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
            AquaFrame.getPanel().repaint();
        }
    }

    @Override
    public int getEatCount() {
        return eatCount;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    //Return The color of the jellyfish
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
        return "Custom Color";
    }

    public int getX_front() {
        return x_front;
    }

    public int getY_front() {
        return y_front;
    }

    @Override
    public int getEatfreq() {
        return eatfreq;
    }

    @Override
    public void eatInc() {
        if (eatCount >= EAT_DISTANCE) {
            eatCount = 0;
            size += 1;
        }
        eatCount += 1;
    }

    @Override
    public void PaintFish(Color col) {
        this.col=col;
    }

    @Override
    public void setHungerState(HungerState state) {
        this.state=state;
    }

    @Override
    public String getHungerState() {
        return state.toString();
    }

    @Override
    public void Stop() {
        Stopflag=true;
        Thread.currentThread().interrupt();
    }
}
