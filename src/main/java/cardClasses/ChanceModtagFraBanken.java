package cardClasses;

public class ChanceModtagFraBanken extends Chance
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
