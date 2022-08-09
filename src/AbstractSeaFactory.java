/**
 * interface that  create sea creature swimmable or immobile object
 */
public interface AbstractSeaFactory {

    public SeaCreature produceSeaCreature(String type);

}
