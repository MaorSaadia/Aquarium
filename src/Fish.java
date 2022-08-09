import java.awt.*;
import java.util.concurrent.CyclicBarrier;

/**
 * Create a base class of fish
 */
public class Fish extends Swimmable implements MarineAnimal {

    private final int EAT_DISTANCE = 4;
    private int size;
    private Color col;
    private int eatCount;
    private int x_front, y_front, x_dir, y_dir,eatfreq;
    private boolean flag = true;
    private CyclicBarrier cb;
    private HungerState state=null;
    private boolean Stopflag=false; //Stop the fish from running

    //def constructor
    public Fish() {
        super(0, 0);
        this.size = 0;
        this.x_front = 0;
        this.y_front = 0;
        this.col = Color.white;
        eatCount = 1;
        x_dir = 1;
        y_dir = 1;
    }

    //Constructor
    public Fish(int size, int x_front, int y_front, int horSpeed, int verSpeed, Color col,int eatfreq) {
        super(horSpeed, verSpeed);
        this.size = size;
        this.x_front = x_front;
        this.y_front = y_front;
        this.col = col;
        this.eatfreq=eatfreq;
        this.eatCount = 0;
        this.x_dir = 1;
        this.y_dir = 1;
        addObserver(AquaFrame.getPanel());//add the panel to be Observer to the fish
        this.state=new Satiated();

    }


    public Fish(Fish other) {
        super(other.horSpeed, other.verSpeed);
        this.size = other.size;
        this.x_front = other.x_front;
        this.y_front = other.y_front;
        this.col = other.col;
        this.eatfreq=other.eatfreq;
        eatCount = other.eatCount;
        x_dir = other.x_dir;
        y_dir = other.y_dir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fish fish = (Fish) o;
        return EAT_DISTANCE == fish.EAT_DISTANCE && size == fish.size && col == fish.col && eatCount == fish.eatCount && x_front == fish.x_front && y_front == fish.y_front && x_dir == fish.x_dir && y_dir == fish.y_dir;
    }

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
        g.setColor(col);
        if (x_dir == 1) // fish swims to right side
        {
            // Body of fish
            g.fillOval(x_front - size, y_front - size / 4, size, size / 2);
            // Tail of fish
            int[] x_t = {x_front - size - size / 4, x_front - size - size / 4, x_front - size};
            int[] y_t = {y_front - size / 4, y_front + size / 4, y_front};
            Polygon t = new Polygon(x_t, y_t, 3);
            g.fillPolygon(t);
            // Eye of fish
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(new Color(255 - col.getRed(), 255 - col.getGreen(), 255 -
                    col.getBlue()));
            g2.fillOval(x_front - size / 5, y_front - size / 10, size / 10, size / 10);
            // Mouth of fish
            if (size > 70)
                g2.setStroke(new BasicStroke(3));
            else if (size > 30)
                g2.setStroke(new BasicStroke(2));
            else
                g2.setStroke(new BasicStroke(1));
            g2.drawLine(x_front, y_front, x_front - size / 10, y_front + size / 10);
            g2.setStroke(new BasicStroke(1));
        } else // fish swims to left side

        {
            // Body of fish
            g.fillOval(x_front, y_front - size / 4, size, size / 2);
            // Tail of fish
            int[] x_t = {x_front + size + size / 4, x_front + size + size / 4, x_front + size};
            int[] y_t = {y_front - size / 4, y_front + size / 4, y_front};
            Polygon t = new Polygon(x_t, y_t, 3);
            g.fillPolygon(t);
            // Eye of fish
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(new Color(255 - col.getRed(), 255 - col.getGreen(), 255 -
                    col.getBlue()));
            g2.fillOval(x_front + size / 10, y_front - size / 10, size / 10, size / 10);
            // Mouth of fish
            if (size > 70)
                g2.setStroke(new BasicStroke(3));
            else if (size > 30)
                g2.setStroke(new BasicStroke(2));
            else
                g2.setStroke(new BasicStroke(1));
            g2.drawLine(x_front, y_front, x_front + size / 10, y_front + size / 10);
            g2.setStroke(new BasicStroke(1));

        }
    }

    @Override
    //Function that set the fish sleep
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
    //Function that Wakeup fish from sleep
    public void setResume() {
        synchronized (this) {
            flag = true;
            notify();
        }
    }

    @Override
    public void setBarrier(CyclicBarrier b) {
        cb=b;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public Fish clone() {
        return new Fish(size,x_front,y_front,horSpeed,verSpeed,col,eatfreq);
    }

    @Override
    public void Edit_Swimmable(int size,int x_front,int y_front, int horSpeed, int verSpeed, Color col) {
        this.size=size;
        this.x_front=x_front;
        this.y_front=y_front;
        this.horSpeed=horSpeed;
        this.verSpeed=verSpeed;
        this.col=col;

    }

    @Override
    public void setState(int size, int x_front, int y_front, int horSpeed, int verSpeed, Color col,int eatfreq) {
        this.size = size;
        this.x_front = x_front;
        this.y_front = y_front;
        this.horSpeed = horSpeed;
        this.verSpeed = verSpeed;
        this.col = col;
        this.eatfreq=eatfreq;
    }

    //implements run from thread and make the fish swims
    public void run() {

        Rectangle r = AquaFrame.getPanel().getBounds();//get bounds of the screen
        while (Stopflag==false) {

            while (flag == false) {
                setSuspend();
            }

            if(eatPop_up==eatfreq){
                setChanged();
                notifyObservers(getColor()+" Fish With ID "+ID);
                eatPop_up=0;
                state=new Hungry();
                state.doAction(this);
            }

            if(state.toString()=="Hungry" && AquaPanel.checkFood()){

                double oldSpead = Math.sqrt(horSpeed * horSpeed + verSpeed * verSpeed);
                double newHorSpeed = oldSpead * (x_front - r.getWidth() / 2) / (Math.sqrt(Math.pow(x_front - r.getWidth() / 2, 2) + Math.pow(y_front - r.getHeight() / 2, 2)));
                double newVerSpeed = oldSpead * (y_front - r.getHeight() / 2) / (Math.sqrt(Math.pow(x_front - r.getWidth() / 2, 2) + Math.pow(y_front - r.getHeight() / 2, 2)));
                x_front -= newHorSpeed;
                y_front -= newVerSpeed;
                if (x_front < r.getWidth() / 2)
                    x_dir = 1;
                else
                    x_dir = -1;
                if (((Math.abs(x_front - r.getWidth() / 2) < EAT_DISTANCE) && (Math.abs(y_front - r.getHeight() / 2) < EAT_DISTANCE))) {
                    AquaFrame.getPanel().eatFood(this);
                    state=new Satiated();
                    state.doAction(this);
                }
            } else {

                x_front += horSpeed * x_dir;
                y_front += verSpeed * y_dir;
            }

            if (x_front > r.getWidth()) {
                x_dir = -1;
                eatPop_up++;
                x_front = (int) (r.getWidth() - size * 1.25);
            } else if (x_front < 0) {
                x_dir = 1;
                x_front = (int) (size * 1.25);
                eatPop_up++;
            }

            if (y_front > (int) (r.getHeight() - 30 - size * 0.25)) {
                y_dir = -1;
            } else if (y_front < (int) (size * 0.25)) {
                y_dir = 1;
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
            AquaFrame.getPanel().repaint();

        }
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


    //Getter
    @Override
    public int getEatCount() {
        return eatCount;
    }

    //Getter
    @Override
    public int getSize() {
        return size;
    }

    @Override
    //Return The color of the fish
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

    @Override
    public void eatInc() {
        if (eatCount == EAT_DISTANCE) {
            eatCount = 0;
            changeFish();
        }
        eatCount += 1;
    }

    public void changeFish() {
        size += 1;
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
