package cardClasses;

/**
 * Betaling fra spilleren til banken
 */
public class ChanceBetalBanken extends Chancekort
{
    private int beloeb;

    ChanceBetalBanken(String nvn, String inf, int bel)
    {
        super(nvn, inf);
        beloeb=bel;
    }

    public int getBeloeb()
    {
        return beloeb;
    }
}
