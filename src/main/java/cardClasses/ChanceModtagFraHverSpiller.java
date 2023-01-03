package cardClasses;

/**
 * Spilleren modtager penge fra alle de andre spillere
 */
public class ChanceModtagFraHverSpiller extends Chancekort
{
    private int beloeb;

    ChanceModtagFraHverSpiller(String nvn, String inf, int bel)
    {
        super(nvn, inf);
        beloeb=bel;
    }

    public int getBeloeb()
    {
        return beloeb;
    }

}
