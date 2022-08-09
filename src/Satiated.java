/**
 *  Satiated class that get a swimmable object and change his status to Satiated
 */

public class Satiated implements HungerState{
    @Override
    public void doAction(Swimmable Sw) {
        Sw.setHungerState(this);
    }

    @Override
    public String toString() {
        return "Satiated";
    }
}
