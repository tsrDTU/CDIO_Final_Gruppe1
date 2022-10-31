/**
 * number: Number of the card
 * name: Name of the card
 * info: The information body of the card
 * action: The action the card initiates. 0= No action, 1 move to the first free field, 3 move jail.
 */
public abstract class ChanceCard implements Comparable<ChanceCard> {

    private String name;
    private String info;
    private int action;

    public ChanceCard ( String nameIn,String infoIn,int actionIn)
    {
        name=nameIn;
        info=infoIn;
        action=actionIn;
    }

    public String toString()
    {
        return number+" ,"+name+" ,"+info+" ,"+action;
    }


}
