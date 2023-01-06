package cardClasses;

/**
 * Matador legatet
 */
public class ChanceMatadorLegat extends Chancekort
{
    private final int legat=40000, vaerdigraense=15000;

    ChanceMatadorLegat (String nvn, String inf)
    {
        super(nvn, inf);
    }

    public int getLegat()
    {
        return legat;
    }

    public int getVaerdigraense()
    {
        return vaerdigraense;
    }
}
