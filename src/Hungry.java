/**
 *  Hungery class that get a swimmable object and change his status to hungry
 */

public class Hungry implements HungerState{

    @Override
    public void doAction(Swimmable Sw) {
        Sw.setHungerState(this);
    }

    @Override
    public String toString() {
        return "Hungry";
    }
}
