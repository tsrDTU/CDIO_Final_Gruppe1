package cardClasses;

/**
 * Spilleren skal gå i fængsel
 */
public class ChanceGaaIFaengsel extends Chancekort
{
    private final int feltNr=30;

    ChanceGaaIFaengsel (String nvn, String inf)
    {
        super(nvn, inf);
    }

    public int getFeltNr()
    {
        return feltNr;
    }
}
