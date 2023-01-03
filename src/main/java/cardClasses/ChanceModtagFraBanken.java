package cardClasses;

/**
 * Spilleren modtager penge fra banken
 */
public class ChanceModtagFraBanken extends Chancekort
{
    private int beloeb;

    ChanceModtagFraBanken(String nvn, String inf, int bel)
    {
        super(nvn, inf);
        beloeb=bel;
    }

    public int getBeloeb()
    {
        return beloeb;
    }
}
